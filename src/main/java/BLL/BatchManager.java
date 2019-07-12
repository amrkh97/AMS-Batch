package BLL;

import java.util.ArrayList;
import DAL.BatchDAL;
import DB.DBManager;
import java.sql.Connection;
import java.sql.SQLException;

import HelperClasses.UniqueIdGenerator;
import Models.ArrayOfMedicines;
import Models.Medicine;
import Models.MedicineUsedModel;
import Models.ServerResponse;
import Models.BatchResponse.BatchResponseModel;
import Models.Data.DataModel;

public class BatchManager {

	public static BatchResponseModel createBatch(ArrayList<Medicine> medicineList) {

		ArrayList<String> _MedicineNames = new ArrayList<String>();
		Long id = UniqueIdGenerator.generateLongId();
		BatchResponseModel _BatchResponseModel = new BatchResponseModel();

		_BatchResponseModel.setBatchID(id);
		_BatchResponseModel.setIsMissing(false);
		Connection conn = DBManager.getDBConn();

		try {
			for (Medicine currentMedicine : medicineList) {

				StringBuilder _StringBuilder = new StringBuilder();
				StringBuilder _compareString = new StringBuilder();

				String _intermediateString = BatchDAL.createBatch(id, currentMedicine, conn);

				_StringBuilder.append(_intermediateString);
				_compareString.append("00");

				if (!_compareString.toString().equals(_StringBuilder.toString())) {
					_MedicineNames.add(currentMedicine.getMedicineName());
					_BatchResponseModel.setIsMissing(true);
				}
			}

		} finally {
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		_BatchResponseModel.setMissingMedicines(_MedicineNames);
		return _BatchResponseModel;
	}

	public static ServerResponse updateAmbulanceMapWithBatch(Integer VIN, Long ID) {
		ServerResponse serverResp = new ServerResponse();
		serverResp.setResponseHexCode(BatchDAL.updateAmbulanceMapWithBatch(VIN, ID));
		if(serverResp.getResponseHexCode().equals("00")) {
			serverResp.setResponseMsg("Assignment Successful");
		}else {
			serverResp.setResponseMsg("Failed to Assign the batch to vehicle with VIN: "+VIN);
		}
		
		return serverResp;
	}

	public static ServerResponse updateMedicinesUsed(MedicineUsedModel medicineList) {

		ArrayList<Medicine> UsedMedicineList = new ArrayList<Medicine>();
		UsedMedicineList.addAll(medicineList.getArrayOfMedicines().getMedicineArray());
		Connection conn = DBManager.getDBConn();
		ServerResponse serverResp = new ServerResponse();
		serverResp.setResponseHexCode("99");
		serverResp.setResponseMsg("Error in Connection");
		Integer counterCheck = 0;
		try {
			for (Medicine currentMedicine : UsedMedicineList) {

				serverResp = BatchDAL.updateMedicinesUsed(medicineList.getBatchID(), medicineList.getSequenceNumber(),
						currentMedicine.getBarCode(), currentMedicine.getCountInStock(), conn);
				if (serverResp.getResponseHexCode().equals("00")) {
					counterCheck++;
				}
			}
		} finally {
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (!counterCheck.equals(UsedMedicineList.size())) {
			serverResp.setResponseHexCode("01");
			serverResp.setResponseMsg("Count of some used medicine isn't consistent with that assigned to this batch: "
					+ medicineList.getBatchID());
		}

		return serverResp;
	}
	
	public static ArrayOfMedicines getAllMedicines(DataModel dataModel) {
		ArrayOfMedicines arrayOfMedicines = new ArrayOfMedicines();
		arrayOfMedicines.setMedicineArray(BatchDAL.getAllMedicines(dataModel.getLongID()));
		return arrayOfMedicines;
	}

}
