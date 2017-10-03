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
import vos.Acompaņamiento;

@Path("Acompaņamiento")
public class AcompaņamientoService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Acompaņamientos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos
	 * @return Json con todos los Acompaņamientos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAcompaņamientos() {
		TM tm = new TM(getPath());
		List<Acompaņamiento> Acompaņamientos;
		try {
			Acompaņamientos = tm.darAcompaņamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamientos).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Acompaņamiento con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Acompaņamiento a buscar que entra en la URL como parametro 
     * @return Json con el/los Acompaņamientos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompaņamientoName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Acompaņamiento Acompaņamientos = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Acompaņamiento no valido");
			
			Acompaņamientos = tm.buscarAcompaņamientoPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamientos).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Acompaņamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos/Acompaņamiento
     * @param Acompaņamiento - Acompaņamiento a agregar
     * @return Json con el Acompaņamiento que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompaņamiento(Acompaņamiento Acompaņamiento) {
		TM tm = new TM(getPath());
		try {
			tm.addAcompaņamiento(Acompaņamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamiento).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Acompaņamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos
     * @param Acompaņamiento - Acompaņamiento a actualizar. 
     * @return Json con el Acompaņamiento que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcompaņamiento(Acompaņamiento Acompaņamiento) {
		TM tm = new TM(getPath());
		try {
			tm.updateAcompaņamiento(Acompaņamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamiento).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Acompaņamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos
     * @param Acompaņamiento - Acompaņamiento a aliminar. 
     * @return Json con el Acompaņamiento que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompaņamiento(Acompaņamiento Acompaņamiento) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAcompaņamiento(Acompaņamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamiento).build();
	}

	
}
