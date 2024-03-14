package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.Vacina;

public class VacinaRepository  implements BaseRepository<Vacina>{

	@Override
	public Vacina salvar(Vacina novaVacina) {
		String query = "INSERT INTO vacina (nome, paisOrigem, pesquisadorResponsavel, estagio, dataInicioPesquisa) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			
			preencherParametrosParaInsertOuUpdate(pstmt, novaVacina);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaVacina.setIdVacina(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaVacina;
		
	}
	
	
	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Vacina novaVacina) throws SQLException {
		pstmt.setString(1, novaVacina.getNome());
		pstmt.setString(2, novaVacina.getPaisOrigem());
		pstmt.setObject(3, novaVacina.getPesquisadorResponsavel());
		pstmt.setInt(4, novaVacina.getEstagio());
		pstmt.setDate(2, Date.valueOf(novaVacina.getDataInicioPesquisa()));
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE idVacina = " + id;
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
	public boolean alterar(Vacina entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacina consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
