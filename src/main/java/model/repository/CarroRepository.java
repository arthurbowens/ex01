package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.Carro;
import model.entity.Montadora;
import model.seletor.CarroSeletor;

public class CarroRepository {

	public ArrayList<Carro> consultarComFiltro(CarroSeletor seletor) {
		ArrayList<Carro> carros = new ArrayList<>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT carro.*, montadora.* FROM concesionaria.carro "
				+ "INNER JOIN nomeMontadora.montadora ON carro.montadora_idMontadora = montadora.idMontadora";
		boolean primeiro = true;

		if (seletor.getNomeMontadora() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "upper(montadora.nome) LIKE UPPER('%" + seletor.getNomeMontadora() + "%')";
			primeiro = false;
		}

		if (seletor.getModelo() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "upper(carro.modelo) LIKE UPPER('%" + seletor.getModelo() + "%')";
			primeiro = false;
		}

		if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "carro.ano BETWEEN '" + seletor.getAnoInicial() + "' AND '" + seletor.getAnoFinal() + "'";
			primeiro = false;
		}

		if (seletor.getValorInicial() != null && seletor.getValorFinal() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "carro.valor BETWEEN '" + seletor.getValorInicial() + "' AND '" + seletor.getValorFinal() + "'";
			primeiro = false;
		}

		try {
			resultado = stmt.executeQuery(query);
			MontadoraRepository montadoraRepository = new MontadoraRepository();

			while (resultado.next()) {
				Carro carro = new Carro();
				carro.setId(resultado.getInt("idCarro"));
				carro.setModelo(resultado.getString("modelo"));
				carro.setPlaca(resultado.getString("placa"));
				carro.setAno(resultado.getInt("ano"));
				carro.setValor(resultado.getDouble("valor"));

				int montadoraId = resultado.getInt("idMontadora");
				Montadora montadora = montadoraRepository.consultarPorId(montadoraId);
				carro.setMontadora(montadora);

				carros.add(carro);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar com filtros");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return carros;
	}
}