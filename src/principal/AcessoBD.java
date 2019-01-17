package principal;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AcessoBD {
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "curso";
	static String senha = "123";
	static Connection conexao;
	
	public static void connectar() throws SQLException{
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}
	
	public static void consultarCliente() throws SQLException {
		String SQL = "SELECT * FROM cliente";
		Statement statement = conexao.createStatement();
		ResultSet tuplas = statement.executeQuery(SQL);
		while(tuplas.next()) {
//			JOptionPane.showMessageDialog(null, "CPF: "+tuplas.getInt(1)+" NOME: "+tuplas.getString(2)+" E-MAIL: "+tuplas.getString(3));
			System.out.println(tuplas.getString(2));
		}
	}
	
	public static void mostrarMetaInfoBD() throws SQLException {
		DatabaseMetaData meta = conexao.getMetaData();
		String fabricanteBD = meta.getDatabaseProductName();
		String versaoBD = meta.getDatabaseProductVersion();
		JOptionPane.showMessageDialog(null, fabricanteBD+" ------- "+versaoBD);
	}
	

	public static void main(String[] args) throws SQLException {
		connectar();
		mostrarMetaInfoBD();
		consultarCliente();
		conexao.close();
	}

}
