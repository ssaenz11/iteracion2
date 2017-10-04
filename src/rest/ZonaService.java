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
import vos.Usuario;
import vos.Zona;

@Path("Zonas")
public class ZonaService {


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
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getZonas() {
		TM tm = new TM(getPath());
		List<Zona> zonas;
		try {
			zonas = tm.darZonas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zonas).build();
	}

    /**
     * Metodo que expone servicio REST usando GET que busca el Cliente con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Cliente a buscar que entra en la URL como parametro 
     * @return Json con el/los Clientes encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonaName( @QueryParam("nombre") String name) {
		TM tm = new TM(getPath());
		List<Zona> zonas;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Usuario no valido");
			zonas = tm.buscarZonaPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zonas).build();
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param zona - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(Zona zona) {
		TM tm = new TM(getPath());
		try {
			tm.addZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Clientes que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/varios
     * @param zonas - Clientes a agregar. 
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(List<Zona> zonas) {
		TM tm = new TM(getPath());
		try {
			tm.addZonas(zonas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zonas).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param zona - Cliente a actualizar. 
     * @return Json con el Cliente que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateZona(Zona zona) {
		TM tm = new TM(getPath());
		try {
			tm.updateZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param Zona - Cliente a aliminar. 
     * @return Json con el Cliente que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteZona(Zona Zona) {
		TM tm = new TM(getPath());
		try {
			tm.deleteZona(Zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zona).build();
	}
}
