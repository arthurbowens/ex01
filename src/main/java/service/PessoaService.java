package service;

import java.util.List;

import exception.ControleVacinasException;
import exception.VacinacaoException;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository repository = new PessoaRepository();

	public Pessoa salvar(Pessoa novaPessoa) throws VacinacaoException {
		validarCamposObrigatorios(novaPessoa);

		validarCpf(novaPessoa);

		return repository.salvar(novaPessoa);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	//1-a
	 public boolean excluirPessoaComValidacao(int id) throws ControleVacinasException {
	        if(repository.pessoaTemVacinacoes(id)) {
	            throw new ControleVacinasException("Não é possível excluir a pessoa, pois ela já recebeu pelo menos uma dose de vacina.");
	        }

	        return repository.excluir(id);
	    }

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}

	private void validarCpf(Pessoa novaPessoa) throws VacinacaoException {
		if (repository.cpfJaCadastrado(novaPessoa.getCpf())) {
			throw new VacinacaoException("CPF " + novaPessoa.getCpf() + " já cadastrado ");
		}
	}

	private void validarCamposObrigatorios(Pessoa p) throws VacinacaoException {
		String mensagemValidacao = "";
		if (p.getNome() == null || p.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (p.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if (p.getCpf() == null || p.getCpf().isEmpty() || p.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF";
		}
		if (p.getSexo() == ' ') {
			mensagemValidacao += " - informe o sexo";
		}
		if (p.gettipoDePessoa() < 1 || p.gettipoDePessoa() > 3) {
			mensagemValidacao += " - informe o tipo (entre 1 e 3)";
		}
		if (p.getPaisOrigem() == null) {
			mensagemValidacao += " - informe o país de origem";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new VacinacaoException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}
	
	
}
