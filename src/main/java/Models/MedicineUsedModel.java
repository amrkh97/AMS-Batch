package Models;

public class MedicineUsedModel {
	
	private ArrayOfMedicines arrayOfMedicines;
	private Long batchID;
	private Integer sequenceNumber;
	
	public ArrayOfMedicines getArrayOfMedicines() {
		return arrayOfMedicines;
	}
	public void setArrayOfMedicines(ArrayOfMedicines arrayOfMedicines) {
		this.arrayOfMedicines = arrayOfMedicines;
	}
	public Long getBatchID() {
		return batchID;
	}
	public void setBatchID(Long batchID) {
		this.batchID = batchID;
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
