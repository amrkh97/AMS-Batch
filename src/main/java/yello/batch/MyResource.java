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
import Models.MedicineUsedModel;
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
		return "Hello, Heroku!";
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
			return Response.ok(_BatchResponseModel)
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
		
		return Response.ok(BatchManager.updateMedicinesUsed(medicineList))
				.header("Access-Control-Allow-Origin", "*").build();
		
	}

	@Path("batch/updateAmbulanceMapWithBatch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateAmbulanceMapWithBatch(DataModel _dataModel) {
		
		return Response.ok(BatchManager.updateAmbulanceMapWithBatch(_dataModel.getSentID(),_dataModel.getLongID()))
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
}
