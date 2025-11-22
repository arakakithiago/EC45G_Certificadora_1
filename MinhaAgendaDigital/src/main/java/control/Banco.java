package control;

import dao.CategoriaDAO;
import dao.TarefaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Tarefa;

public class Banco {

    private static Banco bancoUnico;

    private final String nome;

    private Banco() {
        nome = "Minha agenda digital";
    }

    public static Banco getBanco() {
        if (bancoUnico == null) {
            bancoUnico = new Banco();
        }
        return bancoUnico;
    }

    public String getNome() {
        return nome;
    }

    public Tarefa getTarefaPorNome(String nome) throws SQLException {
        TarefaDAO d = null;
        try {
            d = new TarefaDAO();
            return d.buscarTarefaPorNome(nome);
        } catch (SQLException sqe) {
            JOptionPane.showMessageDialog(
                    null,
                    sqe.getLocalizedMessage(),
                    "ERRO",
                    0
            );
            return null;
        } finally {
            if (d != null) {
                d.fecharConexao();
            }
        }
    }

    private void closeDAO(Object dao) {
        if (dao != null) {
            try {
                if (dao instanceof TarefaDAO) {
                    ((TarefaDAO) dao).fecharConexao();
                } else if (dao instanceof CategoriaDAO) {
                    ((CategoriaDAO) dao).fecharConexao();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    private void showError(String mensagem) {
        JOptionPane.showMessageDialog(
                null,
                mensagem,
                "ERRO",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public boolean testarConexao() {
        try {
            TarefaDAO dao = new TarefaDAO();
            dao.fecharConexao();
            return true;
        } catch (SQLException e) {
            showError("Falha na conexão com o banco: " + e.getMessage());
            return false;
        }
    }

    public List<Tarefa> getTodasTarefas() {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            return dao.listarTodasTarefas();
        } catch (SQLException sqe) {
            showError("Erro ao listar tarefas: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

    public List<Tarefa> getTarefasPorCategoria(int idCategoria) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            return dao.listarTarefasPorCategoria(idCategoria);
        } catch (SQLException sqe) {
            showError("Erro ao buscar tarefas por categoria: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean inserirTarefa(Tarefa tarefa) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            dao.inserirTarefa(tarefa);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao inserir tarefa: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean atualizarTarefa(Tarefa tarefa) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            dao.atualizarTarefa(tarefa);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao atualizar tarefa: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean excluirTarefa(int id) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            dao.excluirTarefa(id);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao excluir tarefa: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean marcarTarefaConcluida(int id) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            dao.marcarComoConcluida(id);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao marcar tarefa como concluída: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public Categoria getCategoria() {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            return dao.buscarCategoria();
        } catch (SQLException sqe) {
            showError("Erro ao buscar categoria: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

    public Categoria getCategoriaPorId(int id) {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            return dao.buscarCategoriaPorId(id);
        } catch (SQLException sqe) {
            showError("Erro ao buscar categoria por ID: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

    public List<Categoria> getTodasCategorias() {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            return dao.listarTodasCategorias();
        } catch (SQLException sqe) {
            showError("Erro ao listar categorias: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean inserirCategoria(Categoria categoria) {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            dao.adicionarCategoria(categoria);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao inserir categoria: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean atualizarCategoria(Categoria categoria) {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            dao.atualizarCategoria(categoria);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao atualizar categoria: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public boolean excluirCategoria(int id) {
        CategoriaDAO dao = null;
        try {
            dao = new CategoriaDAO();
            dao.excluirCategoria(id);
            return true;
        } catch (SQLException sqe) {
            showError("Erro ao excluir categoria: " + sqe.getMessage());
            return false;
        } finally {
            closeDAO(dao);
        }
    }

    public Tarefa getTarefaPorId(int id) {
        TarefaDAO dao = null;
        try {
            dao = new TarefaDAO();
            return dao.buscarTarefaPorId(id);
        } catch (SQLException sqe) {
            showError("Erro ao buscar tarefa por ID: " + sqe.getMessage());
            return null;
        } finally {
            closeDAO(dao);
        }
    }

}
