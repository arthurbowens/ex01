package model.entity;

public class Carro {

	private Integer idCarro;
	private String modelo;
	private String placa;
	private Montadora montadora;
	private Integer ano;
	private Double valor;

	public Carro() {

	}

	public Carro(Integer id, String modelo, String placa, Montadora montadora, Integer ano, Double valor) {
		super();
		this.idCarro = id;
		this.modelo = modelo;
		this.placa = placa;
		this.montadora = montadora;
		this.ano = ano;
		this.valor = valor;
	}

	public Integer getId() {
		return idCarro;
	}

	public void setId(Integer id) {
		this.idCarro = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setPaisDaMarca(String string) {
		// TODO Auto-generated method stub
		
	}
}
