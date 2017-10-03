package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaCliente 
{

	
////Atributos

	/**
	 * Id del producto
	 */
	@JsonProperty(value="idCliente")
	private int  idCliente;

	/**
	 * id_restaurante del producto
	 */
	@JsonProperty(value="idPedido")
	private Long idPedido;
	
	/**
	 * cantidad del producto
	 */
	@JsonProperty(value="cantidad")
	private int cantidad;
	
	
	
	
	
	public PreferenciaCliente(@JsonProperty(value="idCliente")int id,
			@JsonProperty(value="idPedido")Long idPedido,
			@JsonProperty(value="cantidad")int cantidad)
	{
		this.idCliente = id;
		this.idPedido = idPedido;
		this.cantidad = cantidad;
	}

	

	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public Long getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
	
}
