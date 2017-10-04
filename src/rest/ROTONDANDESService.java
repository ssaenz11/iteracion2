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
import vos.PreferenciaCliente;
import vos.ServicioProducto;

@Path("RotonAndes")
public class ROTONDANDESService 
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
	@Path("ServiciosHechos")
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
	
	
	


   
}
