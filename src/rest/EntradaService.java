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
import vos.Entrada;

@Path("Entrada")
public class EntradaService {

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
	 * Metodo que expone servicio REST usando GET que da todos los Entradas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
	 * @return Json con todos los Entradas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEntradas() {
		TM tm = new TM(getPath());
		List<Entrada> Entradas;
		try {
			Entradas = tm.darEntradas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entradas).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Entrada con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Entrada a buscar que entra en la URL como parametro 
     * @return Json con el/los Entradas encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getEntradaName( @PathParam("nombre") String name) {
		TM tm = new TM(getPath());
		Entrada Entradas = null;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Entrada no valido");
			
			Entradas = tm.buscarEntradaPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entradas).build();
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Entrada que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas/Entrada
     * @param Entrada - Entrada a agregar
     * @return Json con el Entrada que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntrada(Entrada Entrada) {
		TM tm = new TM(getPath());
		try {
			tm.addEntrada(Entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entrada).build();
	}
	
    
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Entrada que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
     * @param Entrada - Entrada a actualizar. 
     * @return Json con el Entrada que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntrada(Entrada Entrada) {
		TM tm = new TM(getPath());
		try {
			tm.updateEntrada(Entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entrada).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Entrada que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
     * @param Entrada - Entrada a aliminar. 
     * @return Json con el Entrada que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEntrada(Entrada Entrada) {
		TM tm = new TM(getPath());
		try {
			tm.deleteEntrada(Entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entrada).build();
	}

	
	
}
