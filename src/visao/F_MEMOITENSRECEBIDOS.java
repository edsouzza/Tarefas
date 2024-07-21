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
import static biblioteca.VariaveisPublicas.assuntoTransferido;
import static biblioteca.VariaveisPublicas.controlenaveg;
import static biblioteca.VariaveisPublicas.anoVigente;
import biblioteca.ModeloTabela;
import biblioteca.SomenteNumeros;
import static biblioteca.VariaveisPublicas.codigoUsuario;
import static biblioteca.VariaveisPublicas.lstListaInteiros;
import static biblioteca.VariaveisPublicas.nomeCliente;
import static biblioteca.VariaveisPublicas.patriDeptos;
import static biblioteca.VariaveisPublicas.valorItem;
import static biblioteca.VariaveisPublicas.patriDevolucao;
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
import java.awt.LayoutManager;
import javax.swing.JFrame;
import modelo.PatriTensTransferido;
import modelo.PatriTransferido;


public class F_MEMOITENSRECEBIDOS extends javax.swing.JFrame {
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
    
    
    String sqlPatriCGGM    = "SELECT i.*, m.* FROM TBLITENSMEMOTRANSFERIDOS i, TBLMODELOS m WHERE i.modeloid=m.codigo AND i.status <> 'TRANSFERIDO' AND i.status <> 'BAIXADO' ORDER BY i.item";        
    String sqlVazia        = "SELECT codigo FROM TBLITENSMEMOTRANSFERIDOS WHERE codigo < 1";  
    String observacao, numemoinicial;
    int icodigo, codExc, codItem, Item, TotalItens, codigoPatri = 0;
    boolean mostrouForm, selecionouUsuario;
    
    public F_MEMOITENSRECEBIDOS() {
        initComponents();
        Leitura();        
        setResizable(false);              //desabilitando o redimencionamento da tela        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar
        this.setTitle("Gerar e imprimir memorando de devolução de equipamentos");
        
        // Colocando enter para pular de campo 
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS)); 
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0)); 
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);      
                       
        LayoutManager layout = this.getLayout();
        System.out.println("Layout do JFrame: " + layout.getClass().getName());

        
        //umabiblio.configurarCamposTextos(txtDESTINO);
        txtORIGEM.setFont(new Font("TimesRoman",Font.BOLD,16)); 
        txtORIGEM.setForeground(Color.red);        
        txtORIGEM.setDocument(new CampoTxtLimitadoPorQdeCaracteresUpperCase(30));   
        
        txtOBSERVACAO.setFont(new Font("TimesRoman",Font.BOLD,16)); 
        txtOBSERVACAO.setForeground(Color.red);
        txtOBSERVACAO.setDocument(new CampoTxtLimitadoPorQdeCaracteres(80));         
                
        //configuração dos botões
        umabiblio.configurarBotoes(btnAdicionar);
        umabiblio.configurarBotoes(btnCancelar);
        umabiblio.configurarBotoes(btnExcluirItem);
        umabiblio.configurarBotoes(btnImprimir);
        umabiblio.configurarBotoes(btnSair);
               
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
        txtOBSERVACAO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnExcluirItem = new javax.swing.JButton();
        txtORIGEM = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnLISTARCLIENTES = new javax.swing.JButton();

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

        txtOBSERVACAO.setForeground(new java.awt.Color(51, 51, 255));
        txtOBSERVACAO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOBSERVACAOFocusGained(evt);
            }
        });
        txtOBSERVACAO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOBSERVACAOMouseClicked(evt);
            }
        });

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
        txtORIGEM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtORIGEMMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("ORIGEM");

        btnLISTARCLIENTES.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnLISTARCLIENTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users16.jpg"))); // NOI18N
        btnLISTARCLIENTES.setText("SERVIDORES");
        btnLISTARCLIENTES.setToolTipText("Listar clientes ativos");
        btnLISTARCLIENTES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLISTARCLIENTES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLISTARCLIENTESMouseClicked(evt);
            }
        });
        btnLISTARCLIENTES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLISTARCLIENTESActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtOBSERVACAO)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNUMEMO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtORIGEM))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLISTARCLIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2))
                .addGap(225, 225, 225))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNUMEMO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtORIGEM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLISTARCLIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOBSERVACAO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1040, 804));
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
        txtORIGEM.setEditable(false);
        txtOBSERVACAO.setEditable(false);        
        btnSair.setEnabled(true);               
        numMemoTransferido = "";
        mostrouForm = false;        
        valorItem = 0;
        controlenaveg = 0;
        lstListaInteiros.clear();
        selecionouUsuario = false;

        //JOptionPane.showMessageDialog(null, "Próximo numemo = "+String.valueOf(umMetodo.gerarProximoNumeroMemoTransferir()));
        if(!umabiblio.tabelaVazia("TBLMEMOSTRANSFERIDOS")){
            txtNUMEMO.setText(String.valueOf(umMetodo.gerarProximoNumeroMemoTransferir()));               
        }else{
            txtNUMEMO.setText("1");
        } 
        PreencherTabela(sqlVazia);                
        
    }
        
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //Exclui os itens se nao gerou relatorio
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed
        
    private void gravarMemorando(){
        //GRAVA O CABECALHO DO MEMORANDO NA TABELA
        
        //GERANDO NUMERO DO MEMO COM O ANO VIGENTE
        numMemoTransferido     = txtNUMEMO.getText()+"/"+anoVigente;
        origemTransferidos     = txtORIGEM.getText();
        destinoTransferidos    = "CGGM/INFO";
        assuntoTransferido     = "DEVOLUCAO DE EQUIPAMENTOS";
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
            umModPatriTransferido.setAssunto(assuntoTransferido);
            umCtrlPatriTranferido.salvarPatriTransferido(umModPatriTransferido); 
            umGravarLog.gravarLog("cadastro do memo de devolucao de patrimonios "+numMemoTransferido);
        }        
        
    }
   
    private void AdicionarPatrimoniosAoMemorando(){                  
        
        /*QDO CLICA NO BOTAO ADICIONAR ABRE-SE A LISTA DE PATRIMONIOS E AO ESCOLHER UM ITEM O MESMO É INCLUIDO NA TBLITENSMEMOTRANSFERIDOS COM STATUS 
          PROCESSANDO MAS SÓ SERÁ GRAVADO SE GERAR O RELATORIO ATRAVES DO BOTAO IMPRIMIR, SE SAIR SEM GERAR O RELATORIO O MEMO E ÍTENS SERÃO EXCLUIDOS*/                             
        numMemoTransferido     = txtNUMEMO.getText()+"/"+anoVigente;
        origemTransferidos     = txtORIGEM.getText();
        assuntoTransferido     = "DEVOLUCAO DE EQUIPAMENTOS";
        destinoTransferidos    = "CGGM/INFO";
        patriDevolucao         = true;

        //ABRE ALISTA DE PATRIMONIOS COM SEUS DEVIDOS MODELOS PARA SELEÇÃO DO PATRIMONIO DESEJADO->NAO SE TRATA DE PATRIDEPTOS
        F_LISTAPATRIMONIOS frmPatrimonios = new F_LISTAPATRIMONIOS(this, true);
        frmPatrimonios.setVisible(true);           
        
        PreencherTabela(sqlPatriCGGM); 
        
        btnImprimir.setEnabled(true);            
        btnSair.setEnabled(false);   
        btnExcluirItem.setEnabled(false);
        btnCancelar.setEnabled(true);            
        controlenaveg   = 0;
        patriDevolucao  = false;      
    }
    
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        
       AdicionarPatrimoniosAoMemorando();           
        
    }//GEN-LAST:event_btnAdicionarActionPerformed
        
    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        //AO CLICAR EM UM REGISTRO DA TABELA MOSTRAR OS DADOS NOS EDITS
        codItem = (int) jTabela.getValueAt(jTabela.getSelectedRow(), 0); 
        Item    = (int) jTabela.getValueAt(jTabela.getSelectedRow(), 1);
        //JOptionPane.showMessageDialog(null, "CODIGO DO ÍTEM SELECIONADO...: "+codItem); 
        
        btnImprimir      .setEnabled(false);
        btnAdicionar     .setEnabled(false);
        btnSair          .setEnabled(true);
        btnCancelar      .setEnabled(false);
        btnExcluirItem   .setEnabled(true);        
        txtNUMEMO        .setEditable(false);

    }//GEN-LAST:event_jTabelaMouseClicked
            
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        /*IMPRIMINDO RELATORIO DOS PATRIMONIOS TRANSFERIDOS VERIFICANDO SE O ARQUIVO EXISTE RETORNA TRUE/FALSE
        System.out.println(new File("relatorio/relmemotransferidos.jasper").exists()); */
       //devolvendo o foco ao txtDESTINO logo apos a emissao do relatorio caso queira fazer outro memorando
     
        gravarMemorando();
        
        //atualizando tabela de ÍTENS ( TBLITENSMEMOTRANSFERIDOS ) do memorando de PROCESSANDO para TRANSFERIDO
        umMetodo.atualizarStatusParaTransferidos(numMemoTransferido);
                
        GerarRelatorios objRel = new GerarRelatorios();
        try {
            objRel.imprimirPatrimoniosTransferidos("relatorio/relmemorecebidos.jasper", numMemoTransferido);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!\n"+e);                
        }    

        /*=============================================================================================================================================
                                                    REATIVAR PATRIMONIOS INSERIDOS NO MEMORANDO
        ===============================================================================================================================================*/
                    
        umPatrimonioDAO.ReativarPatrimonioPeloMemorandoDAO(numMemoTransferido);  
            
        Leitura();       
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if(umabiblio.ConfirmouOperacao("Tem certeza que deseja sair e cancelar a operação,os dados não salvos serão perdidos?", "Saindo do Memorando "+numMemoTransferido)){
            umGravarLog.gravarLog("cancelando criacao do memorando "+numMemoTransferido);
            umMetodo.deletarMemorandoSemItens(numMemoTransferido);
            umMetodo.deletarItensDoMemorando(numMemoTransferido);        
            Leitura();                 
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    
    private void btnExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirItemActionPerformed
        //EXCLUINDO ITEM DO MEMO ATUAL
        ArrayList<Integer> lstListaItens = new ArrayList<>();
        
        String message = "Confirma a exclusão do ítem com código "+codItem+" do memorando em curso?";
        String title   = "Confirmação de Exclusão";
        //Exibe caixa de dialogo (veja figura) solicitando confirmação ou não. 
        //Se o usuário clicar em "Sim" retorna 0 pra variavel reply, se informado não retorna 1
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) 
        {            
            if(umCtrlPatrItemTranferido.excluirItemDoMemoAtual(codItem))
            {
                JOptionPane.showMessageDialog(null, "Ítem com código "+codItem+" foi excluído com sucesso do memorando atual!");
                
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
                    umCtrlPatrItemTranferido.atualizarValorDosItensAposExclusao(Item, numMemoTransferido);
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

    private void txtORIGEMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtORIGEMMouseClicked
        txtORIGEM.selectAll();
    }//GEN-LAST:event_txtORIGEMMouseClicked

    private void btnLISTARCLIENTESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLISTARCLIENTESActionPerformed
             
       F_LISTACLIENTESATIVOS frm = new F_LISTACLIENTESATIVOS(new JFrame(),true);
       frm.setVisible(true);
             
       String texto = nomeCliente;
       if(texto.length() > 30){
            texto = nomeCliente.substring(0, 30);              
       }else{
            texto = nomeCliente;  
       }
       txtORIGEM.setEditable(true);
       txtOBSERVACAO.setEditable(true);        
       txtOBSERVACAO.requestFocus();
       txtORIGEM.setText(texto);
       selecionouUsuario = true;    
       btnCancelar.setEnabled(true);
       btnAdicionar.setEnabled(true);
       
    }//GEN-LAST:event_btnLISTARCLIENTESActionPerformed

    private void txtOBSERVACAOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOBSERVACAOFocusGained
        if(selecionouUsuario){
            btnCancelar.setEnabled(true);
            btnAdicionar.setEnabled(true);
//            String rf    = rfCliente; 
//            txtOBSERVACAO.setText("devolvido por "+txtORIGEM.getText()+" D"+rf+" ");
        }
    }//GEN-LAST:event_txtOBSERVACAOFocusGained

    private void txtOBSERVACAOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOBSERVACAOMouseClicked
        if(!selecionouUsuario){
              
            //txtOBSERVACAO.setText(null);
            JOptionPane.showMessageDialog(null, "Selecione um nome no botão SERVIDORES para continuar!", "Servidor não selecionado!", 2); 
            
        }
        
    }//GEN-LAST:event_txtOBSERVACAOMouseClicked

    private void btnLISTARCLIENTESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLISTARCLIENTESMouseClicked
         txtOBSERVACAO.requestFocus();          
    }//GEN-LAST:event_btnLISTARCLIENTESMouseClicked
    
     public void PreencherTabela(String sql)
    {
        String[] Colunas;
        
        conexao.conectar();
        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        Colunas = new String[]{"Cod.", "Ítem", "Descrição", "Série", "Chapa"};
        
        try {
            conexao.ExecutarPesquisaSQL(sql);
            
            if(!patriDeptos){
                while (conexao.rs.next()) {
                        dados.add(new Object[]{
                        conexao.rs.getInt("codigo"),
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
            jTabela.getColumnModel().getColumn(1).setPreferredWidth(50);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(1).setResizable(false);    //nao será possivel redimencionar a coluna      
            jTabela.getColumnModel().getColumn(2).setPreferredWidth(550);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(2).setResizable(false);    //nao será possivel redimencionar a coluna        
            jTabela.getColumnModel().getColumn(3).setPreferredWidth(150);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(3).setResizable(false);    //nao será possivel redimencionar a coluna    
            jTabela.getColumnModel().getColumn(4).setPreferredWidth(150);  //define o tamanho da coluna
            jTabela.getColumnModel().getColumn(4).setResizable(false);    //nao será possivel redimencionar a coluna        
              
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
    private javax.swing.JButton btnLISTARCLIENTES;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabela;
    private javax.swing.JTextField txtNUMEMO;
    private javax.swing.JTextField txtOBSERVACAO;
    private javax.swing.JTextField txtORIGEM;
    // End of variables declaration//GEN-END:variables
}
