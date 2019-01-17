package daoVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClienteDAO {
	
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "curso";
	static String senha = "123";
	static Connection conexao;
	
	public static void connectar() throws SQLException{
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}
	
	public static void desconnectar() throws SQLException{
		conexao.close();
	}
	
	public static List<Cliente> todos() throws SQLException {
		String sql = "SELECT * from cliente";
		Statement statement = conexao.createStatement();
		ResultSet tupla = statement.executeQuery(sql);
		List<Cliente> clientes = new ArrayList<>();
		while(tupla.next()) {
			clientes.add(new Cliente(tupla.getLong(1), tupla.getString(2), tupla.getString(3)));
		}
		
		System.out.println("Numero de clientes cadastrados é "+clientes.size());
		return clientes;
	}
	
	public static Cliente get(long cpf) throws SQLException {
		String sql = "SELECT * from cliente where cpf = "+cpf;
		Statement statement = conexao.createStatement();
		ResultSet tupla = statement.executeQuery(sql);
		Cliente cliente = null;
		while(tupla.next()) {
			cliente = new Cliente(tupla.getLong(1), tupla.getString(2), tupla.getString(3));
		}
		System.out.println(cliente.toString());
		return cliente;
	}
	
	public static Cliente cadastrar(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente values ("+cliente.getCpf()+",'"+cliente.getNome()+"','"+cliente.getEmail()+"')";
		Statement statement = conexao.createStatement();
		statement.executeQuery(sql);
		conexao.commit();
		return cliente;
	}
	
	public static Cliente editar(Cliente cliente) throws SQLException {
		String sql = "UPDATE cliente set nome = '"+cliente.getNome()+"', email = '"+cliente.getEmail()+"' where cpf = "+cliente.getCpf();
		Statement statement = conexao.createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
		return cliente;
	}
	
	public static void excluir(long cpf) throws SQLException {
		String sql = "DELETE FROM cliente where cpf = "+cpf;
		Statement statement = conexao.createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
	}
	
	public static void main(String[] args) throws SQLException {
		connectar();
//		todos();
		get(100);
		desconnectar();
	}

}
