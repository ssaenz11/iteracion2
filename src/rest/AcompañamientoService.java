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
import vos.Acompañamiento;

@Path("Acompañamiento")
public class AcompañamientoService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Acompañamientos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos
	 * @return Json con todos los Acompañamientos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAcompañamientos() {
		TM tm = new TM(getPath());
		List<Acompañamiento> Acompañamientos;
		try {
			Acompañamientos = tm.darAcompañamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamientos).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Acompañamiento con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Acompañamiento a buscar que entra en la URL como parametro 
     * @return Json con el/los Acompañamientos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompañamientoName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Acompañamiento Acompañamientos = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Acompañamiento no valido");
			
			Acompañamientos = tm.buscarAcompañamientoPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamientos).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Acompañamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos/Acompañamiento
     * @param Acompañamiento - Acompañamiento a agregar
     * @return Json con el Acompañamiento que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompañamiento(Acompañamiento Acompañamiento) {
		TM tm = new TM(getPath());
		try {
			tm.addAcompañamiento(Acompañamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamiento).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Acompañamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos
     * @param Acompañamiento - Acompañamiento a actualizar. 
     * @return Json con el Acompañamiento que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcompañamiento(Acompañamiento Acompañamiento) {
		TM tm = new TM(getPath());
		try {
			tm.updateAcompañamiento(Acompañamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamiento).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Acompañamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos
     * @param Acompañamiento - Acompañamiento a aliminar. 
     * @return Json con el Acompañamiento que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompañamiento(Acompañamiento Acompañamiento) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAcompañamiento(Acompañamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamiento).build();
	}

	
}
