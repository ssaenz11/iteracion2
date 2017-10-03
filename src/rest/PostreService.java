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
import vos.Cliente;
import vos.Postre;

@Path("Postre")
public class PostreService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Postres de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
	 * @return Json con todos los Postres de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPostres() {
		TM tm = new TM(getPath());
		List<Postre> Postres;
		try {
			Postres = tm.darPostres();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postres).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Postre con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Postre a buscar que entra en la URL como parametro 
     * @return Json con el/los Postres encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPostreName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Postre Postres = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Postre no valido");
			
			Postres = tm.buscarPostrePorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postres).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Postre que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres/Postre
     * @param Postre - Postre a agregar
     * @return Json con el Postre que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPostre(Postre Postre) {
		TM tm = new TM(getPath());
		try {
			tm.addPostre(Postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postre).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Postre que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
     * @param Postre - Postre a actualizar. 
     * @return Json con el Postre que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePostre(Postre Postre) {
		TM tm = new TM(getPath());
		try {
			tm.updatePostre(Postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postre).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Postre que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
     * @param Postre - Postre a aliminar. 
     * @return Json con el Postre que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePostre(Postre Postre) {
		TM tm = new TM(getPath());
		try {
			tm.deletePostre(Postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postre).build();
	}

	
}
