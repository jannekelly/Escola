package br.com.escola.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.escola.factory.ConnectionFactory;
import br.com.escola.model.Aluno;

public class AlunoDAO {

	public void save(Aluno aluno) {
		String sql = "INSERT INTO aluno(nome, dataDeNascimento) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, aluno.getNome());
			pstm.setDate(2, (java.sql.Date) aluno.getDataDeNascimento());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					pstm.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Aluno> getAlunos(){
		String sql = "SELECT * FROM aluno";
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Aluno aluno = new Aluno();
				
				aluno.setId(rset.getInt("id"));
				
				aluno.setNome(rset.getString("nome"));
				//Recuperar a idade
				aluno.setDataDeNascimento(rset.getDate("dataDeNascimeto"));
				
				alunos.add(aluno);
				
			}
		}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
			return alunos;
	
	}
}
