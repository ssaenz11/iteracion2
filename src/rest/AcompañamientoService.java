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
import vos.Acompa�amiento;

@Path("Acompa�amiento")
public class Acompa�amientoService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Acompa�amientos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/Acompa�amientoAndes/rest/Acompa�amientos
	 * @return Json con todos los Acompa�amientos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAcompa�amientos() {
		TM tm = new TM(getPath());
		List<Acompa�amiento> Acompa�amientos;
		try {
			Acompa�amientos = tm.darAcompa�amientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompa�amientos).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Acompa�amiento con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/Acompa�amientoAndes/rest/Acompa�amientos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Acompa�amiento a buscar que entra en la URL como parametro 
     * @return Json con el/los Acompa�amientos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompa�amientoName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Acompa�amiento Acompa�amientos = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Acompa�amiento no valido");
			
			Acompa�amientos = tm.buscarAcompa�amientoPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompa�amientos).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Acompa�amiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/Acompa�amientoAndes/rest/Acompa�amientos/Acompa�amiento
     * @param Acompa�amiento - Acompa�amiento a agregar
     * @return Json con el Acompa�amiento que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompa�amiento(Acompa�amiento Acompa�amiento) {
		TM tm = new TM(getPath());
		try {
			tm.addAcompa�amiento(Acompa�amiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompa�amiento).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Acompa�amiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/Acompa�amientoAndes/rest/Acompa�amientos
     * @param Acompa�amiento - Acompa�amiento a actualizar. 
     * @return Json con el Acompa�amiento que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcompa�amiento(Acompa�amiento Acompa�amiento) {
		TM tm = new TM(getPath());
		try {
			tm.updateAcompa�amiento(Acompa�amiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompa�amiento).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Acompa�amiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/Acompa�amientoAndes/rest/Acompa�amientos
     * @param Acompa�amiento - Acompa�amiento a aliminar. 
     * @return Json con el Acompa�amiento que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompa�amiento(Acompa�amiento Acompa�amiento) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAcompa�amiento(Acompa�amiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompa�amiento).build();
	}

	
}
