package service;

import java.util.List;

import exception.ControleVacinasException;
import model.entity.Vacina;
import model.entity.Vacinacao;
import model.repository.VacinaRepository;

public class VacinaService {

	private VacinaRepository repository = new VacinaRepository();

	public Vacina salvar(Vacina novaVacina) {
		return repository.salvar(novaVacina);
	}

	public boolean atualizar(Vacina vacinaEditada) {
		return repository.alterar(vacinaEditada);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Vacina> consultarTodas() {
		return repository.consultarTodos();
	}

	// 1-b
	public boolean vacinaFoiAplicada(int idVacina) throws ControleVacinasException {
		if (repository.vacinaFoiAplicada(idVacina)) {
			System.out.println("Não é possivel excluir vacina pois ja foi aplicada por uma pessoa");
		}
		return repository.vacinaFoiAplicada(idVacina);
	}

	public void aplicarNovaVacina(int idVacina, double valorAplicacao) {

		repository.atualizarMediaVacina(idVacina, valorAplicacao);
	}
	
	public void atualizarMediaAplicacoesVacina(int idVacina, double valorAplicacao) {
		
		repository.atualizarMediaAplicacoesVacina(idVacina, valorAplicacao);
	}

	
}
