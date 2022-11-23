package factory;

import java.sql.*;

/**
 *
 * @author lucsa
 */
public class Conector {

    //declararar variavel q armazena conexao
    private static Connection con;

    //metodo q faz conexao
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/"; //caminho do banco
            String banco = "cantinapp";
            String usuario = "root";
            String senha = "";

            con = DriverManager.getConnection(url + banco, usuario, senha);
            System.out.println("Conectado com sucesso no banco: "+banco);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
