package br.com.escola.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.escola.factory.ConnectionFactory;
import br.com.escola.model.Aluno;
import br.com.escola.model.Turma;

public class TurmaDAO {

	public void save(Turma turma) {
		String sql = "INSERT INTO aluno(nome, capacidade) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, turma.getNome());
			pstm.setInt(2, turma.getCapacidade());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	public List<Turma> getTurmas(){
		String sql = "SELECT * FROM turma";
		
		List<Turma> turmas = new ArrayList<Turma>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Turma turma = new Turma();
				turma.setId(rset.getInt("id"));
				
				turma.setNome(rset.getString("nome"));
				//Recuperar a idade
				turma.setCapacidade(rset.getInt("capacidade"));
				
				turmas.add(turma);
				
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
		
			return turmas;
	
	}
}
