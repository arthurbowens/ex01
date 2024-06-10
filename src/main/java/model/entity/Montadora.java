package model.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Montadora {

	private Integer idMontadora;
	private String nome;
	private String nomePais;
	private String nomePresidente;
	private LocalDate dataFundacao;

	public Montadora(Integer id, String nome, String nomePais, String nomePresidente, Date dataFundacao) {
		super();
		this.idMontadora = id;
		this.nome = nome;
		this.nomePais = nomePais;
		this.nomePresidente = nomePresidente;
	}

	public Montadora() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return idMontadora;
	}

	public void setId(Integer id) {
		this.idMontadora = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

}
