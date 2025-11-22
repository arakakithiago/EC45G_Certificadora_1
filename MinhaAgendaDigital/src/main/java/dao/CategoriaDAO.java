// Nomes: 
// Gabriel de Souza Muniz - RA: 2475430
// Thiago Henrique Rodrigues Arakaki - RA: 2261286
package dao;

import model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO() throws SQLException {

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "utfpr";
            this.connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }

    public void adicionarCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Categoria buscarCategoriaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                            rs.getInt("id"),
                            rs.getString("nome")
                    );
                }
            }
        }
        return null;
    }

    public List<Categoria> listarTodasCategorias() throws SQLException {
        String sql = "SELECT * FROM categoria ORDER BY id";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    public void atualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirCategoria(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Categoria buscarCategoria() throws SQLException {
        String sql = "SELECT * FROM Categoria ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

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

    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
