package Models;

public class AmbBatchMapModel {

	private Long batchID;
	private Integer vin;
	private Boolean isAssigned;
	
	public Long getBatchID() {
		return batchID;
	}
	public void setBatchID(Long batchID) {
		this.batchID = batchID;
	}
	public Integer getVin() {
		return vin;
	}
	public void setVin(Integer vin) {
		this.vin = vin;
	}
	public Boolean getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
}
