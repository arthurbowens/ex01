package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.VacinacaoException;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.vacinacao.PaisRepository;

public class PessoaRepository implements BaseRepository<Pessoa> {

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO pessoa (nome, dataNascimento, sexo, cpf, tipoPessoa) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			
			preencherParametrosParaInsertOuUpdate(pstmt, novaPessoa);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaPessoa;
	}

	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Pessoa novaPessoa) throws SQLException {
		pstmt.setString(1, novaPessoa.getNome());
		pstmt.setDate(2, Date.valueOf(novaPessoa.getDataNascimento()));
		pstmt.setString(3, novaPessoa.getSexo() + "");
		pstmt.setString(4, novaPessoa.getCpf());
		pstmt.setInt(5, novaPessoa.gettipoDePessoa());
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE idpessoa = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;

	}
	
	@Override
	public Pessoa consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Pessoa pessoa = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				pessoa = new Pessoa();
				pessoa.setId(resultado.getInt("ID"));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setSexo(resultado.getString("SEXO").charAt(0));
				pessoa.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate()); 
				pessoa.settipoDePessoa(resultado.getInt("TIPO"));
				
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar pessoa com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}


	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				
				//TODO este bloco repete-se no consultarTodos().... refatorar!
				pessoa.setId(Integer.parseInt(resultado.getString("idpessoa")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate()); 
				pessoa.setSexo(resultado.getString("sexo").charAt(0));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.settipoDePessoa(resultado.getInt("tipo"));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;	
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id) FROM pessoa WHERE cpf = " + cpf;
		
		try {
			ResultSet resultado = stmt.executeQuery(query);
			cpfJaUtilizado = (resultado.getInt(1) > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}
		
		return cpfJaUtilizado;
	}
	
	
	//1-a
	public boolean pessoaTemVacinacoes(int idPessoa) {
	    boolean temVacinacoes = false;
	    Connection conn = Banco.getConnection();
	    Statement stmt = Banco.getStatement(conn);
	    ResultSet resultado = null;
	    String query = "SELECT COUNT(*) FROM vacinacao WHERE pessoa_id = " + idPessoa;
	    try {
	        resultado = stmt.executeQuery(query);
	        if (resultado.next()) {
	            int numVacinacoes = resultado.getInt(1);
	            temVacinacoes = numVacinacoes > 0;
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao verificar vacinações da pessoa");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeResultSet(resultado);
	        Banco.closeStatement(stmt);
	        Banco.closeConnection(conn);
	    }
	    return temVacinacoes;
	}
	
	@Override
	public boolean alterar(Pessoa entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	

}
