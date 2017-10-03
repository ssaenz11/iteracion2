package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.PreferenciaCliente;

@Path("PreferennciaCliente")
public class PreferenciaClienteService {

	
	
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	/**
	 * Metodo que expone servicio REST usando GET que da todos los PreferenciaClientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PreferenciaClienteAndes/rest/PreferenciaClientes
	 * @return Json con todos los PreferenciaClientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPreferenciaClientes() {
		TM tm = new TM(getPath());
		List<PreferenciaCliente> PreferenciaClientes;
		try {
			PreferenciaClientes = tm.darPreferenciaClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PreferenciaClientes).build();
	}

	


    /**
     * Metodo que expone servicio REST usando POST que agrega el PreferenciaCliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PreferenciaClienteAndes/rest/PreferenciaClientes/PreferenciaCliente
     * @param PreferenciaCliente - PreferenciaCliente a agregar
     * @return Json con el PreferenciaCliente que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPreferenciaCliente(PreferenciaCliente PreferenciaCliente) {
		TM tm = new TM(getPath());
		try {
			tm.addPreferenciaCliente(PreferenciaCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PreferenciaCliente).build();
	}
	
    
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePreferenciaCliente(PreferenciaCliente PreferenciaCliente) {
		TM tm = new TM(getPath());
		try {
			tm.deletePreferenciaCliente(PreferenciaCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PreferenciaCliente).build();
	}

}
