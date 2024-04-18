package model.entity;

import java.time.LocalDate;

public class Vacina {
	
	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_MASSA = 3;
	
	private int idVacina;
	private String nome;
	private Pais paisOrigem;
	private Pessoa pesquisadorResponsavel;
	private int estagio;
	private LocalDate dataInicioPesquisa;
	private double media;

	public Vacina(int idVacina, String nome, Pais paisOrigem, Pessoa pesquisadorResponsavel, int estagio,
			LocalDate dataInicioPesquisa, double media) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.media = media;
	}

	public Vacina() {
		
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

	public Pais getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(Pais paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public int getEstagio() {
		return estagio;
	}

	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}
}
