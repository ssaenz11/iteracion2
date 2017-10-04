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
import vos.Acompañamiento;
import vos.AdministradorRestaurante;
import vos.Cliente;
import vos.Restaurante;

@Path("AdministradorRestaurante")
public class AdministradorRestauranteService 
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
	 * Metodo que expone servicio REST usando GET que da todos los AdministradorRestaurantes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes
	 * @return Json con todos los AdministradorRestaurantes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAdministradorRestaurantes() {
		TM tm = new TM(getPath());
		List<AdministradorRestaurante> AdministradorRestaurantes;
		try {
			AdministradorRestaurantes = tm.darAdministradorRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(AdministradorRestaurantes).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el AdministradorRestaurante con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes/<<id>>" para la busqueda"
     * @param name - Nombre del AdministradorRestaurante a buscar que entra en la URL como parametro 
     * @return Json con el/los AdministradorRestaurantes encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAdministradorRestaurante( @PathParam( "id" ) Long id )
	{
		TM tm = new TM( getPath( ) );
		try
		{
			AdministradorRestaurante v = tm.buscarAdministradorRestaurantePorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

//    /**
//     * Metodo que expone servicio REST usando GET que busca el AdministradorRestaurante con el nombre que entra como parametro
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes/nombre/nombre?nombre=<<nombre>>" para la busqueda"
//     * @param name - Nombre del AdministradorRestaurante a buscar que entra en la URL como parametro 
//     * @return Json con el/los AdministradorRestaurantes encontrados con el nombre que entra como parametro o json con 
//     * el error que se produjo
//     */
//	@GET
//	@Path( "{nombre}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response getAdministradorRestauranteName( @QueryParam("nombre") String name) {
//		TM tm = new TM(getPath());
//		List<AdministradorRestaurante> AdministradorRestaurantes;
//		try {
//			if (name == null || name.length() == 0)
//				throw new Exception("Nombre del AdministradorRestaurante no valido");
//			AdministradorRestaurantes = tm.buscarAdministradorRestaurantesPorName(name);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(AdministradorRestaurantes).build();
//	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el AdministradorRestaurante que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes/AdministradorRestaurante
     * @param AdministradorRestaurante - AdministradorRestaurante a agregar
     * @return Json con el AdministradorRestaurante que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) {
		TM tm = new TM(getPath());
		try {
			tm.addAdministradorRestaurante(AdministradorRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(AdministradorRestaurante).build();
	}
	
//    /**
//     * Metodo que expone servicio REST usando POST que agrega los AdministradorRestaurantes que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes/varios
//     * @param AdministradorRestaurantes - AdministradorRestaurantes a agregar. 
//     * @return Json con el AdministradorRestaurante que agrego o Json con el error que se produjo
//     */
//	@POST
//	@Path("/varios")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addAdministradorRestaurante(List<AdministradorRestaurante> AdministradorRestaurantes) {
//		TM tm = new TM(getPath());
//		try {
//			tm.addAdministradorRestaurantes(AdministradorRestaurantes);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(AdministradorRestaurantes).build();
//	}
	
//    /**
//     * Metodo que expone servicio REST usando PUT que actualiza el AdministradorRestaurante que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes
//     * @param AdministradorRestaurante - AdministradorRestaurante a actualizar. 
//     * @return Json con el AdministradorRestaurante que actualizo o Json con el error que se produjo
//     */
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) {
//		TM tm = new TM(getPath());
//		try {
//			tm.updateAdministradorRestaurante(AdministradorRestaurante);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(AdministradorRestaurante).build();
//	}
//	
//    /**
//     * Metodo que expone servicio REST usando DELETE que elimina el AdministradorRestaurante que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes
//     * @param AdministradorRestaurante - AdministradorRestaurante a aliminar. 
//     * @return Json con el AdministradorRestaurante que elimino o Json con el error que se produjo
//     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAdministradorRestaurante(AdministradorRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(AdministradorRestaurante).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Restaurante")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRestaurantes() {
		TM tm = new TM(getPath());
		List<Restaurante> restaurante;
		try {
			restaurante = tm.darRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}


    

    /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param restaurante - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Restaurante")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRestaurante(Restaurante restaurante) {
		TM tm = new TM(getPath());
		try {
			tm.addRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
    
   
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param restaurante - Cliente a aliminar. 
     * @return Json con el Cliente que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Restaurante")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRestaurante(Restaurante restaurante) {
		TM tm = new TM(getPath());
		try {
			tm.deleteRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
	
	
	
}
