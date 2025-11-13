/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Categoria;
import java.sql.*;

/**
 import model.Conta;
import java.sql.*;

public class ContaDAO {

    private Connection connection;

    public ContaDAO() throws SQLException {
        // Estabelecendo conexão com o banco de dados
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "564676";
            this.connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }

    // Método para adicionar uma conta
    public void adicionarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO conta (saldo, id_usuario) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    //Método que retorna quantidade de registros de contas
    public int contarContas() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM conta";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    // Método para buscar uma conta pela posição
    public Conta buscarContaPorPos(int posicao) throws SQLException {
        String sql = "SELECT id, saldo, id_usuario FROM conta ORDER BY id LIMIT 1 OFFSET ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, posicao - 1);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                            rs.getInt("id"),
                            rs.getDouble("saldo"),
                            rs.getInt("id_usuario")
                    );
                }
            }
        }
        return null;
    }

    // Método para buscar uma conta por ID
    public Conta buscarContaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM conta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Conta(
                        rs.getInt("id"),
                        rs.getDouble("saldo"),
                        rs.getInt("id_usuario")
                );
            }
        }
        return null;
    }

    // Método para atualizar o saldo de uma conta
    public void atualizarSaldo(int id, double novoSaldo) throws SQLException {
        String sql = "UPDATE conta SET saldo = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, novoSaldo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma conta
    public void excluirConta(int id) throws SQLException {
        String sql = "DELETE FROM conta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para excluir todas as contas de um determinado usuário
    public void excluirContasUsuario(int id) throws SQLException {
        String sql = "DELETE FROM conta WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para fechar a conexão
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

 *
 * @author Aluno
 */
public class CategoriaDao {
    private Connection connection;
    
    public CategoriaDao() throws SQLException {
        // Estabelecendo conexão com o banco de dados
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "utfpr";
            this.connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }
// Método para adicicionar uma categoria
    public void adicionarCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome, id) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }
    //Método para remover uma categoria
    public void removerCategoria(int id) throws SQLException{
        String sql = "DELETE FROM catagoria where id =?  ";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para atualizar o nome de uma categoria
    public void atualizarCategoria(int id, String nome) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
    // Método para buscar uma conta por ID
    public Categoria buscarCategoriaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM conta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }
        }
        return null;
    }
    
    
// Método para fechar a conexão
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    
    
}
