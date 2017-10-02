package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	////Atributos////
	/**
	 * nombre del usuario
	 */
	@JsonProperty(value="nombre")	
	private String nombre;
	
	@JsonProperty(value="identificacion")
	private Long identificacion;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="rol")
	private String rol;

	/**
	 * 
	 * @param nombre
	 * @param identificacion
	 * @param correo
	 * @param rol
	 */
	public Usuario(@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="identificacion")Long identificacion,
			@JsonProperty(value="correo")String correo,
			@JsonProperty(value="rol")String rol) 
	{
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.correo = correo;
		this.rol = rol;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the identificacion
	 */
	public Long getIdentificacion() {
		return identificacion;
	}


	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}


	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}


	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}


	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}


	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	
}
