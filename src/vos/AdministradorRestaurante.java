package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class AdministradorRestaurante 
{

////Atributos

	


	/**
	 * Nombre del Cliente
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Correo  del Cliente
	 */
	@JsonProperty(value="correo")
	private String correo;
	
	/**
	 * Cedula  del Cliente
	 */
	@JsonProperty(value="cedula")
	private int  cedula;
	
	
	/**
	 * Restaurante asociado  del Cliente
	 */
	@JsonProperty(value="restaurante")
	private int  restaurante;

	

	/**
	 * Metodo constructor de la clase Cliente
	 * <b>post: </b> Crea el cliente con los valores que entran como parametro
	 * @param id - Id del Cliente.
	 * @param name - Nombre del Cliente. name != null
	 * @param correo - correo del Cliente. name != null
	 * @param cedula - cedula del Cliente. name != null
	 */
	
	public AdministradorRestaurante(@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cedula")int cedula,
			@JsonProperty(value="restaurante")int restaurante,
			@JsonProperty(value="correo")String correo)
	{
		super();
		
		this.restaurante  = restaurante;
		this.nombre= nombre;
		this.correo= correo;
		this.cedula= cedula;
	}






	public int getRestaurante() {
		return restaurante;
	}






	public void setRestaurante(int restaurante) {
		this.restaurante = restaurante;
	}






	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getCorreo() {
		return correo;
	}




	public void setCorreo(String correo) {
		this.correo = correo;
	}




	public int getCedula() {
		return cedula;
	}




	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
}
