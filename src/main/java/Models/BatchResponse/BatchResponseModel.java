package Models.BatchResponse;

import java.util.ArrayList;

import Models.ServerResponse;

public class BatchResponseModel extends ServerResponse{
	private Long batchID;
	private ArrayList<String> missingMedicines;
	private Boolean isMissing;
	
	public Long getBatchID() {
		return batchID;
	}
	public void setBatchID(Long batchID) {
		this.batchID = batchID;
	}
	public ArrayList<String> getMissingMedicines() {
		return missingMedicines;
	}
	public void setMissingMedicines(ArrayList<String> missingMedicines) {
		this.missingMedicines = missingMedicines;
	}
	public Boolean getIsMissing() {
		return isMissing;
	}
	public void setIsMissing(Boolean isMissing) {
		this.isMissing = isMissing;
	}

}
