/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private Connection connection;

   //Conexao banco
    public TarefaDAO() throws SQLException {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "utfpr";
            this.connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }

    // Método para inserir tarefa
    public void inserirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefa (nome, datahora, concluida, idcategoria) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
           
            if (tarefa.getNome() == null || tarefa.getNome().trim().isEmpty()) {
                stmt.setNull(1, Types.VARCHAR);
            } else {
                stmt.setString(1, tarefa.getNome().trim());
            }

            stmt.setDate(2, tarefa.getDatahora());
            stmt.setBoolean(3, tarefa.isConcluida()); 
            stmt.setInt(4, tarefa.getIdcategoria());

            stmt.executeUpdate();

           
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tarefa.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    
    public Tarefa buscarTarefaPorNome(String nometarefa) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nometarefa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tarefa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("datahora"),
                            rs.getBoolean("concluida"),
                            rs.getInt("idcategoria")
                    );
                }
            }
        }
        return null;
    }

    public List<Tarefa> buscarTarefasPorData(Date datahora) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE datahora = ?";
        List<Tarefa> tarefas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, datahora);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("datahora"),
                            rs.getBoolean("concluida"), 
                            rs.getInt("idcategoria")
                    );
                    tarefas.add(tarefa);
                }
            }
        }
        return tarefas;
    }

    public Tarefa buscarTarefaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tarefa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("datahora"),
                            rs.getBoolean("concluida"),
                            rs.getInt("idcategoria")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefa SET nome = ?, datahora = ?, idcategoria = ?, concluida = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (tarefa.getNome() == null || tarefa.getNome().trim().isEmpty()) {
                stmt.setNull(1, Types.VARCHAR);
            } else {
                stmt.setString(1, tarefa.getNome().trim());
            }

            stmt.setDate(2, tarefa.getDatahora());
            stmt.setInt(3, tarefa.getIdcategoria());
            stmt.setBoolean(4, tarefa.isConcluida());
            stmt.setInt(5, tarefa.getId());

            stmt.executeUpdate();
        }
    }

    public List<Tarefa> listarTodasTarefas() throws SQLException {
        String sql = "SELECT * FROM tarefa ORDER BY datahora, nome";
        List<Tarefa> tarefas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("datahora"),
                        rs.getBoolean("concluida"),
                        rs.getInt("idcategoria")
                );
                tarefas.add(tarefa);
            }
        }
        return tarefas;
    }

    public void excluirTarefa(int id) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Tarefa> listarTarefasPorCategoria(int idCategoria) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE idcategoria = ? ORDER BY datahora, nome";
        List<Tarefa> tarefas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("datahora"),
                            rs.getBoolean("concluida"),
                            rs.getInt("idcategoria")
                    );
                    tarefas.add(tarefa);
                }
            }
        }
        return tarefas;
    }
    
    public void marcarComoConcluida(int id) throws SQLException {
        String sql = "UPDATE tarefa SET concluida = true WHERE id = ?";
   
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
