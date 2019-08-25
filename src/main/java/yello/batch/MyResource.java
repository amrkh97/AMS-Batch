package yello.batch;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BLL.BatchManager;
import Models.ArrayOfMedicines;
import Models.BatchUpdateModel;
import Models.Medicine;
import Models.MedicineUsedModel;
import Models.ServerResponse;
import Models.BatchResponse.BatchResponseModel;
import Models.Data.DataModel;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Batch Services Are Running!";
	}

	@Path("batch/addBatch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBatch(ArrayOfMedicines medicineList) {
		 BatchResponseModel _BatchResponseModel = new BatchResponseModel();
		_BatchResponseModel =  BatchManager.createBatch(medicineList.getMedicineArray());
		if(_BatchResponseModel.getIsMissing()) {
			//TODO: Add appropriate response.
			_BatchResponseModel.setResponseMsg("A01002001001");
			return Response.status(401).entity(_BatchResponseModel)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.ok(_BatchResponseModel)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	@Path("batch/updateMedicinesUsed")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMedicineUsed(MedicineUsedModel medicineList) {
		ServerResponse response  = new ServerResponse();
		response = BatchManager.updateMedicinesUsed(medicineList);
		switch (response.getResponseMsg()) {
		case "01":
			response.setResponseMsg("A01002002001");
			return Response.ok(response)
					.header("Access-Control-Allow-Origin", "*").build();
			

		default:
			return Response.ok(response)
					.header("Access-Control-Allow-Origin", "*").build();
			
		}
		
	}

	@Path("batch/updateAmbulanceMapWithBatch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateAmbulanceMapWithBatch(DataModel _dataModel) {
		ServerResponse response = new ServerResponse();
		response = BatchManager.updateAmbulanceMapWithBatch(_dataModel.getSentID(),_dataModel.getLongID());
		switch (response.getResponseHexCode()) {
		case "01":
			response.setResponseMsg("A01002003001");
			return Response.status(401).entity(response)
					.header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.ok(response)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	
	@Path("batch/getAllMedicines")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicines(DataModel dataModel) {
		
		return Response.ok(BatchManager.getAllMedicines(dataModel))
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("batch/updateBatch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBatch(BatchUpdateModel medicineList) {
		 BatchResponseModel _BatchResponseModel = new BatchResponseModel();
		_BatchResponseModel =  BatchManager.updateBatch(medicineList.getBatchID(),medicineList.getMedicinesToUpdate());
		if(_BatchResponseModel.getIsMissing()) {
			_BatchResponseModel.setResponseMsg("A01002005001");
			return Response.status(401).entity(_BatchResponseModel)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.ok(_BatchResponseModel)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	
	@Path("batch/getAllbatches")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllbatches() {
		
		return Response.ok(BatchManager.getAllbatches())
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("batch/getAllAssigned")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssigned() {
		
		return Response.ok(BatchManager.getAllAssigned())
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("batch/getAllUnAssigned")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllUnAssigned() {
		
		return Response.ok(BatchManager.getAllUnAssigned())
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("batch/getBatchesByMedName")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response getBatchesByMedName(Medicine medicine) {
		
		return Response.ok(BatchManager.getBatchesByMedName(medicine))
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
}
