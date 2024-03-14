package model.entity;

import java.time.LocalDate;

public class AplicacaoVacina {

	private LocalDate dataAplicacao;
	private Pessoa pessoa;
	private int reacao;

	public AplicacaoVacina(LocalDate dataAplicacao, Pessoa pessoa, int reacao) {
		super();
		this.dataAplicacao = dataAplicacao;
		this.pessoa = pessoa;
		this.reacao = reacao;
	}

	public AplicacaoVacina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getReacao() {
		return reacao;
	}

	public void setReacao(int reacao) {
		this.reacao = reacao;
	}

}
