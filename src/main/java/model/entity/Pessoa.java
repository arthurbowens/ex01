package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pessoa {

	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	private int tipoDePessoa;
	private Pais paisOrigem;
	private ArrayList<Vacinacao> vacinacoes;

	public Pessoa(int id, String nome, LocalDate dataNascimento, char sexo, String cpf, int tipoDePessoa,
			model.entity.Pais paisOrigem, ArrayList<model.entity.Vacinacao> vacinacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoDePessoa = tipoDePessoa;
		this.paisOrigem = paisOrigem;
		this.vacinacoes = vacinacoes;
	}

	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int gettipoDePessoa() {
		return tipoDePessoa;
	}

	public void settipoDePessoa(int tipoDePessoa) {
		this.tipoDePessoa = tipoDePessoa;
	}

	@Override
	public String toString() {
		return "id: " + id + ", nome: " + nome + ", dataNascimento: " + dataNascimento + ", sexo: " + sexo + ", cpf: "
				+ cpf + ", tipoPessoa: " + tipoDePessoa + "]";
	}

	public Pais getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(Pais paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public ArrayList<Vacinacao> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(ArrayList<Vacinacao> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}

}
