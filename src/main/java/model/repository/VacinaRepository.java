package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.Banco;
import model.repository.BaseRepository;
import model.repository.PaisRepository;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {
		String sql = " INSERT INTO vacina(id_pesquisador, nome, pais_origem, estagio_pesquisa, data_inicio_pesquisa) "
				+ " VALUES(?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setInt(1, novaVacina.getPesquisadorResponsavel().getId());
			stmt.setString(2, novaVacina.getNome());
			stmt.setObject(3, novaVacina.getPaisOrigem());
			stmt.setInt(4, novaVacina.getEstagio());
			stmt.setDate(5, Date.valueOf(novaVacina.getDataInicioPesquisa()));

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaVacina.setIdVacina(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Vacina vacinaEditada) {
		boolean alterou = false;
		String query = " UPDATE vacina "
				+ " SET id_pesquisador=?, nome=?, pais_origem=?, estagio_pesquisa=?, data_inicio_pesquisa=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, vacinaEditada.getPesquisadorResponsavel().getId());
			stmt.setString(2, vacinaEditada.getNome());
			stmt.setObject(3, vacinaEditada.getPaisOrigem());
			stmt.setInt(4, vacinaEditada.getEstagio());
			stmt.setDate(5, Date.valueOf(vacinaEditada.getDataInicioPesquisa()));

			stmt.setInt(6, vacinaEditada.getIdVacina());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Vacina vacina = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM vacina WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			if (resultado.next()) {
				vacina = new Vacina();
				vacina.setIdVacina(Integer.parseInt(resultado.getString("ID")));
				vacina.setNome(resultado.getString("NOME"));
				PaisRepository paisRepository = new PaisRepository();
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("idPais")));
				vacina.setEstagio(resultado.getInt("ESTAGIO_PESQUISA"));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("ID_PESQUISADOR"));
				vacina.setPesquisadorResponsavel(pesquisador);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar vacina com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM vacina";

		try {
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			while (resultado.next()) {
				Vacina vacina = new Vacina();
				vacina.setIdVacina(Integer.parseInt(resultado.getString("ID")));
				vacina.setNome(resultado.getString("NOME"));
				PaisRepository paisRepository = new PaisRepository();
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("idPais")));
				vacina.setEstagio(resultado.getInt("ESTAGIO_PESQUISA"));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("ID_PESQUISADOR"));
				vacina.setPesquisadorResponsavel(pesquisador);
				vacinas.add(vacina);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todas as vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}

	// 1-b
	public boolean vacinaFoiAplicada(int idVacina) {
		Connection conn = Banco.getConnection();
		String query = "SELECT COUNT(*) AS total FROM aplicacao_vacina WHERE id_vacina = ?";
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;
		boolean vacinaAplicada = false;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idVacina);
			resultado = pstmt.executeQuery();

			if (resultado.next()) {
				int totalAplicacoes = resultado.getInt("total");
				vacinaAplicada = totalAplicacoes > 0;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se a vacina foi aplicada: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return vacinaAplicada;
	}

	public void atualizarMediaVacina(int idVacina, double novoValorAplicacao) {
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultado = null;

		try {
			String query = "SELECT media_vacina, total_aplicacoes FROM vacina WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idVacina);
			resultado = pstmt.executeQuery();

			if (resultado.next()) {
				double mediaAtual = resultado.getDouble("media_vacina");
				int totalAplicacoes = resultado.getInt("total_aplicacoes");

				double novaMedia = (mediaAtual * totalAplicacoes + novoValorAplicacao) / (totalAplicacoes + 1);

				String queryAtualizacao = "UPDATE vacina SET media_vacina = ?, total_aplicacoes = ? WHERE id = ?";
				pstmt = conn.prepareStatement(queryAtualizacao);
				pstmt.setDouble(1, novaMedia);
				pstmt.setInt(2, totalAplicacoes + 1);
				pstmt.setInt(3, idVacina);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar a média da vacina: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

	}

	
	//mediaAplicacoes
	public void atualizarMediaAplicacoesVacina(int idVacina, double novaAplicacao) {
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultado = null;

		try {
			String query = "SELECT soma_aplicacoes_vacina, quantidade_aplicacoes_vacina FROM vacina WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idVacina);
			resultado = pstmt.executeQuery();

			if (resultado.next()) {
				double somaTotalAnterior = resultado.getDouble("soma_aplicacoes_vacina");
				int quantidadeTotalAnterior = resultado.getInt("quantidade_aplicacoes_vacina");

				double novaMedia = (somaTotalAnterior + novaAplicacao) / (quantidadeTotalAnterior + 1);

				String queryAtualizacao = "UPDATE vacina SET media_aplicacoes_vacina = ?, soma_aplicacoes_vacina = ?, quantidade_aplicacoes_vacina = ? WHERE id = ?";
				pstmt = conn.prepareStatement(queryAtualizacao);
				pstmt.setDouble(1, novaMedia);
				pstmt.setDouble(2, somaTotalAnterior + novaAplicacao);
				pstmt.setInt(3, quantidadeTotalAnterior + 1);
				pstmt.setInt(4, idVacina);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar a média das aplicações da vacina: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
	}
}

