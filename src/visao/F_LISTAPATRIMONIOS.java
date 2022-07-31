/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import Dao.DAOPatrimonio;
import biblioteca.Biblioteca;
import biblioteca.MetodosPublicos;
import biblioteca.ModeloTabela;
import biblioteca.TudoMaiusculas;
import conexao.ConnConexao;
import static biblioteca.VariaveisPublicas.codigoPatrimonio;
import static biblioteca.VariaveisPublicas.numMemoTransferido;
import static biblioteca.VariaveisPublicas.destinoTransferidos;
import static biblioteca.VariaveisPublicas.lstListaGenerica;
import static biblioteca.VariaveisPublicas.lstListaStrings;
import static biblioteca.VariaveisPublicas.nomeDepartamento;
import static biblioteca.VariaveisPublicas.valorItem;
import static biblioteca.VariaveisPublicas.nomeEstacaoTransferida;
import static biblioteca.VariaveisPublicas.nomeSecao;
import controle.ControleGravarLog;
import controle.CtrlPatrimonio;
import controle.CtrlPatriItenstransferido;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import modelo.Patrimonio;
import modelo.PatriTensTransferido;

public class F_LISTAPATRIMONIOS extends javax.swing.JDialog {

    ConnConexao                 conexao                     = new ConnConexao();
    ControleGravarLog           umGravarLog                 = new ControleGravarLog();
    Biblioteca                  umabiblio                   = new Biblioteca();
    Patrimonio                  objModPatrimonio            = new Patrimonio();
    CtrlPatrimonio              ctrlPatrimonio              = new CtrlPatrimonio();    
    PatriTensTransferido        objModPatriTemTransferido   = new PatriTensTransferido();
    CtrlPatriItenstransferido   ctrlPatriTenstransferido    = new CtrlPatriItenstransferido();
    DAOPatrimonio               umPatrimonioTransferidoDAO  = new DAOPatrimonio();
    MetodosPublicos             umMetodo                    = new MetodosPublicos();
        
    int icodigo, codModelo, tipoid = 0;
    
    String sqlDefault   = ("SELECT p.*, m.modelo FROM tblpatrimonios p, tblmodelos m where p.modeloid = m.codigo and p.status = 'ATIVO' ORDER BY m.modelo");
    String codigo, modelo, serie, chapa, numemo, destino, status;
    
    public F_LISTAPATRIMONIOS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);   //desabilitando o redimencionamento da tela        
        //setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar
        jTabela.setForeground(Color.blue);
        txtPESQUISA.setDocument(new TudoMaiusculas());
        txtPESQUISA.setFont(new Font("TimesRoman", Font.BOLD, 12));
        txtPESQUISA.setForeground(Color.red);
        jTabela.setFont(new Font("Arial", Font.BOLD, 12));
        this.setTitle("Selecione um Patrimônio ATIVO!");       
        mostrarPatrimonios();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBoxPesquisar = new javax.swing.JLayeredPane();
        txtPESQUISA = new javax.swing.JTextField();
        btnLimparPesquisa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBoxPesquisar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBoxPesquisar.setName("panelDados"); // NOI18N

        txtPESQUISA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPESQUISAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPESQUISAKeyReleased(evt);
            }
        });

        btnLimparPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_limpar.gif"))); // NOI18N
        btnLimparPesquisa.setText("Limpar");
        btnLimparPesquisa.setToolTipText("Limpar a pesquisa corrente");
        btnLimparPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimparPesquisa.setEnabled(false);
        btnLimparPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jBoxPesquisarLayout = new javax.swing.GroupLayout(jBoxPesquisar);
        jBoxPesquisar.setLayout(jBoxPesquisarLayout);
        jBoxPesquisarLayout.setHorizontalGroup(
            jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPESQUISA, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimparPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jBoxPesquisarLayout.setVerticalGroup(
            jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPESQUISA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jBoxPesquisar.setLayer(txtPESQUISA, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxPesquisar.setLayer(btnLimparPesquisa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBoxPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jBoxPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(727, 550));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarPatrimonios() 
    {      
        //MOSTRA TODOS OS PATRIMONIOS CADASTRADOS 
        PreencherTabela(sqlDefault); 
        
    }

    private void btnLimparPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparPesquisaActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparPesquisaActionPerformed

    private void filtrarPorDigitacao(String pPesq) {
        //filtro pela serie, chapa ou modelo
        String filtraDefaut   = "SELECT p.codigo, p.serie, p.chapa, p.modeloid, p.status, m.codigo, m.modelo FROM tblpatrimonios p, tblmodelos m "
                              + "WHERE p.modeloid = m.codigo AND (p.serie like '%" + pPesq + "%' OR p.chapa like '%" + pPesq + "%' OR m.modelo "
                              + "like '%" + pPesq + "%') AND p.status = 'ATIVO'";   
        
        PreencherTabela(filtraDefaut);  
    }

    public void limparCampos() {
        
        txtPESQUISA.setText("");
        txtPESQUISA.setEnabled(true);
        btnLimparPesquisa.setEnabled(false);
        txtPESQUISA.requestFocus();       
        mostrarPatrimonios();                        
                 
    }
    
    private void gravarRegistro() 
    {        
        //GRAVA O REGISTRO SELECIONADO NA TABELA TEMPORARIA TBLITENSMEMOTRANSFERIDOS PARA POSTERIOR IMPRESSAO       
        numemo          = numMemoTransferido;
        destino         = destinoTransferidos;
        status          = "PROCESSANDO";
        modelo          = umabiblio.pesquisarString("tblmodelos", "modelo", codModelo);        
               
        //SETANDO OS VALORES NO MODELO PARA GRAVAR
        objModPatriTemTransferido.setItem(valorItem);
        objModPatriTemTransferido.setNumemo(numemo);
        objModPatriTemTransferido.setModelo(modelo);
        objModPatriTemTransferido.setSerie(serie);
        objModPatriTemTransferido.setChapa(chapa);      
        objModPatriTemTransferido.setDestino(destino);     
        objModPatriTemTransferido.setStatus(status);
        
        ctrlPatriTenstransferido.salvarPatriItensTransferido(objModPatriTemTransferido);
        umGravarLog.gravarLog("Impressão do Memo de Transferencia "+numemo);
       
    }

    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        //AO CLICAR EM UM REGISTRO DA TABELA PEGAR O CODIGO DO REGISTRO CLICADO
        //JOptionPane.showMessageDialog(null, "SERIE DO PATRIMÔNIO SELECIONADO...: "+jTabela.getValueAt(jTabela.getSelectedRow(), 2));   
        valorItem++;
        
        //Ao clicar na linha desejada obtem-se a serie do equipamento, setando-a no objeto, com o objeto setado faço a pesquisa e obtenho o patrimonio com todos os seus dados
        serie = (String) jTabela.getValueAt(jTabela.getSelectedRow(), 2); 
        //JOptionPane.showMessageDialog(rootPane, serie);
        
        //setando a serie do equipamento para pesquisa
        objModPatrimonio.setSerie(serie);      
               
        //obtendo o patrimonio com todos os dados
        ctrlPatrimonio.pesquisarPatrimonio(objModPatrimonio);  

        //pegando o retorno da funcao/pesquisa e setando nas variaveis globais  
        codigoPatrimonio            = objModPatrimonio.getCodigo();     
        serie                       = objModPatrimonio.getSerie();
        tipoid                      = objModPatrimonio.getTipoid();
        chapa                       = objModPatrimonio.getChapa();
        nomeEstacaoTransferida      = objModPatrimonio.getEstacao();
        codModelo                   = objModPatrimonio.getModeloid();  
        
        //inserindo os dados para atualização dos registros em uma lista           
        lstListaGenerica.add(String.valueOf(codigoPatrimonio));
        lstListaStrings.add(String.valueOf(nomeEstacaoTransferida));

        //O metodo de gravação será startado depois de gerar o relatório se cancelar nada acontece       
        //JOptionPane.showMessageDialog(rootPane, "codigo :"+codigoPatrimonio+"\n"+"serie :"+serie+"\n"+"chapa :"+chapa+"\n" );       
               
        if(umabiblio.duplicidadeDeCadastroMemo("TBLITENSMEMOTRANSFERIDOS", "serie", serie)){
            //Verificando duplicidade na inserção de patrimônios
            JOptionPane.showMessageDialog(null, "Este patrimônio já esta inserido neste memorando!","Atenção duplicidade de patrimônio!",2);
            valorItem--;            
        }else{
            //gravar registro na tabela TBLITENSMEMOTRANSFERIDOS
            //JOptionPane.showMessageDialog(null, valorItem);            
            gravarRegistro();
        }  
        
        dispose();

    }//GEN-LAST:event_jTabelaMouseClicked

    private void txtPESQUISAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyReleased
        filtrarPorDigitacao(txtPESQUISA.getText());
    }//GEN-LAST:event_txtPESQUISAKeyReleased

    private void txtPESQUISAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyPressed
        //se teclar enter estando dentro da pesquisa limpar a pesquisa
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPESQUISA.setText(null);
        }
        btnLimparPesquisa.setEnabled(true);
    }//GEN-LAST:event_txtPESQUISAKeyPressed

    public void PreencherTabela(String sql) {
        conexao.conectar();
        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        String[] Colunas = new String[]{"Código", "Descrição", "Série", "Patrimônio"};
        try {
            conexao.ExecutarPesquisaSQL(sql);
            while (conexao.rs.next()) {
                dados.add(new Object[]{
                    conexao.rs.getString("codigo"),
                    conexao.rs.getString("modelo"),
                    conexao.rs.getString("serie"),
                    conexao.rs.getString("chapa")

                });
            };
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabela.setModel(modelo);
            //define tamanho das colunas
            jTabela.getColumnModel().getColumn(0).setPreferredWidth(50);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna        
            jTabela.getColumnModel().getColumn(1).setPreferredWidth(350);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(1).setResizable(false);    //nao será possivel redimencionar a coluna        
            jTabela.getColumnModel().getColumn(2).setPreferredWidth(130);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(2).setResizable(false);    //nao será possivel redimencionar a coluna    
            jTabela.getColumnModel().getColumn(3).setPreferredWidth(130);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(3).setResizable(false);    //nao será possivel redimencionar a coluna        
            //define propriedades da tabela
            jTabela.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabela.setAutoResizeMode(jTabela.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha  

        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\nErro: " + ex.getMessage());
        } finally {
            conexao.desconectar();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_LISTAPATRIMONIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_LISTAPATRIMONIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_LISTAPATRIMONIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_LISTAPATRIMONIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                F_LISTAPATRIMONIOS dialog = new F_LISTAPATRIMONIOS(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimparPesquisa;
    private javax.swing.JLayeredPane jBoxPesquisar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabela;
    private javax.swing.JTextField txtPESQUISA;
    // End of variables declaration//GEN-END:variables
}
