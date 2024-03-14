package model.entity;

import java.time.LocalDate;

public class Vacinacao {

	private int idVacinacao;
	private int idPessoa;
	private Vacina vacina;
	private LocalDate data;
	private int avaliacao;

	public Vacinacao(int idVacinacao, int idPessoa, Vacina vacina, LocalDate data, int avaliacao) {
		super();
		this.idVacinacao = idVacinacao;
		this.idPessoa = idPessoa;
		this.vacina = vacina;
		this.data = data;
		this.avaliacao = avaliacao;
	}

	public Vacinacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdVacinacao() {
		return idVacinacao;
	}

	public void setIdVacinacao(int idVacinacao) {
		this.idVacinacao = idVacinacao;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

}
