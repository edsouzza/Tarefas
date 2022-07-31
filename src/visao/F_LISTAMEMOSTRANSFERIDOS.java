package visao;

import biblioteca.Biblioteca;
import biblioteca.MetodosPublicos;
import biblioteca.ModeloTabela;
import biblioteca.SomenteNumeros;
import biblioteca.TudoMaiusculas;
import static biblioteca.VariaveisPublicas.numMemoTransferido;
import static biblioteca.VariaveisPublicas.totalRegs;
import conexao.ConnConexao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import relatorios.GerarRelatorios;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import controle.ControleGravarLog;
import controle.CtrlPatriItenstransferido;
import controle.CtrlPatriTransferido;
import modelo.PatriTransferido;


public class F_LISTAMEMOSTRANSFERIDOS extends javax.swing.JFrame {
    ConnConexao                    conexao                      = new ConnConexao();
    Biblioteca                     umabiblio                    = new Biblioteca();
    SomenteNumeros                 soNumeros                    = new SomenteNumeros();
    ControleGravarLog              umGravarLog                  = new ControleGravarLog();  
    
    MetodosPublicos                umMetodo                     = new MetodosPublicos();
    CtrlPatriItenstransferido      umCtrlPatrItemTranferido     = new CtrlPatriItenstransferido();
    CtrlPatriTransferido           umCtrlPatriTranferido        = new CtrlPatriTransferido();
    PatriTransferido               umModPatriTransferido        = new PatriTransferido();
    
    String sqlDinamica  = "SELECT DISTINCT m.codigo, m.numemo, i.destino FROM TBLMEMOSTRANSFERIDOS m, TBLITENSMEMOTRANSFERIDOS i WHERE m.numemo=i.numemo AND m.status = 'TRANSFERIDO' ORDER BY RIGHT('000000000000' || m.numemo, 12)";  
    String numemo;    
    int icodigo, codExc = 0;
    
    public F_LISTAMEMOSTRANSFERIDOS() {
        initComponents();
        Leitura();        
        setResizable(false);              //desabilitando o redimencionamento da tela        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar
        this.setTitle("Lista Memorandos de Patrimônios Transferidos");        
                  
        //configuração dos botões        
        umabiblio.configurarBotoes(btnImprimir);
        umabiblio.configurarBotoes(btnExluir);
        umabiblio.configurarBotoes(btnSair);
               
        //cofigurações das tabelas
        jTabela.setFont(new Font("TimesRoman",Font.BOLD,12));
        jTabela.setForeground(Color.blue);
        
        //configs do txtPesquisa
        txtPESQUISA.setDocument(new TudoMaiusculas());
        txtPESQUISA.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtPESQUISA.setForeground(Color.red);              
        
        //Impede que formulario seja arrastado na tela
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                setEnabled(false);
                setEnabled(true);
            }
        });//fim addComponentListener       
        
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnExluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jBoxPesquisar = new javax.swing.JLayeredPane();
        txtPESQUISA = new javax.swing.JTextField();
        btnLimparPesquisa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 733));

        jTabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabela);

        btnExluir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnExluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sairPrograma.gif"))); // NOI18N
        btnExluir.setText("Excluir");
        btnExluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExluir.setEnabled(false);
        btnExluir.setMaximumSize(new java.awt.Dimension(77, 25));
        btnExluir.setMinimumSize(new java.awt.Dimension(77, 25));
        btnExluir.setPreferredSize(new java.awt.Dimension(77, 25));
        btnExluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExluirActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_imprimir.gif"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setEnabled(false);
        btnImprimir.setPreferredSize(new java.awt.Dimension(77, 25));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sair.gif"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.setPreferredSize(new java.awt.Dimension(77, 25));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBoxPesquisar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBoxPesquisar.setName("panelDados"); // NOI18N

        txtPESQUISA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPESQUISA, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimparPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jBoxPesquisarLayout.setVerticalGroup(
            jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxPesquisarLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPESQUISA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jBoxPesquisar.setLayer(txtPESQUISA, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxPesquisar.setLayer(btnLimparPesquisa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBoxPesquisar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(216, 216, 216))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBoxPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(831, 640));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
                       
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed
        
    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        //AO CLICAR EM UM REGISTRO DA TABELA MOSTRAR OS DADOS NOS EDITS
        icodigo = (int)jTabela.getValueAt(jTabela.getSelectedRow(), 0);
        numemo  = (String)jTabela.getValueAt(jTabela.getSelectedRow(), 1);
        
        btnImprimir      .setEnabled(true);
        btnExluir        .setEnabled(true);     
        btnExluir.setToolTipText("Excluir memorando selecionado");
        btnImprimir.setToolTipText("Imprimir memorando selecionado");
        
    }//GEN-LAST:event_jTabelaMouseClicked
    
    private void btnExluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExluirActionPerformed
        //exclusão do ítem da tabela de transferencia
        if(umabiblio.permissaoLiberada())
        {  
            if (umabiblio.ConfirmouOperacao("Confirma a exclusão do memorando selecionado?", "Exclusão de memorando"))
            {        
                if (umCtrlPatriTranferido.excluirMemoTransferido(numemo))
                {                
                    //esta exclusao devera afetar as duas tabelas TBLMEMOSTRANSFERIDOS | TBLITENSMEMOTRANSFERIDOS             
                    umCtrlPatrItemTranferido.excluirItensDoMemoSelecionado(numemo);
                    JOptionPane.showMessageDialog(null, "O memorando selecionado foi excluído e seus ítens reativados com sucesso!","Exclusão de memorando!",1);
                    PreencherTabela(sqlDinamica);                 
                }            
            }

            Leitura();
        }
    }//GEN-LAST:event_btnExluirActionPerformed
       
    private void filtrarPorDigitacao(String pPesq) 
    {
       PreencherTabela("SELECT DISTINCT m.codigo, m.numemo, i.destino FROM TBLMEMOSTRANSFERIDOS m, TBLITENSMEMOTRANSFERIDOS i "
                        + "WHERE m.numemo=i.numemo AND m.status = 'TRANSFERIDO' AND "
                        + "( (m.numemo like '%" + pPesq + "%') OR (i.destino like '%" + pPesq + "%')) "
                        + "ORDER BY RIGHT('000000000000' || m.numemo, 12)"
                       );
       
       this.setTitle("Total de registros retornados pela pesquisa = "+totalRegs);
    }
    
    private void Leitura() {
        
        umabiblio.limparTodosCampos(rootPane);  //LIMPA TODOS OS EDITS 
        btnExluir.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnSair.setEnabled(true);         
        PreencherTabela(sqlDinamica);                
         
    }
    
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
                    
        GerarRelatorios objRel = new GerarRelatorios();
        try {
            objRel.imprimirRelatorioPatrimoniosTransferidos("relatorio/relimprimirmemodetransferidos.jasper", numemo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!\n"+e);                
        }          
              
        umGravarLog.gravarLog("impressão do memo de transferência de patrimonios "+numemo);
        Leitura();
    }//GEN-LAST:event_btnImprimirActionPerformed
    
    public void limparCampos() {
        Leitura();        
    }
    
    private void txtPESQUISAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyReleased
        filtrarPorDigitacao(txtPESQUISA.getText());
        btnLimparPesquisa.setEnabled(true);
    }//GEN-LAST:event_txtPESQUISAKeyReleased

    private void btnLimparPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparPesquisaActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparPesquisaActionPerformed

    private void txtPESQUISAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyPressed
        //se teclar enter estando dentro da pesquisa limpar a pesquisa
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            //btnLimparPesquisaActionPerformed(null);
            txtPESQUISA.setText(null);
        }
    }//GEN-LAST:event_txtPESQUISAKeyPressed
    
    public void PreencherTabela(String sql)
    {
        conexao.conectar();
        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        String[] Colunas = new String[]{"Código", "Memorando", "Destino"};
        try {
            conexao.ExecutarPesquisaSQL(sql);
            while (conexao.rs.next()) {
                dados.add(new Object[]{
                    
                    conexao.rs.getInt("codigo"),
                    conexao.rs.getString("numemo"),
                    conexao.rs.getString("destino")

                });
                totalRegs = conexao.rs.getRow();
            };
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabela.setModel(modelo);
            //define tamanho das colunas   
            jTabela.getColumnModel().getColumn(0).setPreferredWidth(60);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna      
            jTabela.getColumnModel().getColumn(1).setPreferredWidth(100); //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(1).setResizable(false);    //nao será possivel redimencionar a coluna        
            jTabela.getColumnModel().getColumn(2).setPreferredWidth(600); //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(2).setResizable(false);    //nao será possivel redimencionar a coluna         
            //define propriedades da tabela
            jTabela.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabela.setAutoResizeMode(jTabela.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha  
        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\nErro: " + ex.getMessage());
        }finally{
             conexao.desconectar();
        }
    }
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimparPesquisa;
    private javax.swing.JButton btnSair;
    private javax.swing.JLayeredPane jBoxPesquisar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabela;
    private javax.swing.JTextField txtPESQUISA;
    // End of variables declaration//GEN-END:variables
}
