package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.ServicioProducto;

@Path("ServicioProducto")
public class ServicioProductoService 
{

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
	 * Metodo que expone servicio REST usando GET que da todos los ServicioProductos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ServicioProductoAndes/rest/ServicioProductos
	 * @return Json con todos los ServicioProductos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getServicioProductos() {
		TM tm = new TM(getPath());
		List<ServicioProducto> ServicioProductos;
		try {
			ServicioProductos = tm.darServicioProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ServicioProductos).build();
	}

	


    /**
     * Metodo que expone servicio REST usando POST que agrega el ServicioProducto que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ServicioProductoAndes/rest/ServicioProductos/ServicioProducto
     * @param ServicioProducto - ServicioProducto a agregar
     * @return Json con el ServicioProducto que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServicioProducto(ServicioProducto ServicioProducto) {
		TM tm = new TM(getPath());
		try {
			tm.addServicioProducto(ServicioProducto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ServicioProducto).build();
	}
	
    
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteServicioProducto(ServicioProducto ServicioProducto) {
		TM tm = new TM(getPath());
		try {
			tm.deleteServicioProducto(ServicioProducto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ServicioProducto).build();
	}

}
