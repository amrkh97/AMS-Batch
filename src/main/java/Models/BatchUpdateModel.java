package Models;

import java.util.ArrayList;

public class BatchUpdateModel {
	
	private Long batchID;
	private ArrayList<Medicine> medicinesToUpdate;
	
	public Long getBatchID() {
		return batchID;
	}
	public void setBatchID(Long batchID) {
		this.batchID = batchID;
	}
	public ArrayList<Medicine> getMedicinesToUpdate() {
		return medicinesToUpdate;
	}
	public void setMedicinesToUpdate(ArrayList<Medicine> medicinesToUpdate) {
		this.medicinesToUpdate = medicinesToUpdate;
	}
	
}
