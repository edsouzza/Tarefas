package visao;

import Dao.DAOPatriTensTransferido;
import Dao.DAOPatrimonio;
import biblioteca.Biblioteca;
import biblioteca.CampoTxtLimitadoPorQdeCaracteresUpperCase;
import biblioteca.CampoTxtLimitadoPorQdeCaracteres;
import biblioteca.MetodosPublicos;
import static biblioteca.VariaveisPublicas.numMemoTransferido;
import static biblioteca.VariaveisPublicas.origemTransferidos;
import static biblioteca.VariaveisPublicas.destinoTransferidos;
import static biblioteca.VariaveisPublicas.destinoMemorando;
import static biblioteca.VariaveisPublicas.controlenaveg;
import static biblioteca.VariaveisPublicas.patriDeptos;
import static biblioteca.VariaveisPublicas.anoVigente;
import biblioteca.ModeloTabela;
import biblioteca.SomenteNumeros;
import static biblioteca.VariaveisPublicas.codigoUsuario;
import static biblioteca.VariaveisPublicas.enviando;
import static biblioteca.VariaveisPublicas.lstListaGenerica;
import static biblioteca.VariaveisPublicas.lstListaInteiros;
import static biblioteca.VariaveisPublicas.valorItem;
import conexao.ConnConexao;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import relatorios.GerarRelatorios;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import controle.ControleGravarLog;
import controle.CtrlPatriItenstransferido;
import controle.CtrlPatriTransferido;
import modelo.PatriTensTransferido;
import modelo.PatriTransferido;


public class F_MEMOITENSTRANSFERIDOS extends javax.swing.JFrame {
    ConnConexao                    conexao                      = new ConnConexao();
    Biblioteca                     umabiblio                    = new Biblioteca();
    SomenteNumeros                 soNumeros                    = new SomenteNumeros();
    ControleGravarLog              umGravarLog                  = new ControleGravarLog();      
    MetodosPublicos                umMetodo                     = new MetodosPublicos();
    CtrlPatriItenstransferido      umCtrlPatrItemTranferido     = new CtrlPatriItenstransferido();
    CtrlPatriTransferido           umCtrlPatriTranferido        = new CtrlPatriTransferido();
    PatriTransferido               umModPatriTransferido        = new PatriTransferido();
    PatriTensTransferido           umModPatrItensTransferido    = new PatriTensTransferido();
    DAOPatrimonio                  umPatrimonioDAO              = new DAOPatrimonio();
    DAOPatriTensTransferido        umDAOPatriItens              = new DAOPatriTensTransferido();
    
    
    String sqlPatriCGGM    = "SELECT i.*, m.* FROM TBLITENSMEMOTRANSFERIDOS i, TBLMODELOS m WHERE i.modeloid=m.codigo AND i.status <> 'TRANSFERIDO' ORDER BY i.item";       
    //String sqlPatriDEPTOS  = "SELECT i.*, m.* FROM TBLITENSMEMOTRANSFERIDOS i, TBLMODELOS m WHERE i.modeloid=m.codigo AND i.status <> 'TRANSFERIDO' ORDER BY i.item";      
    String sqlVazia        = "SELECT codigo FROM TBLITENSMEMOTRANSFERIDOS WHERE codigo < 1";  
    String observacao, numemoinicial;
    int icodigo, codExc, codItem, TotalItens, codigoPatri = 0;
    boolean mostrouForm;
    
    public F_MEMOITENSTRANSFERIDOS() {
        initComponents();
        Leitura();        
        setResizable(false);              //desabilitando o redimencionamento da tela        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar
        this.setTitle("Gerar e imprimir memorando de transferências de patrimônios");
        
        // Colocando enter para pular de campo 
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS)); 
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0)); 
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);         
        
        //umabiblio.configurarCamposTextos(txtDESTINO);
        txtORIGEM.setFont(new Font("TimesRoman",Font.BOLD,16)); 
        txtORIGEM.setForeground(Color.red);        
        txtORIGEM.setDocument(new CampoTxtLimitadoPorQdeCaracteresUpperCase(80));    
        txtDESTINO.setFont(new Font("TimesRoman",Font.BOLD,16)); 
        txtDESTINO.setForeground(Color.red);        
        txtDESTINO.requestFocus();
        txtDESTINO.setDocument(new CampoTxtLimitadoPorQdeCaracteresUpperCase(80));    
        
        txtOBSERVACAO.setFont(new Font("TimesRoman",Font.BOLD,16)); 
        txtOBSERVACAO.setForeground(Color.red);
        txtOBSERVACAO.setDocument(new CampoTxtLimitadoPorQdeCaracteres(80));         
                
        //configuração dos botões
        umabiblio.configurarBotoes(btnAdicionar);
        umabiblio.configurarBotoes(btnCancelar);
        umabiblio.configurarBotoes(btnExcluirItem);
        umabiblio.configurarBotoes(btnImprimir);
        umabiblio.configurarBotoes(btnSair);
        umabiblio.configurarBotoes(btnSelecionarDestino);
               
        //cofigurações das tabelas
        jTabela.setFont(new Font("TimesRoman",Font.BOLD,12));
        jTabela.setForeground(Color.blue);
        jTabela.setFocusable(false);   //RETIRANDO O FOCO DA TABELA PARA ABRIR FOCO DIRETO NO TXTNUMERO        
        txtNUMEMO.setFocusable(false); //RETIRANDO O FOCO DO NUMERO PARA ABRIR FOCO DIRETO NO TXTNUMERO        
        
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
        btnAdicionar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtNUMEMO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDESTINO = new javax.swing.JTextField();
        txtOBSERVACAO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnExcluirItem = new javax.swing.JButton();
        txtORIGEM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSelecionarDestino = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 860));
        setPreferredSize(new java.awt.Dimension(1035, 860));

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 733));

        jTabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabela);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users16.jpg"))); // NOI18N
        btnAdicionar.setText("Add Equipamentos");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_imprimir.gif"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setPreferredSize(new java.awt.Dimension(77, 25));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sair.gif"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.setPreferredSize(new java.awt.Dimension(77, 25));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        txtNUMEMO.setEditable(false);
        txtNUMEMO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNUMEMO.setForeground(new java.awt.Color(255, 51, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("MEMO");

        txtDESTINO.setForeground(new java.awt.Color(51, 51, 255));
        txtDESTINO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDESTINOMouseClicked(evt);
            }
        });
        txtDESTINO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDESTINOKeyPressed(evt);
            }
        });

        txtOBSERVACAO.setForeground(new java.awt.Color(51, 51, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("OBSERVAÇÃO");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sair.gif"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sairPrograma.gif"))); // NOI18N
        btnExcluirItem.setText("Excluir Ítem");
        btnExcluirItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirItem.setEnabled(false);
        btnExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirItemActionPerformed(evt);
            }
        });

        txtORIGEM.setForeground(new java.awt.Color(51, 51, 255));
        txtORIGEM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtORIGEMFocusLost(evt);
            }
        });
        txtORIGEM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtORIGEMMouseClicked(evt);
            }
        });
        txtORIGEM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtORIGEMKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("DESTINO");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("ORIGEM");

        btnSelecionarDestino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_pesquisa.gif"))); // NOI18N
        btnSelecionarDestino.setText("Destino Patrimonios Deptos");
        btnSelecionarDestino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelecionarDestino.setEnabled(false);
        btnSelecionarDestino.setPreferredSize(new java.awt.Dimension(77, 25));
        btnSelecionarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(txtNUMEMO, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtORIGEM)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDESTINO, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecionarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addComponent(txtOBSERVACAO)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDESTINO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtORIGEM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNUMEMO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnSelecionarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOBSERVACAO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1048, 808));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
            
    private void Leitura() 
    {        
                 
        umMetodo.excluirMemorandoSemItens();        
        umCtrlPatrItemTranferido.excluirItensSProcessando();

        umabiblio.limparTodosCampos(rootPane);  //LIMPA TODOS OS EDITS 
        btnCancelar.setEnabled(false);
        btnAdicionar.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnExcluirItem.setEnabled(false);
        btnSelecionarDestino.setEnabled(false);
        txtORIGEM.setEditable(true);
        txtDESTINO.setEditable(true);
        txtOBSERVACAO.setEditable(true);        
        btnSair.setEnabled(true);         
        txtDESTINO.setEditable(true);        
        numMemoTransferido = "";
        mostrouForm = false;
        patriDeptos=false;
        valorItem = 0;
        controlenaveg = 0;
        lstListaInteiros.clear();

        //JOptionPane.showMessageDialog(null, "Próximo numemo = "+String.valueOf(umMetodo.gerarProximoNumeroMemoTransferir()));
        if(!umabiblio.tabelaVazia("TBLMEMOSTRANSFERIDOS")){
            txtNUMEMO.setText(String.valueOf(umMetodo.gerarProximoNumeroMemoTransferir()));               
        }else{
            txtNUMEMO.setText("1");
        } 
        PreencherTabela(sqlVazia);
        txtORIGEM.requestFocus();
    }
        
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //Exclui os itens se nao gerou relatorio
        btnCancelarActionPerformed(null);
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed
   
   
    private void AdicionarPatrimoniosAoMemorando(){                  
        
        /*QDO CLICA NO BOTAO ADICIONAR ABRE-SE A LISTA DE PATRIMONIOS E AO ESCOLHER UM ITEM O MESMO É INCLUIDO NA TBLITENSMEMOTRANSFERIDOS COM STATUS 
          PROCESSANDO MAS SÓ SERÁ GRAVADO SE GERAR O RELATORIO ATRAVES DO BOTAO IMPRIMIR, SE SAIR SEM GERAR O RELATORIO O MEMO E ÍTENS SERÃO EXCLUIDOS*/
                
        //SE O NUMERO DO MEMORANDO ESTIVER PREENCHIDO - NOSSO CABEÇALHO
        if(!txtNUMEMO.getText().isEmpty() || !txtORIGEM.getText().isEmpty() || !txtDESTINO.getText().isEmpty()){
            
            //GERANDO NUMERO DO MEMO COM O ANO VIGENTE
            numMemoTransferido     = txtNUMEMO.getText()+"/"+anoVigente;
            origemTransferidos     = txtORIGEM.getText();
            destinoTransferidos    = txtDESTINO.getText();
            observacao             = txtOBSERVACAO.getText();
            
            /*salvando memorando em definitivo ( TBLMEMOSTRANSFERIDOS ) apos gerar o relatorio  
              Nao deixar salvar quando clicado mais de uma vez / So gravar a primeira vez que clicar*/            
            if(!umMetodo.temDuplicidadeDeCadastro("TBLMEMOSTRANSFERIDOS", "numemo", numMemoTransferido))
            {
                umModPatriTransferido.setNumemo(numMemoTransferido);
                
                if(!observacao.equals(null) && !observacao.equals(""))
                {
                    umModPatriTransferido.setObservacao("Obs : "+umMetodo.primeiraLetraMaiuscula(observacao));
                }else{
                    umModPatriTransferido.setObservacao(" ");
                }
                
                umModPatriTransferido.setStatus("TRANSFERIDO");
                umModPatriTransferido.setIdusuario(codigoUsuario);
                umCtrlPatriTranferido.salvarPatriTransferido(umModPatriTransferido); 
                umGravarLog.gravarLog("cadastro do memo de transferencia de patrimonios "+numMemoTransferido);
            }
            
            //ABRE ALISTA DE PATRIMONIOS COM SEUS DEVIDOS MODELOS PARA SELEÇÃO DO PATRIMONIO DESEJADO->NAO SE TRATA DE PATRIDEPTOS
            F_LISTAPATRIMONIOS frmPatrimonios = new F_LISTAPATRIMONIOS(this, true);
            frmPatrimonios.setVisible(true);
            btnImprimir.setEnabled(true);        
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Entre primeiro com um número de Memorando para continuar!","Atenção número do Memorando inválido!",2);
            btnAdicionar.setEnabled(false);
            txtNUMEMO.requestFocus();
        }                   
           PreencherTabela(sqlPatriCGGM);         
                
        btnSair.setEnabled(false);   
        btnExcluirItem.setEnabled(false);
        btnCancelar.setEnabled(true);    
        txtOBSERVACAO.setEditable(false);
        controlenaveg=0;
                
    }
    
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        
       AdicionarPatrimoniosAoMemorando();
        
    }//GEN-LAST:event_btnAdicionarActionPerformed
        
    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        //AO CLICAR EM UM REGISTRO DA TABELA MOSTRAR OS DADOS NOS EDITS
        codItem = (int) jTabela.getValueAt(jTabela.getSelectedRow(), 0); 
        //JOptionPane.showMessageDialog(null, "CODIGO DO ÍTEM SELECIONADO...: "+codItem); 
        
        btnImprimir      .setEnabled(false);
        btnAdicionar     .setEnabled(false);
        btnSair          .setEnabled(true);
        btnCancelar      .setEnabled(false);
        btnExcluirItem   .setEnabled(true);
        
        txtNUMEMO        .setEditable(false);
        txtDESTINO       .setEditable(false);

    }//GEN-LAST:event_jTabelaMouseClicked
    
    private void txtDESTINOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDESTINOKeyPressed
        btnAdicionar.setEnabled(true);
        btnCancelar.setEnabled(true);
        lstListaGenerica.clear();
        patriDeptos=false;
        btnSelecionarDestino.setEnabled(false);
        
    }//GEN-LAST:event_txtDESTINOKeyPressed
        
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        /*IMPRIMINDO RELATORIO DOS PATRIMONIOS TRANSFERIDOS VERIFICANDO SE O ARQUIVO EXISTE RETORNA TRUE/FALSE
        System.out.println(new File("relatorio/relmemotransferidos.jasper").exists()); */
        txtDESTINO.requestFocus(); //devolvendo o foco ao txtDESTINO logo apos a emissao do relatorio caso queira fazer outro memorando
                
        GerarRelatorios objRel = new GerarRelatorios();
        try {
            objRel.imprimirPatrimoniosTransferidos("relatorio/relmemotransferidos.jasper");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!\n"+e);                
        }    
        
        //atualizando tabela de ÍTENS ( TBLITENSMEMOTRANSFERIDOS ) do memorando de PROSSESANDO para TRANSFERIDO
        umMetodo.atualizarStatusParaTransferidos();
        
        /*Preciso passar um array lista de strings com os codigos e transforma-los em integer para passar como parametro do metodo 
        e asssim atualizar todos os registros incluidos no memorando*/        
                
        for(int i=0; i<lstListaInteiros.size();i++){
            codigoPatri = lstListaInteiros.get(i);     
            if(enviando){
                umMetodo.atualizarStatusParaEnviados(codigoPatri);
            }else{
                umMetodo.atualizarStatusParaDevolvidos(codigoPatri);
            }            
        }   
        
        /*=============================================================================================================================================
                                                    INATIVAR PATRIMONIOS INSERIDOS NO MEMORANDO
        ===============================================================================================================================================*/
                    
        umPatrimonioDAO.InativarPatrimonioPeloMemorandoDAO(numMemoTransferido);  
            
        Leitura();        
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        umMetodo.deletarMemorandoSemItens(numMemoTransferido);
        umMetodo.deletarItensDoMemorando(numMemoTransferido);        
        Leitura();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    
    private void btnExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirItemActionPerformed
        //EXCLUINDO ITEM DO MEMO ATUAL
        ArrayList<Integer> lstListaItens = new ArrayList<>();
        
        String message = "Confirma a exclusão do ítem "+codItem+" do memorando em curso?";
        String title   = "Confirmação de Exclusão";
        //Exibe caixa de dialogo (veja figura) solicitando confirmação ou não. 
        //Se o usuário clicar em "Sim" retorna 0 pra variavel reply, se informado não retorna 1
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) 
        {            
            if(umCtrlPatrItemTranferido.excluirItemDoMemoAtual(codItem, numMemoTransferido))
            {
                JOptionPane.showMessageDialog(null, "Ítem "+codItem+" foi excluído com sucesso do memorando atual!");
                
                //Reorganizar os numeros dos ítens e depois mostrar com PreencherTabela(sqlDinamica)
                valorItem--;
                //reorganizarListaDeItensDoMemorandoAposExclusao(codItem);
                
                //Verificando se o memo atual ainda tem ítens apos a exclusao
                lstListaItens = umDAOPatriItens.ListaItemsAposExclusao();
                
                //Se ainda restarem ítens no memo em curso
                if(lstListaItens.size()>0)
                {
//                    JOptionPane.showMessageDialog(null,"Sim este memorando ainda tem ítens cadastrados após a exclusão");
//                    JOptionPane.showMessageDialog(null, "O total atual de itens após a exclusão é : "+lstListaGenerica.size());
                                       
                    //atualizar o valor do item
                    umCtrlPatrItemTranferido.atualizarValorDosItensAposExclusao(codItem);
                    btnAdicionar.setEnabled(true);
                    btnImprimir.setEnabled(true);
                                        
                }else{
                    //JOptionPane.showMessageDialog(null,"Não existem ítens cadastrados neste memorando após a exclusão");
                    //Tudo Ok por aqui...
                    btnAdicionar.setEnabled(true);
                }
                
                PreencherTabela(sqlPatriCGGM);       
                
            }
        }else{
            btnAdicionar.setEnabled(true);
        }            
            
        btnExcluirItem.setEnabled(false);   
        
    }//GEN-LAST:event_btnExcluirItemActionPerformed

    private void txtORIGEMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtORIGEMKeyPressed
        //SE TECLAR ENTER DENTRO DO TXT IR PARA TXTDESTINO
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDESTINO.requestFocus();            
        }
        if(controlenaveg==1){
             btnSelecionarDestino.setEnabled(true);
             txtDESTINO.setEditable(false);             
        }
        btnSair.setEnabled(false);   
    }//GEN-LAST:event_txtORIGEMKeyPressed

    private void txtORIGEMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtORIGEMMouseClicked
        txtORIGEM.selectAll();
    }//GEN-LAST:event_txtORIGEMMouseClicked

    private void txtDESTINOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDESTINOMouseClicked
        txtDESTINO.selectAll();
    }//GEN-LAST:event_txtDESTINOMouseClicked

    private void btnSelecionarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarDestinoActionPerformed
        destinoMemorando = "";
        patriDeptos=true;
        
        if(umMetodo.temUnidadesParaEnvio() || umMetodo.temUnidadesParaDevolucao()){
            if(mostrouForm == false){
                                
                F_LISTADEPTOSMEMORANDO frm = new F_LISTADEPTOSMEMORANDO(this,true);
                frm.setVisible(true);  
                
                mostrouForm = true;
                btnAdicionar.setEnabled(true);
                txtORIGEM.setEditable(false);
                txtDESTINO.setEditable(false);
                txtOBSERVACAO.requestFocus();
            }
        }else{
             JOptionPane.showMessageDialog(null, "Ops, não temos patrimônios de departamentos disponíveis para emissão de memorando no momento!", "Sem patrimônios para encaminhamento!", 2);
             btnCancelarActionPerformed(null);             
        }
        txtDESTINO.setText(destinoMemorando);
        btnSelecionarDestino.setEnabled(false);
        btnCancelar.setEnabled(true);

    }//GEN-LAST:event_btnSelecionarDestinoActionPerformed

    private void txtORIGEMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtORIGEMFocusLost
        if(txtORIGEM.getText().equals("CGGM")){
            txtORIGEM.setText("CGGM/INFO");
        }
    }//GEN-LAST:event_txtORIGEMFocusLost
    
    public void PreencherTabela(String sql)
    {
        String[] Colunas;
        
        conexao.conectar();
        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        if(!patriDeptos){
            Colunas = new String[]{"Ítem", "Descrição", "Série", "Chapa"};
        }else{
            Colunas = new String[]{"Ítem", "Descrição", "Série", "Chapa"};
        }
        try {
            conexao.ExecutarPesquisaSQL(sql);
            
            if(!patriDeptos){
                while (conexao.rs.next()) {
                        dados.add(new Object[]{
                        conexao.rs.getInt("item"),
                        conexao.rs.getString("modelo"),
                        conexao.rs.getString("serie"),
                        conexao.rs.getString("chapa")
                    });
                };
            }else{
                while (conexao.rs.next()) {
                        dados.add(new Object[]{
                        conexao.rs.getInt("item"),
                        conexao.rs.getString("modelo"),
                        conexao.rs.getString("serie"),
                        conexao.rs.getString("chapa")
                    });
                };        
            }
            
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabela.setModel(modelo);
            //define tamanho das colunas   
            jTabela.getColumnModel().getColumn(0).setPreferredWidth(50);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna      
            jTabela.getColumnModel().getColumn(1).setPreferredWidth(680);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(1).setResizable(false);    //nao será possivel redimencionar a coluna        
            jTabela.getColumnModel().getColumn(2).setPreferredWidth(125);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(2).setResizable(false);    //nao será possivel redimencionar a coluna    
            jTabela.getColumnModel().getColumn(3).setPreferredWidth(125);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(3).setResizable(false);    //nao será possivel redimencionar a coluna        
            //define propriedades da tabela
            jTabela.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabela.setAutoResizeMode(jTabela.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha  
        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher a Tabela de Patrimônios!\nErro: " + ex.getMessage());
        }finally{
             conexao.desconectar();
        }
    }
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluirItem;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSelecionarDestino;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabela;
    private javax.swing.JTextField txtDESTINO;
    private javax.swing.JTextField txtNUMEMO;
    private javax.swing.JTextField txtOBSERVACAO;
    private javax.swing.JTextField txtORIGEM;
    // End of variables declaration//GEN-END:variables
}
