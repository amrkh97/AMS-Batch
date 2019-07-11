package BLL;

import java.util.ArrayList;
import DAL.BatchDAL;
import DB.DBManager;
import java.sql.Connection;
import java.sql.SQLException;

import HelperClasses.UniqueIdGenerator;
import Models.Medicine;
import Models.BatchResponse.BatchResponseModel;

public class BatchManager {

	public static BatchResponseModel createBatch(ArrayList<Medicine> medicineList) {

		ArrayList<String> _MedicineNames = new ArrayList<String>();
		Long id = UniqueIdGenerator.generateLongId();
		BatchResponseModel _BatchResponseModel = new BatchResponseModel();
		
		_BatchResponseModel.setBatchID(id);
		_BatchResponseModel.setIsMissing(false);
		Connection conn=DBManager.getDBConn();
		
		try {
			for (Medicine currentMedicine : medicineList) {
				
				StringBuilder _StringBuilder = new StringBuilder();
				StringBuilder _compareString = new StringBuilder();
				
				String _intermediateString = BatchDAL.createBatch(id, currentMedicine, conn);
				
				_StringBuilder.append(_intermediateString);
				_compareString.append("00");
				
				if(!_compareString.toString().equals(_StringBuilder.toString())) {
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
		_BatchResponseModel.setMissingMedicines( _MedicineNames);
		return _BatchResponseModel;
	}
	
	
	public static String updateAmbulanceMapWithBatch(Integer VIN,Long ID) {
		
		return BatchDAL.updateAmbulanceMapWithBatch(VIN, ID);
	}
}
