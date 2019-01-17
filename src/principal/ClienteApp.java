package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteApp {
	
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
	
	public static void inserir(long cpf, String nome, String email) throws SQLException {
		String sql = "INSERT INTO cliente values ("+cpf+",'"+nome+"','"+email+"')";
		Statement statement = conexao.createStatement();
		statement.executeQuery(sql);
		conexao.commit();
	}
	
	public static void consultar(long cpf) throws SQLException {
		String sql = "SELECT * from cliente where cpf = "+cpf;
		Statement statement = conexao.createStatement();
		ResultSet tupla = statement.executeQuery(sql);
		while(tupla.next()) {
//			JOptionPane.showMessageDialog(null, "CPF: "+tuplas.getInt(1)+" NOME: "+tuplas.getString(2)+" E-MAIL: "+tuplas.getString(3));
			System.out.println(tupla.getString(2));
		}
		conexao.commit();
	}
	
	public static void todos() throws SQLException {
		String sql = "SELECT * from cliente";
		Statement statement = conexao.createStatement();
		ResultSet tupla = statement.executeQuery(sql);
		while(tupla.next()) {
//			JOptionPane.showMessageDialog(null, "CPF: "+tuplas.getInt(1)+" NOME: "+tuplas.getString(2)+" E-MAIL: "+tuplas.getString(3));
			System.out.println(tupla.getString(2));	
		}
		System.out.println("Numero de clientes cadastrados é "+tupla.getRow());
		conexao.commit();
	}
	
	public static void alterar(long cpf, String nome, String email) throws SQLException {
		String sql = "UPDATE cliente set nome = '"+nome+"', email = '"+email+"' where cpf = "+cpf;
		Statement statement = conexao.createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
	}
	
	public static void excluir(long cpf) throws SQLException {
		String sql = "DELETE FROM cliente where cpf = "+cpf;
		Statement statement = conexao.createStatement();
		statement.executeUpdate(sql);
		conexao.commit();
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		connectar();
		inserir(100, "Alfredi", "alfredo@email.com");
//		consultar(100);
//		todos();
//		alterar(100, "Alfredinnn", "alfredinn@email.com");
//		excluir(100);
		desconnectar();

	}

}
