package model.entity;

import java.time.LocalDate;

public class Vacina {

	private int idVacina;
	private String nome;
	private String paisOrigem;
	private Pessoa pesquisadorResponsavel;
	private int estagio;
	private LocalDate dataInicioPesquisa;

	public Vacina(int idVacina, String nome, String paisOrigem, Pessoa pesquisadorResponsavel, int estagio,
			LocalDate dataInicioPesquisa) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Vacina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getEstagio() {
		return estagio;
	}

	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

}
