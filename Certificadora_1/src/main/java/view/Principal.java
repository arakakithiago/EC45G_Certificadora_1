/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package view;   

import dao.TarefaDAO;
import model.Tarefa;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Principal extends javax.swing.JFrame {

    private TarefaDAO tarefaDAO;

    public Principal() {

        /*ajustando centralizacao*/
        initComponents();
        pack();
        this.setLocationRelativeTo(null);


        try {
            this.tarefaDAO = new TarefaDAO();
            carregarTabelaTarefas();
            // Adiciona listener de seleção à tabela
            jTable1.getSelectionModel().addListSelectionListener(e -> jTable1SelecaoLinhaAlterada());
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o DAO de Tarefa: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            // System.exit(1); // Opcional: fechar se a conexão for crítica
        }
    }

    private void carregarTabelaTarefas() {
    carregarTabelaTarefas(null, null, null, null); // Chama a sobrecarga sem filtros
}

// Método para carregar os dados na jTable1 com filtros
private void carregarTabelaTarefas(String nome, Integer idCategoria, String data, Boolean concluida) {
    // ... (restante do código do modelo da tabela) ...

    try {
        // AQUI ESTÁ A MUDANÇA:
        List<Tarefa> tarefas = tarefaDAO.listarComFiltro(nome, idCategoria, data, concluida);
            
        for (Tarefa t : tarefas) {
            // ... (restante do loop de preenchimento da tabela) ...
        }
        // ... (restante do código) ...
    } catch (SQLException e) {
        // ...
    }
}
    
    // Método auxiliar para mapear o nome da categoria com base na posição do JComboBox1
    // A implementação real dependeria de como o idCategoria é armazenado no banco (se é a posição, ou um ID separado)
    // Assumindo que o ID da Categoria começa em 1.
    private int obterIdCategoria(String nomeCategoria) {
        switch (nomeCategoria) {
            case "Saúde (Medicamentos)": return 1;
            case "Compromissos": return 2;
            case "Finanças (Pagamentos)": return 3;
            case "Casa (Rotina)": return 4;
            case "Social (Lazer/Família)": return 5;
            case "Higiene (Pessoal)": return 6;
            case "Exercícios (Físico)": return 7;
            case "Compras (Mercado)": return 8;
            case "Internet (Digital)": return 9;
            default: return 0; // Nenhum filtro
        }
    }
    
     private String obterNomeCategoria(int idCategoria) {
        String[] categorias = { "Saúde (Medicamentos)", "Compromissos", "Finanças (Pagamentos)", 
                                "Casa (Rotina)", "Social (Lazer/Família)", "Higiene (Pessoal)", 
                                "Exercícios (Físico)", "Compras (Mercado)", "Internet (Digital)" };
        if (idCategoria >= 1 && idCategoria <= categorias.length) {
            return categorias[idCategoria - 1];
        }
        return "Desconhecida";
    }
    
    // Método auxiliar para mapear o estado (booleano) para o nome do estado
    private Boolean obterEstadoConcluida(String nomeEstado) {
        switch (nomeEstado) {
            case "Concluída": return true;
            case "Não iniciada":
            case "Em andamento": return false; // Por simplicidade, tudo que não é Concluído é considerado false
            default: return null; // Nenhum filtro
        }
    }
    
     // Método auxiliar para mapear o estado (booleano) para o nome do estado
    private String obterNomeEstado(boolean concluida) {
        return concluida ? "Concluída" : "Em andamento/Não iniciada"; // O status exato dependeria de um campo 'status' mais detalhado
    }
    
    // Método para lidar com a seleção de linha na tabela
    private void jTable1SelecaoLinhaAlterada() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada != -1) {
            // O ID é a primeira coluna do modelo (índice 0), mesmo que esteja oculta
            Object id = jTable1.getModel().getValueAt(linhaSelecionada, 0); 
            jTextField2.setText(id.toString());
        } else {
            jTextField2.setText("");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Minha Agenda Virtual");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome da tarefa", "Categoria", "Data", "Estado"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saúde (Medicamentos)", "Compromissos", "Finanças (Pagamentos)", "Casa (Rotina)", "Social (Lazer/Família)", "Higiene (Pessoal)", "Exercícios (Físico)", "Compras (Mercado)", "Internet (Digital)" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome da tarefa:");

        jLabel4.setText("Categoria:");

        jLabel5.setText("Data:");

        jLabel6.setText("Estado:");

        jButton3.setText("Excluir tarefa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Editar Tarefa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tarefa selecionada nº:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não iniciada", "Em andamento", "Concluida" }));

        jButton7.setText("Criar nova tarefa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(296, 296, 296))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
       
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
       
        try {
            // Cria e exibe a nova tela de cadastro (precisa do construtor na outra classe)
            // É comum passar a tela principal para que a tela de cadastro possa recarregar a tabela após a inserção.
            CriarTarefa criarTarefaFrame = new CriarTarefa(this); // O 'this' aqui é a instância de Principal
            criarTarefaFrame.setVisible(true);
            
            // Se esta tela for modal, ou se você quiser que a principal feche/seja desabilitada:
            // this.setEnabled(false); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir tela de criação de tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
        String nome = jTextField1.getText().trim();
        
        // Tratamento da Categoria
        String categoriaSelecionada = (String) jComboBox1.getSelectedItem();
        // Obtendo o ID da categoria baseado na seleção (Assumindo que 1 corresponde à primeira opção)
        int idCategoria = obterIdCategoria(categoriaSelecionada); 

        // Tratamento do Estado
        String estadoSelecionado = (String) jComboBox2.getSelectedItem();
        Boolean concluida = obterEstadoConcluida(estadoSelecionado);
        
        // Tratamento da Data (no formato DD/MM/AAAA)
        String dataString = jFormattedTextField1.getText().trim();
        if (!dataString.matches("\\d{2}/\\d{2}/\\d{4}")) { 
            dataString = null; // Sem filtro de data se o formato não estiver completo
        }
        
        // Chamada ao novo método de filtro no DAO
        carregarTabelaTarefas(nome, idCategoria, dataString, concluida);
        
        JOptionPane.showMessageDialog(this, "Filtragem aplicada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao tentar filtrar tarefas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idTarefa = Integer.parseInt(jTextField2.getText());
            int confirmacao = JOptionPane.showConfirmDialog(this, 
                    "Tem certeza que deseja excluir a tarefa de ID " + idTarefa + "?", 
                    "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                tarefaDAO.excluir(idTarefa);
                carregarTabelaTarefas(); // Recarrega a tabela após a exclusão
                jTextField2.setText(""); // Limpa o campo de ID
                JOptionPane.showMessageDialog(this, "Tarefa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "ID da tarefa inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir tarefa: " + e.getMessage(), "Erro de Banco", JOptionPane.ERROR_MESSAGE);
        }
   
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

 
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}