package yello.batch;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BLL.BatchManager;
import Models.ArrayOfMedicines;
import Models.Medicine;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
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
    	return Response.ok().entity(BatchManager.createBatch(medicineList.getMedicineArray())).build();
    }
    
    
}
