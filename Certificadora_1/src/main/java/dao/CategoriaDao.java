package dao;

import control.Banco; 
import model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    private final Connection connection;
    
    public CategoriaDao() throws SQLException {
        this.connection = Banco.getConnection();      
    }

    // Método para adicionar uma categoria
    public void adicionarCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome) VALUES (?)"; 

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
            
            // Recupera o ID gerado pelo banco
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1)); 
                }
            }
        }
    }
    
    // Método para remover uma categoria (Corrigido o typo 'catagoria')
    public void removerCategoria(int id) throws SQLException{
        String sql = "DELETE FROM categoria where id = ?"; 
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para atualizar o nome de uma categoria
    public void atualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }
    
    // Método para buscar uma categoria por ID (Corrigido o uso da tabela 'conta')
    public Categoria buscarCategoriaPorId(int id) throws SQLException {
        String sql = "SELECT id, nome FROM categoria WHERE id = ?"; 

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
    
    // Método para listar todas as categorias (Implementado)
    public List<Categoria> listarTodas() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome FROM categoria ORDER BY nome";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
                categorias.add(c);
            }
        }
        return categorias;
    }
    
    // Método para fechar a conexão
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}