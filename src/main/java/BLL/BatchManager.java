package BLL;

import java.util.ArrayList;
import DAL.BatchDAL;
import HelperClasses.UniqueIdGenerator;
import Models.Medicine;
import Models.BatchResponse.BatchResponseModel;

public class BatchManager {

	public static BatchResponseModel createBatch(ArrayList<Medicine> medicineList) {

		ArrayList<String> _MedicineNames = new ArrayList<String>();
		Long id = UniqueIdGenerator.generateLongId();
		BatchResponseModel _BatchResponseModel = new BatchResponseModel();
		
		_BatchResponseModel.setBatchID(id);
		for (Medicine currentMedicine : medicineList) {
			
			StringBuilder _StringBuilder = new StringBuilder();
			StringBuilder _compareString = new StringBuilder();
			
			String _intermediateString = BatchDAL.createBatch(id, currentMedicine);
			
			_StringBuilder.append(_intermediateString);
			_compareString.append("00");
			
			if(!_compareString.equals(_StringBuilder)) {
				_MedicineNames.add(currentMedicine.getMedicineName());
				_BatchResponseModel.setIsMissing(true);
			}
		}
		
		return _BatchResponseModel;
	}
	
	
	public static String updateAmbulanceMapWithBatch(Integer VIN,Long ID) {
		
		return BatchDAL.updateAmbulanceMapWithBatch(VIN, ID);
	}
}
