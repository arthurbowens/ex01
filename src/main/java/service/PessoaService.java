package service;

import java.util.List;

import exception.ControleVacinasException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {

	
private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa){
		return repository.salvar(novaPessoa);
	}

	public boolean excluir(int id) {
		//TODO pode excluir Pessoa que já possui partidas?
		return repository.excluir(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}
	
	@SuppressWarnings("unused")
	private void validarCpf(Pessoa novaPessoa) throws ControleVacinasException {
		if(repository.cpfJaCadastrado(novaPessoa.getCpf())) {
			throw new ControleVacinasException("CPF " + novaPessoa.getCpf() + " já cadastrado "); 
		}
	}

	
	
}
