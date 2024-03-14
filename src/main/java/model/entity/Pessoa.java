package model.entity;

import java.time.LocalDate;

public class Pessoa {

	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private String tipoDePessoa;

	public Pessoa(int id, String nome, LocalDate dataNascimento, String sexo, String cpf, String tipoPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoDePessoa = tipoPessoa;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
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

	public String gettipoDePessoa() {
		return tipoDePessoa;
	}

	public void settipoDePessoa(String tipoDePessoa) {
		this.tipoDePessoa = tipoDePessoa;
	}

	@Override
	public String toString() {
		return "id: " + id + ", nome: " + nome + ", dataNascimento: " + dataNascimento + ", sexo: " + sexo
				+ ", cpf: " + cpf + ", tipoPessoa: " + tipoDePessoa + "]";
	}


}
