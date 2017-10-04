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
import vos.Administrador;
import vos.AdministradorRestaurante;
import vos.Cliente;

@Path("Administrador")
public class AdministradorService 
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
	 * Metodo que expone servicio REST usando GET que da todos los Administradors de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors
	 * @return Json con todos los Administradors de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAdministradors() {
		TM tm = new TM(getPath());
		List<Administrador> Administradors;
		try {
			Administradors = tm.darAdministradors();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Administradors).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Administrador con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors/<<id>>" para la busqueda"
     * @param name - Nombre del Administrador a buscar que entra en la URL como parametro 
     * @return Json con el/los Administradors encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAdministrador( @PathParam( "id" ) Long id )
	{
		TM tm = new TM( getPath( ) );
		try
		{
			Administrador v = tm.buscarAdministradorPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

//    /**
//     * Metodo que expone servicio REST usando GET que busca el Administrador con el nombre que entra como parametro
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors/nombre/nombre?nombre=<<nombre>>" para la busqueda"
//     * @param name - Nombre del Administrador a buscar que entra en la URL como parametro 
//     * @return Json con el/los Administradors encontrados con el nombre que entra como parametro o json con 
//     * el error que se produjo
//     */
//	@GET
//	@Path( "{nombre}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response getAdministradorName( @QueryParam("nombre") String name) {
//		TM tm = new TM(getPath());
//		List<Administrador> Administradors;
//		try {
//			if (name == null || name.length() == 0)
//				throw new Exception("Nombre del Administrador no valido");
//			Administradors = tm.buscarAdministradorsPorName(name);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(Administradors).build();
//	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Administrador que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors/Administrador
     * @param Administrador - Administrador a agregar
     * @return Json con el Administrador que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdministrador(Administrador Administrador) {
		TM tm = new TM(getPath());
		try {
			tm.addAdministrador(Administrador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Administrador).build();
	}
	
//    /**
//     * Metodo que expone servicio REST usando POST que agrega los Administradors que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors/varios
//     * @param Administradors - Administradors a agregar. 
//     * @return Json con el Administrador que agrego o Json con el error que se produjo
//     */
//	@POST
//	@Path("/varios")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addAdministrador(List<Administrador> Administradors) {
//		TM tm = new TM(getPath());
//		try {
//			tm.addAdministradors(Administradors);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(Administradors).build();
//	}
	
//    /**
//     * Metodo que expone servicio REST usando PUT que actualiza el Administrador que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors
//     * @param Administrador - Administrador a actualizar. 
//     * @return Json con el Administrador que actualizo o Json con el error que se produjo
//     */
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateAdministrador(Administrador Administrador) {
//		TM tm = new TM(getPath());
//		try {
//			tm.updateAdministrador(Administrador);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(Administrador).build();
//	}
//	
//    /**
//     * Metodo que expone servicio REST usando DELETE que elimina el Administrador que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorAndes/rest/Administradors
//     * @param Administrador - Administrador a aliminar. 
//     * @return Json con el Administrador que elimino o Json con el error que se produjo
//     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAdministrador(Administrador Administrador) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAdministrador(Administrador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Administrador).build();
	}
	
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Clientes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientes() {
		TM tm = new TM(getPath());
		List<Cliente> Clientes;
		try {
			Clientes = tm.darClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Clientes).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando POST que agrega los Clientes que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/varios
     * @param Clientes - Clientes a agregar. 
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	 /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param Cliente - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente Cliente) {
		TM tm = new TM(getPath());
		try {
			tm.addCliente(Cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Cliente).build();
	}
	
	
	  /**
     * Metodo que expone servicio REST usando DELETE que elimina el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param Cliente - Cliente a aliminar. 
     * @return Json con el Cliente que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(Cliente Cliente) {
		TM tm = new TM(getPath());
		try {
			tm.deleteCliente(Cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Cliente).build();
	}
	
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los AdministradorRestaurantes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes
	 * @return Json con todos los AdministradorRestaurantes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/AdmiRestaurante")
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
     * Metodo que expone servicio REST usando POST que agrega el AdministradorRestaurante que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AdministradorRestauranteAndes/rest/AdministradorRestaurantes/AdministradorRestaurante
     * @param AdministradorRestaurante - AdministradorRestaurante a agregar
     * @return Json con el AdministradorRestaurante que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/AdmiRestaurante")
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
	
	@DELETE
	@Path("/AdmiRestaurante")
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

}
