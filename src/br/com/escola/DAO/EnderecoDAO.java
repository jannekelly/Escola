package br.com.escola.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.escola.factory.ConnectionFactory;
import br.com.escola.model.Aluno;
import br.com.escola.model.Endereco;

public class EnderecoDAO {

	public void save(Endereco endereco) {
		String sql = "INSERT INTO aluno(logadouro, bairro, cidade, estado, complemento) VALUES (?, ?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, endereco.getLogadouro());
			pstm.setString(2, endereco.getBairro());
			pstm.setString(3,endereco.getCidade());
			pstm.setString(4, endereco.getEstado());
			pstm.setString(5, endereco.getComplemento());
			
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
	
	public List<Endereco> getEnderecos(){
		String sql = "SELECT * FROM endereco";
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Endereco endereco = new Endereco();
				
				endereco.setId(rset.getInt("id"));
				
				endereco.setLogadouro(rset.getString("logadouro"));
				//Recuperar a idade
				endereco.setBairro(rset.getString("bairro"));
				endereco.setCidade(rset.getString("cidade"));
				endereco.setEstado(rset.getString("estado"));
				endereco.setComplemento(rset.getString("complemento"));
				
				enderecos.add(endereco);
				
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
		
			return enderecos;
	
	}
}
