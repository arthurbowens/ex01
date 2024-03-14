package service;

import java.util.List;

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
}
