package br.com.escola.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.escola.factory.ConnectionFactory;
import br.com.escola.model.Aluno;
import br.com.escola.model.Escola;

public class EscolaDAO {
	
	public void save(Escola escola) {
		String sql = "INSERT INTO escola(nome, fk_endereco) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, escola.getNome());
			pstm.setInt(2, escola.getEndereco().getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	
	public List<Escola> getEscolass(){
		String sql = "SELECT * FROM escola";
		
		List<Escola> escolas = new ArrayList<Escola>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Escola escola =new Escola();
				
				escola.setId(rset.getInt("id"));
				
				escola.setNome(rset.getString("nome"));
				//Recuperar a idade
				//escola.setEndereco(rset.get);
				
				escolas.add(escola);
				
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
		
			return escolas;
	
	}

}
