/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import control.Banco;
import model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 * Implementa a camada de acesso a dados (DAO) para a entidade Tarefa.
 */
public class TarefaDAO {
    private final Connection connection;

    
    
    public TarefaDAO() throws SQLException {
        // Construtor: obtém a conexão através da classe Banco centralizada
        this.connection = Banco.getConnection();
    }

    /**
     * Salva uma nova Tarefa no banco de dados.
     */
    public void salvar(Tarefa tarefa) throws SQLException {
        // Assume id_tarefa é SERIAL e é gerado automaticamente
        String sql = "INSERT INTO tarefa (titulo, datahora, concluida, id_categoria) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tarefa.getNome()); // 'nome' mapeado para a coluna 'titulo'
            stmt.setTimestamp(2, new Timestamp(tarefa.getDatahora().getTime())); // Converte Date para Timestamp SQL
            stmt.setBoolean(3, tarefa.isConcluida());
            stmt.setInt(4, tarefa.getIdCategoria()); // Chave estrangeira
            
            stmt.executeUpdate();
            
            // Recupera o ID gerado pelo banco e seta no objeto Tarefa
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tarefa.setId(rs.getInt(1)); // Seta o ID gerado
                }
            }
        }
    }
    
    /**
     * Lista todas as Tarefas cadastradas no banco.
     */
    public List<Tarefa> listarTodas() throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        // Query para selecionar todos os campos da tabela Tarefa
        String sql = "SELECT id_tarefa, titulo, datahora, concluida, id_categoria FROM tarefa ORDER BY datahora";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Tarefa t = new Tarefa(rs.getInt("id"), rs.getString("nome"), rs.getDate("datahora"), rs.getBoolean("concluida"));
                t.setId(rs.getInt("id_tarefa")); 
                t.setNome(rs.getString("titulo")); 
                
                // Converte Timestamp do SQL de volta para java.util.Date
                Timestamp ts = rs.getTimestamp("datahora");
                if (ts != null) {
                    t.setDatahora(new java.util.Date(ts.getTime())); 
                }
                
                t.setConcluida(rs.getBoolean("concluida"));
                t.setIdCategoria(rs.getInt("id_categoria")); 
                
                tarefas.add(t);
            }
        }
        return tarefas;
    }
    
    public void atualizarTarefa(Tarefa tarefa) throws SQLException {
    String sql = "UPDATE tarefa SET nome = ?, "
               + "datahora = ?, "
               + "concluida = ? "
               + "WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, tarefa.getNome());
        
        // Converter java.util.Date para java.sql.Date
        java.util.Date dataUtil = tarefa.getDatahora();
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
        stmt.setDate(2, dataSql);
        
        stmt.setBoolean(3, tarefa.isConcluida());
        stmt.setInt(4, tarefa.getId()); 
        
        stmt.executeUpdate();
    }
    }
    
    public void removerTarefa(int id) throws SQLException{
        String sql = "DELETE FROM tarefa where id = ?"; 
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Tarefa buscarTarefaPorData(Date datahora) throws SQLException {
    String sql = "SELECT id, nome, datahora, concluida FROM tarefa WHERE datahora = ?"; 

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Converter java.util.Date para java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(datahora.getTime());
        stmt.setDate(1, sqlDate);
        
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Tarefa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDate("datahora"),
                rs.getBoolean("concluida")
            ); 
        }
    }
    return null;

    }

     public Tarefa buscarTarefaPorId(int id) throws SQLException {
        String sql = "SELECT id, nome FROM tarefa WHERE id = ?"; 

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

          if (rs.next()) {
            return new Tarefa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDate("datahora"),
                rs.getBoolean("concluida")
            ); 
            }
        }
        return null;
    }


    
    // *******************************************************************
    // FALTAM OS MÉTODOS DE MANUTENÇÃO (CRUD) CONFORME DIAGRAMA DE CLASSES:
    // public void atualizar(Tarefa tarefa) throws SQLException { ... } OK
    // public void excluir(int id) throws SQLException { ... } OK
    // public Tarefa buscarPorData(...) throws SQLException { ... } OK
    // public Tarefa buscarPorID(int id) throws SQLException { ... } OK
    // *******************************************************************

    /**
     * Fecha a conexão com o banco de dados.
     * @throws java.sql.SQLException
     */
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}