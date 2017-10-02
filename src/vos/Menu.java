package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu
{


	//// Atributos

	/**
	 * Id del menú
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * id_entrada del menú
	 */
	@JsonProperty(value="id_entrada")
	private Long id_entrada;

	/**
	 * id_postre del menú
	 */
	@JsonProperty(value="id_postre")
	private Long id_postre;

	/**
	 * id_bebida del menú
	 */
	@JsonProperty(value="id_bebida")
	private Long id_bebida;


	/**
	 * id_plato_fuerte del menú
	 */
	@JsonProperty(value="id_plato_fuerte")
	private Long id_plato_fuerte;

	/**
	 * valor del menú
	 */
	@JsonProperty(value="valor")
	private double valor;

	public Menu(@JsonProperty(value="id")Long id,
			@JsonProperty(value="id_bebida")Long id_bebida,
			@JsonProperty(value="id_entrada")Long id_entrada,
			@JsonProperty(value="id_plato_fuerte")Long id_plato_fuerte,
			@JsonProperty(value="id_postre")Long id_postre,
			@JsonProperty(value="valor")double valor)
	{
		this.id = id;
		this.id_bebida = id_bebida;
		this.id_entrada = id_entrada;
		this.id_plato_fuerte = id_plato_fuerte;
		this.id_postre = id_postre;
		this.valor = valor;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_entrada() {
		return id_entrada;
	}

	public void setId_entrada(Long id_entrada) {
		this.id_entrada = id_entrada;
	}

	public Long getId_postre() {
		return id_postre;
	}

	public void setId_postre(Long id_postre) {
		this.id_postre = id_postre;
	}

	public Long getId_bebida() {
		return id_bebida;
	}

	public void setId_bebida(Long id_bebida) {
		this.id_bebida = id_bebida;
	}

	public Long getId_plato_fuerte() {
		return id_plato_fuerte;
	}

	public void setId_plato_fuerte(Long id_plato_fuerte) {
		this.id_plato_fuerte = id_plato_fuerte;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


}
