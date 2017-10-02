/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Video
 * @author Monitores 2017-20
 */
public class Video {

	//// Atributos

	/**
	 * Id del video
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * Nombre del video
	 */
	@JsonProperty(value="name")
	private String name;

	/**
	 * Duracion en minutos del video
	 */
	@JsonProperty(value="duration")
	private Integer duration;

	/**
	 * Metodo constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parametro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duracion en minutos del video.
	 */
	public Video(@JsonProperty(value="id")Long id, @JsonProperty(value="name")String name,@JsonProperty(value="duration")Integer duration) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
	}
	
	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Metodo getter del atributo id
	 * @return id del video
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo setter del atributo id <b>post: </b> El id del video ha sido
	 * cambiado con el valor que entra como parametro
	 * @param id - Id del video
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo getter del atributo name
	 * @return nombre del video
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo setter del atributo name <b>post: </b> El nombre del video ha sido
	 * cambiado con el valor que entra como parametro
	 * @param name - Id del video
	 */
	public void setName(String name) {
		this.name = name;
	}



}
