package service;

import exception.CarrosException;
import model.entity.Montadora;
import model.repository.MontadoraRepository;

public class MontadoraService {

	private MontadoraRepository repository = new MontadoraRepository();

	public Montadora salvar(Montadora novaMontadora) throws CarrosException {
		validarCamposObrigatorios(novaMontadora);
		return repository.salvar(novaMontadora);

	}

	private void validarCamposObrigatorios(Montadora montadora) throws CarrosException {
		StringBuilder mensagemErro = new StringBuilder();

		if (montadora.getNome() == null || montadora.getNome().isEmpty()) {
			mensagemErro.append("Nome da montadora é obrigatório.\n");
		}

		if (montadora.getNomePais() == null || montadora.getNomePais().isEmpty()) {
			mensagemErro.append("Nome do país é obrigatório.\n");
		}

		if (montadora.getNomePresidente() == null || montadora.getNomePresidente().isEmpty()) {
			mensagemErro.append("Nome do presidente é obrigatório.\n");
		}

		if (montadora.getDataFundacao() == null) {
			mensagemErro.append("Data de fundação é obrigatória.\n");
		}

		if (mensagemErro.length() > 0) {
			throw new CarrosException("Campos obrigatórios não informados:\n" + mensagemErro.toString());
		}
	}
	
	public Montadora consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
}
