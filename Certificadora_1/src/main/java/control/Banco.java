package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

    // configs do banco
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "utfpr"; 

    // mttodo para obter uma nova conexao
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao conectar ao banco de dados!");
            System.err.println("Verifique se o PostgreSQL está rodando e se a senha está correta.");
            throw new SQLException("Erro de Conexão com o Banco de Dados", e); 
        }
    }
}