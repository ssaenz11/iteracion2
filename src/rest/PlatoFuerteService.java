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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.PlatoFuerte;

@Path("PlatoFuerte")
public class PlatoFuerteService 
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
	 * Metodo que expone servicio REST usando GET que da todos los PlatoFuertes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
	 * @return Json con todos los PlatoFuertes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPlatoFuertes() {
		TM tm = new TM(getPath());
		List<PlatoFuerte> PlatoFuertes;
		try {
			PlatoFuertes = tm.darPlatoFuertes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuertes).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el PlatoFuerte con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del PlatoFuerte a buscar que entra en la URL como parametro 
     * @return Json con el/los PlatoFuertes encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPlatoFuerteName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		PlatoFuerte PlatoFuertes = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del PlatoFuerte no valido");
			
			PlatoFuertes = tm.buscarPlatoFuertePorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuertes).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el PlatoFuerte que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes/PlatoFuerte
     * @param PlatoFuerte - PlatoFuerte a agregar
     * @return Json con el PlatoFuerte que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlatoFuerte(PlatoFuerte PlatoFuerte) {
		TM tm = new TM(getPath());
		try {
			tm.addPlatoFuerte(PlatoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuerte).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el PlatoFuerte que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
     * @param PlatoFuerte - PlatoFuerte a actualizar. 
     * @return Json con el PlatoFuerte que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlatoFuerte(PlatoFuerte PlatoFuerte) {
		TM tm = new TM(getPath());
		try {
			tm.updatePlatoFuerte(PlatoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuerte).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el PlatoFuerte que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
     * @param PlatoFuerte - PlatoFuerte a aliminar. 
     * @return Json con el PlatoFuerte que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlatoFuerte(PlatoFuerte PlatoFuerte) {
		TM tm = new TM(getPath());
		try {
			tm.deletePlatoFuerte(PlatoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuerte).build();
	}

	
}
