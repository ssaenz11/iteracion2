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
import vos.Bebida;

@Path("Bebida")
public class BebidaService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Bebidas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
	 * @return Json con todos los Bebidas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBebidas() {
		TM tm = new TM(getPath());
		List<Bebida> Bebidas;
		try {
			Bebidas = tm.darBebidas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebidas).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Bebida con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Bebida a buscar que entra en la URL como parametro 
     * @return Json con el/los Bebidas encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBebidaName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Bebida Bebidas = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Bebida no valido");
			
			Bebidas = tm.buscarBebidaPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebidas).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Bebida que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas/Bebida
     * @param Bebida - Bebida a agregar
     * @return Json con el Bebida que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebida(Bebida Bebida) {
		TM tm = new TM(getPath());
		try {
			tm.addBebida(Bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebida).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Bebida que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
     * @param Bebida - Bebida a actualizar. 
     * @return Json con el Bebida que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBebida(Bebida Bebida) {
		TM tm = new TM(getPath());
		try {
			tm.updateBebida(Bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebida).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Bebida que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
     * @param Bebida - Bebida a aliminar. 
     * @return Json con el Bebida que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBebida(Bebida Bebida) {
		TM tm = new TM(getPath());
		try {
			tm.deleteBebida(Bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebida).build();
	}

	
}
