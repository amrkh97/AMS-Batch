package BLL;

import java.util.ArrayList;

import DAL.BatchDAL;
import HelperClasses.UniqueIdGenerator;
import Models.Medicine;

public class BatchManager {

	
	public static Long createBatch(ArrayList<Medicine> medicineList) {
		
		Long id = UniqueIdGenerator.generateLongId();
		
		for(Medicine currentMedicine: medicineList) {
			BatchDAL.createBatch(id,currentMedicine);
		}
		
		System.out.println("BatchID: "+id);
		return id;
	}
}
