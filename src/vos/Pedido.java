package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	
	////Atributos////
	@JsonProperty(value="id")
	private Long id;
	/**
	 * fecha del pedido.
	 */
	@JsonProperty(value="fecha")
	private String fecha;
	
	
	
	/**
	 * id del cliente que hizo el pedido.
	 */
	@JsonProperty(value="id_cliente")
	private Long id_cliente;
	
	/**
	 * nombre del restaurante al que se le hizo el pedido.
	 */
	@JsonProperty(value="nombre_Rest")
	private String nombre_Rest;
	
	/**
	 * id del producto que se solicito en el pedido.
	 */
	@JsonProperty(value="id_prod")
	private Long id_prod;

	/**
	 * @param id
	 * @param fecha
	 * @param hora
	 * @param id_cliente
	 * @param nombre_Rest
	 * @param id_prod
	 */
	public Pedido(@JsonProperty(value="id")Long id,
			@JsonProperty(value="fecha")String fecha, 
			
			@JsonProperty(value="cliente")Long id_cliente, 
			@JsonProperty(value="nombre_Rest")String nombre_Rest,
			@JsonProperty(value="id_prod")Long id_prod) {
		this.id = id;
		this.fecha = fecha;
		
		this.id_cliente = id_cliente;
		this.nombre_Rest = nombre_Rest;
		this.id_prod = id_prod;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	

	/**
	 * @return the id_cliente
	 */
	public Long getId_cliente() {
		return id_cliente;
	}

	/**
	 * @param id_cliente the id_cliente to set
	 */
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	/**
	 * @return the nombre_Rest
	 */
	public String getNombre_Rest() {
		return nombre_Rest;
	}

	/**
	 * @param nombre_Rest the nombre_Rest to set
	 */
	public void setNombre_Rest(String nombre_Rest) {
		this.nombre_Rest = nombre_Rest;
	}

	/**
	 * @return the id_prod
	 */
	public Long getId_prod() {
		return id_prod;
	}

	/**
	 * @param id_prod the id_prod to set
	 */
	public void setId_prod(Long id_prod) {
		this.id_prod = id_prod;
	}
	
	
	
}
