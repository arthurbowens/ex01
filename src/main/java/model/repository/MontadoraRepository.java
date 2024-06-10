package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Montadora;

public class MontadoraRepository implements BaseRepository<Montadora> {

	@Override
	public Montadora salvar(Montadora novaMontadora) {
		String sql = " INSERT INTO pessoa (nome, nomePais, nomePresidente, " + "		               dataFundacao) "
				+ " VALUES(?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaMontadora.getNome());
			stmt.setString(2, novaMontadora.getNomePais());
			stmt.setString(3, novaMontadora.getNomePresidente());
			stmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova montadora");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaMontadora;

	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Montadora entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Montadora consultarPorId(int idMontadora) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Montadora montadora = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM montadora WHERE id = " + idMontadora;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				montadora = new Montadora();
				montadora.setId(resultado.getInt("idMontadora"));
				montadora.setNome(resultado.getString("nome"));
				montadora.setNomePais("nomePais");
				montadora.setNomePresidente("nomePresidente");
				montadora.setDataFundacao(resultado.getDate("dataFundacao").toLocalDate());

			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar pessoa com o idMontadora: " + idMontadora);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return montadora;
	}

	@Override
	public ArrayList<Montadora> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
