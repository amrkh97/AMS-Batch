package BLL;

import java.util.ArrayList;

import DAL.BatchDAL;
import Models.Medicine;

public class BatchManager {

	public static Integer checkID() {
		Integer randomID = (int)(Math.random() * 99999 + 1);
		return BatchDAL.checkID(randomID);
	}
	
	public static Integer createBatch(Integer id,ArrayList<Medicine> medicineList) {
		
		for(Medicine currentMedicine: medicineList) {
			BatchDAL.createBatch(id,currentMedicine);
		}
		
		System.out.println("BatchID: "+id);
		return id;
	}
}
