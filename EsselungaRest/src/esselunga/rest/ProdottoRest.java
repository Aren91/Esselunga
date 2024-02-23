package esselunga.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import esselunga.ejb.interfaces.ProdottoEjbInterface;
import esselunga.jpa.models.Prodotto;
import esselunga.rest.config.EJBFactory;

@Path("/prodottoRest")
public class ProdottoRest {
	
	private ProdottoEjbInterface prodottoEjbInterface;
	
	private final static String bodyType = "application/json";
	
	@POST
	@Path("/insertProdotto")
	@Produces(bodyType)
	@Consumes(bodyType)
	public Response insert(Prodotto prodotto) {
		
		logInit("insert", prodotto);
		try {
			prodottoEjbInterface = new EJBFactory<ProdottoEjbInterface>(ProdottoEjbInterface.class).getEJB();
			prodotto = prodottoEjbInterface.insert(prodotto);
			
			return Response.status(Status.CREATED).entity(prodotto).build();
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/updateProdotto")
	@Produces(bodyType)
	@Consumes(bodyType)
	public Response update(Prodotto prodotto) {
		
		logInit("update", prodotto);
		try {
			prodottoEjbInterface = new EJBFactory<ProdottoEjbInterface>(ProdottoEjbInterface.class).getEJB();
			prodotto = prodottoEjbInterface.update(prodotto);
			
			return Response.ok(prodotto).build();
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/deleteProdotto")
	@Consumes(bodyType)
	public Response delete(Prodotto prodotto) {
		
		logInit("delete", prodotto);
		try {
			prodottoEjbInterface = new EJBFactory<ProdottoEjbInterface>(ProdottoEjbInterface.class).getEJB();
			prodottoEjbInterface.delete(prodotto);
			
			return Response.noContent().build();
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	@GET
	@Path("/findProdottoById")
	public Response findById(@QueryParam("id")Integer id) {
		
		logInit("findById", id);
		Prodotto prodottoTrovato = new Prodotto();
		try {
			prodottoEjbInterface = new EJBFactory<ProdottoEjbInterface>(ProdottoEjbInterface.class).getEJB();
			prodottoTrovato = prodottoEjbInterface.findById(id);
			
			return Response.status(Status.OK).entity(prodottoTrovato).build();
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	public Response findAll() {
		
		logInit("findAll", null);
		List<Prodotto> prodottiTrovati = new ArrayList<Prodotto>();
		try {
			prodottoEjbInterface = new EJBFactory<ProdottoEjbInterface>(ProdottoEjbInterface.class).getEJB();
			prodottiTrovati = prodottoEjbInterface.findAll();
			
			return Response.status(Status.OK).entity(prodottiTrovati).build();
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	protected void logInit(String method) {
		logInit(method, null);
	}
	
	protected void logInit(String method, Object object) {
		System.out.println(" -- Dentro " + this.getClass().getSimpleName() + " -- " + method + " -- "
			+ (object != null
				? object.toString()
				: ""));
	}

}
