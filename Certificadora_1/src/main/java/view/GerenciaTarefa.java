/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import dao.CategoriaDao;
import dao.TarefaDAO;
import model.Categoria;
import model.Tarefa;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;

public class GerenciaTarefa extends javax.swing.JFrame {

    private CategoriaDao categoriaDAO;
    private TarefaDAO tarefaDAO;
    private List<Categoria> listaCategorias;

    public GerenciaTarefa() {
        
        /*ajustando centralizacao*/
        initComponents();
        pack();
        this.setLocationRelativeTo(null);
        
       
        try {
            // Inicializa ambos os DAOs
            this.categoriaDAO = new CategoriaDao();
            this.tarefaDAO = new TarefaDAO();
            preencherComboBoxCategorias();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar DAOs: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para carregar as categorias no jComboBox1
    private void preencherComboBoxCategorias() {
        
        // 1. Limpa o ComboBox
    jComboBox1.removeAllItems();
    
    // 2. Não adiciona a opção estática "Criar nova categoria"
    //    jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Criar nova categoria" })); // LINHA REMOVIDA
    
    try {
        listaCategorias = categoriaDAO.listarTodas();
        
        // 3. Adiciona apenas as categorias REAIS do banco
        for (Categoria c : listaCategorias) {
            jComboBox1.addItem(c.getNome());
        }
        
        // 4. Seleção Padrão
        // Agora, se houver itens, o índice 0 é a PRIMEIRA CATEGORIA REAL.
        if (jComboBox1.getItemCount() > 0) {  
            jComboBox1.setSelectedIndex(0); // Seleciona a primeira categoria real (Índice 0)
        } 
        // Se estiver vazio, o ComboBox fica vazio, o que será pego pela validação de salvar.

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar categorias para Tarefa: " + e.getMessage(), "Erro de Banco", JOptionPane.ERROR_MESSAGE);
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel3.setText("Nome da tarefa:");

        jLabel4.setText("Categoria");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Criar tarefa");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não iniciada", "Em andamento", "Concluida" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Data");

        jLabel6.setText("Estado");

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setText("Criar nova tarefa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tarefa nº:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saúde (Medicamentos)", "Compromissos", "Finanças (Pagamentos)", "Casa (Rotina)", "Social (Lazer/Família)", "Higiene (Pessoal)", "Exercícios (Físico)", "Compras (Mercado)", "Internet (Digital)" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(289, 289, 289)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
       
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
       String nomeTarefa = jTextField1.getText().trim();
    String dataHoraStr = jFormattedTextField1.getText().trim();
    int indiceCategoria = jComboBox1.getSelectedIndex(); // Captura o índice (0, 1, 2...)
    
    // 1. VALIDAÇÃO CORRIGIDA: Se o índice for -1 (nada selecionado), ou campos vazios, dispara o erro.
    if (nomeTarefa.isEmpty() || dataHoraStr.isEmpty() || indiceCategoria < 0) { 
        JOptionPane.showMessageDialog(this, "Preencha Nome, Data e selecione uma Categoria válida.", "Campos Vazios/Inválidos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
        Date dataHora = formatador.parse(dataHoraStr);
        
        // 2. CAPTURA DO OBJETO CORRIGIDA: Usa o índice 0 (primeira categoria) diretamente
        Categoria categoriaSelecionada = listaCategorias.get(indiceCategoria); 
        
        // 3. Criar e Salvar a Tarefa
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setNome(nomeTarefa);
        novaTarefa.setDatahora(dataHora);
        novaTarefa.setConcluida(false);
        novaTarefa.setIdCategoria(categoriaSelecionada.getId()); 
        
        tarefaDAO.salvar(novaTarefa);
        
        JOptionPane.showMessageDialog(this, "Tarefa '" + nomeTarefa + "' criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpar os campos
        jTextField1.setText("");
        jFormattedTextField1.setText("");
        
    } catch (java.text.ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use DD/MM/AA.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar tarefa: " + e.getMessage(), "Erro de Banco", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciaTarefa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
