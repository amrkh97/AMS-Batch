package Models;

public class Medicine {
private String barCode;
private String medicineName;
private String price;
private Integer countInStock;
private String implications;
private String medicineUsage ;
private String sideEffects;
private String activeComponent;
private String medicineStatus ;


public String getBarCode() {
	return barCode;
}
public void setBarCode(String barCode) {
	this.barCode = barCode;
}
public String getMedicineName() {
	return medicineName;
}
public void setMedicineName(String medicineName) {
	this.medicineName = medicineName;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public Integer getCountInStock() {
	return countInStock;
}
public void setCountInStock(Integer countInStock) {
	this.countInStock = countInStock;
}
public String getImplications() {
	return implications;
}
public void setImplications(String implications) {
	this.implications = implications;
}
public String getMedicineUsage() {
	return medicineUsage;
}
public void setMedicineUsage(String medicineUsage) {
	this.medicineUsage = medicineUsage;
}
public String getSideEffects() {
	return sideEffects;
}
public void setSideEffects(String sideEffects) {
	this.sideEffects = sideEffects;
}
public String getActiveComponent() {
	return activeComponent;
}
public void setActiveComponent(String activeComponent) {
	this.activeComponent = activeComponent;
}
public String getMedicineStatus() {
	return medicineStatus;
}
public void setMedicineStatus(String medicineStatus) {
	this.medicineStatus = medicineStatus;
}

}
