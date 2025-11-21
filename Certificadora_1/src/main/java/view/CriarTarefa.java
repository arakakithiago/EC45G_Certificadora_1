package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CriarTarefa extends javax.swing.JFrame { // Estendendo javax.swing.JFrame

    private Principal telaPrincipal;
   

    /**
     * Construtor para inicializar a tela de criação de tarefas.
     * @param principal Referência para a tela principal para recarregar a lista.
     */
    public CriarTarefa(Principal principal) {
        this.telaPrincipal = principal;
        
        // Esta linha é crucial e deve ser a primeira chamada de inicialização.
        // O NetBeans vai injetar o código de design aqui.
        initComponents(); 
        
        setTitle("Criar Nova Tarefa");
        setSize(400, 300); // Defina o tamanho inicial da sua janela
        this.setLocationRelativeTo(null); // Centraliza a janela
    }
    
    // Construtor vazio (opcional, mas útil para testes diretos)
    public CriarTarefa() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack(); // <-- Este é o método que estava dando erro. Ele DEVE existir.
    }
    
    public void fecharECarregarPrincipal() {
        this.dispose(); // Fecha esta janela
        if (this.telaPrincipal != null) {
            this.telaPrincipal.carregarTabelaTarefas(); // Recarrega os dados na tabela principal
        }
    }
    
    // Método main para teste (Opcional, mas útil)
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CriarTarefa().setVisible(true);
            }
        });
    }

  
}