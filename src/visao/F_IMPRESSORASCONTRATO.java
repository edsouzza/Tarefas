/*FORMULARIO CRIADO PARA CADASTRO DE TABELAS COM APENAS UM CAMPO*/
package visao;

import biblioteca.Biblioteca;
import biblioteca.CampoLimitadoEmQdeLetrasMaiNumeros;
import conexao.ConnConexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import biblioteca.MetodosPublicos;
import biblioteca.ModeloTabela;
import biblioteca.RetornarQdeLinhasDoTxt;
import biblioteca.SelecionarArquivoTexto;
import static biblioteca.VariaveisPublicas.TipoModelo;
import static biblioteca.VariaveisPublicas.editando;
import static biblioteca.VariaveisPublicas.consultandoInativos;
import static biblioteca.VariaveisPublicas.reativando;
import static biblioteca.VariaveisPublicas.cadastrando;
import static biblioteca.VariaveisPublicas.lstListaCampos;
import static biblioteca.VariaveisPublicas.tabela;
import static biblioteca.VariaveisPublicas.alterouStatus;
import static biblioteca.VariaveisPublicas.codigoDepartamento;
import static biblioteca.VariaveisPublicas.codigoTipoModelo;
import static biblioteca.VariaveisPublicas.nomeDepartamento;
import static biblioteca.VariaveisPublicas.tabela_da_lista;
import static biblioteca.VariaveisPublicas.totalRegs;
import static biblioteca.VariaveisPublicas.contador;
import controle.ControleGravarLog;
import controle.CtrlImpressoraContrato;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import modelo.ImpressoraContrato;

public class F_IMPRESSORASCONTRATO extends javax.swing.JDialog {

    ConnConexao              umaConexao                      = new ConnConexao();
    Biblioteca               umabiblio                       = new Biblioteca();
    MetodosPublicos          umMetodo                        = new MetodosPublicos();
    ControleGravarLog        umGravarLog                     = new ControleGravarLog();
            
    ImpressoraContrato       objModeloImpressora             = new ImpressoraContrato();
    CtrlImpressoraContrato   ctrlImpressoraContrato          = new CtrlImpressoraContrato();    
    String                   nomeDoCampo                     = null;

    Boolean clicouNaTabela = false;
    String nome, obs, unidade, modelo, serie, ip, caminhoTXT, sql, status, linha, nomeTabelaSemTBL = "";
    int codigo, codigoRegSelecionado = 0;
    
    String sqlATIVOS    = "select c.*, m.modelo, d.nome as unidade from tblImpressorasContrato c, tblmodelos m,  tbldepartamentos d where c.unidadeid=d.codigo and c.modeloid=m.codigo and c.status='ATIVO' order by d.nome,c.serie,m.modelo";
    String sqlINATIVOS  = "select c.*, m.modelo, d.nome as unidade from tblImpressorasContrato c, tblmodelos m,  tbldepartamentos d where c.unidadeid=d.codigo and c.modeloid=m.codigo and c.status='INATIVO' order by d.nome,c.serie,m.modelo";
    String sqlVazia     = "select * from tblImpressorasContrato where codigo = 0";

    public F_IMPRESSORASCONTRATO(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIcon();
        Leitura();
        setResizable(false);   //desabilitando o redimencionamento da tela        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar

        //Impede que formulario seja arrastado na tela
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                setEnabled(false);
                setEnabled(true);
            }
        });//fim addComponentListener

        //configuracoes dos edits 
        umMetodo.configurarCamposTextos(txtPESQUISA);
        umMetodo.configurarCamposTextos(txtCODIGO);
        umMetodo.configurarCamposTextos(txtUNIDADE);
        umMetodo.configurarCamposTextos(txtMODELO);
        umMetodo.configurarCamposTextos(txtSERIE);
        umMetodo.configurarCamposTextos(txtIP);

        //configuração dos botões
        umMetodo.configurarBotoes(btnNovo);
        umMetodo.configurarBotoes(btnEditar);
        umMetodo.configurarBotoes(btnGravar);
        umMetodo.configurarBotoes(btnVoltar);
        umMetodo.configurarBotoes(btnSair);
        umMetodo.configurarBotoes(btnImportarDados);

        jTabelaATIVOS.setFont(new Font("Arial", Font.BOLD, 12));
        jTabelaINATIVOS.setFont(new Font("Arial", Font.BOLD, 12));
        txtOBS.setDocument(new CampoLimitadoEmQdeLetrasMaiNumeros(200));
        txtOBS.setFont(new Font("TimesRoman", Font.BOLD, 14));
        txtCODIGO.setForeground(Color.red);

    }

    public void getValoresDosEdits() {
        codigo    = Integer.parseInt(txtCODIGO.getText());
        unidade   = txtUNIDADE.getText();
        modelo    = txtMODELO.getText();
        serie     = txtSERIE.getText();
        ip        = txtIP.getText();
        obs       = txtOBS.getText();
        status    = cmbStatus.getSelectedItem().toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBoxPesquisar = new javax.swing.JLayeredPane();
        txtPESQUISA = new javax.swing.JTextField();
        jBoxDados = new javax.swing.JLayeredPane();
        lblNOME = new javax.swing.JLabel();
        txtUNIDADE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCODIGO = new javax.swing.JTextField();
        txtOBS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        lblNOME1 = new javax.swing.JLabel();
        txtMODELO = new javax.swing.JTextField();
        lblNOME2 = new javax.swing.JLabel();
        txtSERIE = new javax.swing.JTextField();
        lblNOME3 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        jBoxBotoes = new javax.swing.JLayeredPane();
        btnGravar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnImportarDados = new javax.swing.JButton();
        btnGerarExcel = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabelaATIVOS = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabelaINATIVOS = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Servidores com Cargo");

        jBoxPesquisar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBoxPesquisar.setName("panelDados"); // NOI18N

        txtPESQUISA.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPESQUISA.setToolTipText("Digite algo para pesquisar pelo nome");
        txtPESQUISA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPESQUISAMouseClicked(evt);
            }
        });
        txtPESQUISA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPESQUISAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPESQUISAKeyReleased(evt);
            }
        });

        jBoxPesquisar.setLayer(txtPESQUISA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jBoxPesquisarLayout = new javax.swing.GroupLayout(jBoxPesquisar);
        jBoxPesquisar.setLayout(jBoxPesquisarLayout);
        jBoxPesquisarLayout.setHorizontalGroup(
            jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPESQUISA)
                .addContainerGap())
        );
        jBoxPesquisarLayout.setVerticalGroup(
            jBoxPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBoxPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPESQUISA, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        jBoxDados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBoxDados.setName("panelDados"); // NOI18N

        lblNOME.setText("UNIDADE");

        txtUNIDADE.setForeground(new java.awt.Color(51, 51, 255));
        txtUNIDADE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtUNIDADEMouseMoved(evt);
            }
        });
        txtUNIDADE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUNIDADEMouseClicked(evt);
            }
        });

        jLabel3.setText("CÓDIGO");

        txtCODIGO.setForeground(new java.awt.Color(51, 51, 255));

        txtOBS.setForeground(new java.awt.Color(51, 51, 255));

        jLabel9.setText("OBSERVAÇÕES");

        jLabel2.setText("STATUS");

        cmbStatus.setForeground(new java.awt.Color(51, 51, 255));
        cmbStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbStatus.setEnabled(false);
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });

        lblNOME1.setText("MODELO");

        txtMODELO.setForeground(new java.awt.Color(51, 51, 255));
        txtMODELO.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtMODELOMouseMoved(evt);
            }
        });
        txtMODELO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMODELOFocusGained(evt);
            }
        });
        txtMODELO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMODELOMouseClicked(evt);
            }
        });

        lblNOME2.setText("SERIE");

        txtSERIE.setForeground(new java.awt.Color(51, 51, 255));
        txtSERIE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSERIEMouseClicked(evt);
            }
        });
        txtSERIE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSERIEKeyPressed(evt);
            }
        });

        lblNOME3.setText("IP");

        txtIP.setForeground(new java.awt.Color(51, 51, 255));
        txtIP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIPMouseClicked(evt);
            }
        });
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIPKeyPressed(evt);
            }
        });

        jBoxDados.setLayer(lblNOME, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtUNIDADE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtCODIGO, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtOBS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(cmbStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(lblNOME1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtMODELO, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(lblNOME2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtSERIE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(lblNOME3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxDados.setLayer(txtIP, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jBoxDadosLayout = new javax.swing.GroupLayout(jBoxDados);
        jBoxDados.setLayout(jBoxDadosLayout);
        jBoxDadosLayout.setHorizontalGroup(
            jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNOME3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOBS, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(10, 10, 10)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jBoxDadosLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCODIGO))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUNIDADE, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNOME))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNOME1)
                            .addComponent(txtMODELO, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSERIE)
                            .addGroup(jBoxDadosLayout.createSequentialGroup()
                                .addComponent(lblNOME2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jBoxDadosLayout.setVerticalGroup(
            jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNOME)
                            .addComponent(lblNOME1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUNIDADE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMODELO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addComponent(lblNOME2)
                        .addGap(36, 36, 36))
                    .addComponent(txtSERIE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(lblNOME3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jBoxDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOBS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jBoxDadosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jBoxBotoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_gravar.jpg"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_reverter.gif"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sair.gif"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_blocoNotas.gif"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calculator.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnImportarDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_professor.gif"))); // NOI18N
        btnImportarDados.setText("Importar TXT");
        btnImportarDados.setToolTipText("Cadastrar a partir de um arquivo TXT");
        btnImportarDados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImportarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarDadosActionPerformed(evt);
            }
        });

        btnGerarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Excel.gif"))); // NOI18N
        btnGerarExcel.setText("Excel");
        btnGerarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGerarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarExcelActionPerformed(evt);
            }
        });

        jBoxBotoes.setLayer(btnGravar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnVoltar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnSair, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnNovo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnImportarDados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jBoxBotoes.setLayer(btnGerarExcel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jBoxBotoesLayout = new javax.swing.GroupLayout(jBoxBotoes);
        jBoxBotoes.setLayout(jBoxBotoesLayout);
        jBoxBotoesLayout.setHorizontalGroup(
            jBoxBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnGerarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnImportarDados)
                .addGap(27, 27, 27)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jBoxBotoesLayout.setVerticalGroup(
            jBoxBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBoxBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jBoxBotoesLayout.createSequentialGroup()
                        .addGroup(jBoxBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jBoxBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGerarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImportarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jTabelaATIVOS.setGridColor(new java.awt.Color(255, 255, 255));
        jTabelaATIVOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaATIVOSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTabelaATIVOS);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("ATIVOS", jPanel6);

        jTabelaINATIVOS.setGridColor(new java.awt.Color(255, 255, 255));
        jTabelaINATIVOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaINATIVOSMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTabelaINATIVOS);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("INATIVOS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane3)
                    .addComponent(jBoxBotoes, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBoxDados, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBoxPesquisar, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBoxPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBoxDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jBoxBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1070, 689));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPESQUISAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyPressed
        //se teclar enter estando dentro da pesquisa limpar a pesquisa
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPESQUISA.setText(null);
            btnNovo.setEnabled(false);
            btnVoltar.setEnabled(true);
            PreencherTabelaATIVOS(sqlATIVOS);
        }

    }//GEN-LAST:event_txtPESQUISAKeyPressed

    private void filtrarPorDigitacao(String pPesq) {
        //filtrando por digitação pelos trez campos nome rf ou secao
        if (consultandoInativos) {
            PreencherTabelaINATIVOS("select c.*, m.modelo, d.nome as unidade from tblImpressorasContrato c, tblmodelos m,  tbldepartamentos d "
                                + "where "
                                + "(c.serie like '%" + pPesq + "%' OR d.nome like '%" + pPesq + "%' OR m.modelo like '%" + pPesq + "%' OR c.ip like '%" + pPesq + "%') "
                                + "and " 
                                + "c.unidadeid=d.codigo and "
                                + "c.modeloid=m.codigo and "
                                + "c.status='INATIVO' "
                                + "order by d.nome,m.modelo"); 
                                this.setTitle("Total de registros retornados pela pesquisa = "+totalRegs);
        } else {
            PreencherTabelaATIVOS("select c.*, m.modelo, d.nome as unidade from tblImpressorasContrato c, tblmodelos m,  tbldepartamentos d "
                                + "where "
                                + "(c.serie like '%" + pPesq + "%' OR d.nome like '%" + pPesq + "%' OR m.modelo like '%" + pPesq + "%' OR c.ip like '%" + pPesq + "%') "
                                + "and " 
                                + "c.unidadeid=d.codigo and "
                                + "c.modeloid=m.codigo and "
                                + "c.status='ATIVO' "
                                + "order by d.nome,m.modelo");  
                                this.setTitle("Total de registros retornados pela pesquisa = "+totalRegs);
        }     

        btnImportarDados.setEnabled(false);
        btnNovo.setEnabled(false);
        btnGerarExcel.setEnabled(false);
        
    }

    private void txtPESQUISAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPESQUISAKeyReleased
        //filtrar o nome conforme o usuario for digitando
        filtrarPorDigitacao(txtPESQUISA.getText());
    }//GEN-LAST:event_txtPESQUISAKeyReleased

    private void gravarNovoRegistro() {
        //pegando os valores que estão nos edits
        getValoresDosEdits();

        //setando os valores dos edits no objeto mod
        objModeloImpressora.setUnidadeid(codigoDepartamento);
        objModeloImpressora.setModeloid(codigoTipoModelo);
        objModeloImpressora.setSerie(serie);
        objModeloImpressora.setIp(ip);
        objModeloImpressora.setObs(obs);
        
        if (txtUNIDADE.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos com (*) que são obrigatórios!", "Atenção campo vazio!", 2);
        } else {
            //gravando no banco de dados, antes verifica se o nome já esta cadastrado e não grava se isso acontecer     
            if (umMetodo.temDuplicidadeDeCadastro("tblImpressorasContrato", "serie", serie)) {
                JOptionPane.showMessageDialog(null,"Atenção já existe uma impressora com série "+ serie +" cadastrada!, verifique se for o caso reative!","Duplicidade na série!",2); 
            } else {
                if(ctrlImpressoraContrato.salvarImpressoraContrato(objModeloImpressora)){                                
                    JOptionPane.showMessageDialog(null, "Impressora cadastrada com sucesso!");
                    umGravarLog.gravarLog("cadastro da Impressora de Contrato serie n."+serie);    
                    PreencherTabelaATIVOS(sqlATIVOS);
                }
            } 
        }
        
    }

    private void gravarEdicaoRegistro() {
        //pegando os valores que estão nos edits
        getValoresDosEdits();
        
        codigoDepartamento = umMetodo.getCodigoPassandoString("tbldepartamentos", "nome", unidade);
        codigoTipoModelo   = umMetodo.getCodigoPassandoString("tblmodelos", "modelo", modelo);
                
        //setando os valores dos edits no objeto mod  
        objModeloImpressora.setCodigo(codigo);
        objModeloImpressora.setUnidadeid(codigoDepartamento);
        objModeloImpressora.setModeloid(codigoTipoModelo);
        objModeloImpressora.setSerie(txtSERIE.getText());
        objModeloImpressora.setIp(txtIP.getText()); 
        objModeloImpressora.setStatus(status);
        objModeloImpressora.setObs(obs); 
                             
        if(ctrlImpressoraContrato.AtualizarImpressoraContrato(objModeloImpressora))
        {
                 JOptionPane.showMessageDialog(null,"Os dados foram atualizados com sucesso!"); 
                 umGravarLog.gravarLog("atualizacao no cadastro da impressora serie "+serie);           
        }else{
                 JOptionPane.showMessageDialog(null, "Não foi possível executar a atualização do registro!");
        }       
       
        PreencherTabelaATIVOS(sqlATIVOS);
        
    }

    public void reativarRegistro(String sTabela, int iCodigo) {
        //coloque este metodo local por conta de estar atualizando duas tabelas ao mesmo tempo e sairiam duas msgs se usasse o metodo publico
        umaConexao.conectar();
        sql = "UPDATE " + sTabela + " SET status='ATIVO' WHERE codigo = " +iCodigo+ " ";
        umaConexao.ExecutarAtualizacaoSQL(sql);
        try {
             //somente grava a alteração        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível reativar o registro!\nErro:" + e + ", o sql passado foi \n" + sql);
        } finally {
            umaConexao.desconectar();
        }
    }

    private void gravarReativacaoRegistro() {
        if (umMetodo.ConfirmouOperacao("Confirma a reativação do registro selecionado?", "Reativar registro")) {
            reativarRegistro("tblImpressorasContrato", codigoRegSelecionado);
            umGravarLog.gravarLog("reativacao da impressora serie "+serie);  
            JOptionPane.showMessageDialog(null, "Registro reativado com sucesso!");  
        }         
    }

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        if (cadastrando) {
            gravarNovoRegistro();
        } else if (editando) {
            gravarEdicaoRegistro();
        } else if (reativando) {
            gravarReativacaoRegistro();
        }
        Leitura();
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        Leitura();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed
           
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if(umabiblio.permissaoLiberada()){
            cadastrando=true;        
            HabilitarDesabilitarBotoes(false);
            btnImportarDados.setEnabled(false);
            btnVoltar.setText("Cancelar");
            btnGerarExcel.setEnabled(false);
            umMetodo.limparTodosCampos(jBoxDados);
            txtPESQUISA.setText(null);   
            txtUNIDADE.setEditable(false);
            txtCODIGO.setEditable(false);        
            txtCODIGO.setText(String.valueOf(umMetodo.mostrarProximoCodigo(tabela)));
            cmbStatus.removeAllItems();
            cmbStatus.addItem("ATIVO");
            cmbStatus.setSelectedIndex(0);
            PreencherTabelaATIVOS(sqlVazia);        

            //ABRE A LISTA DE DEPARTAMENTOS
            tabela_da_lista   = "TBLDEPARTAMENTOS";
            F_LISTAPADRAO frm = new F_LISTAPADRAO(new javax.swing.JFrame(), true);
            frm.setVisible(true);                    
            txtUNIDADE.setText(nomeDepartamento);  
            txtMODELO.requestFocus(); 
        }
       
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(umabiblio.permissaoLiberada()){
            Edicao();
            txtSERIE.setEditable(true);
            contador    = 0;
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void ImportarTXT() {
        //inicializando as variaveis dos campos a serem gravados
        int totalLinhas = 0;
        RetornarQdeLinhasDoTxt qdeLinhas = new RetornarQdeLinhasDoTxt();

        //setando o caminho do arquivo TXT no edit do formulario apenas para mostrar o arquivo que esta sendo importado
        SelecionarArquivoTexto select = new SelecionarArquivoTexto();
        caminhoTXT = select.ImportarTXT();

        if (caminhoTXT != null) {
            //setando o caminho do arquivo TXT na variavel caminhoTXT para pegar os valores                   
            totalLinhas = qdeLinhas.retornaNumLinhasDoTxt(caminhoTXT);
             //JOptionPane.showMessageDialog(null, "Qde de linhas do arquivo...: "+String.valueOf(totalLinhas));

            //criando uma variavel arquivo do tipo File e setando o caminho do arquivo TXT nela
            File arquivo = new File(caminhoTXT);
            try {
                FileReader ler = new FileReader(arquivo);
                BufferedReader lerBuf = new BufferedReader(ler);
                linha = lerBuf.readLine();

                while (linha != null) {
                    unidade  = linha.split(";")[0];
                    modelo   = linha.split(";")[1];
                    serie    = linha.split(";")[2];
                    ip       = linha.split(";")[3];
                    obs      = linha.split(";")[4];

                    //mostrando os valores no console
                    //System.out.println("NOME.....:"+nome+"\nRF.......:"+rf+"\nLOTAÇÃO..:"+lotacao+"\n");     
                    //setando os valores no objeto do modelo

                    objModeloImpressora.setUnidadeid(codigoDepartamento);
                    objModeloImpressora.setModeloid(codigoTipoModelo);
                    objModeloImpressora.setSerie(serie);
                    objModeloImpressora.setIp(ip);
                    objModeloImpressora.setObs("");                    
                    
                    //gravando no banco de dados, antes verifica se o rf já esta cadastrado e não grava se isso acontecer
                    if (umMetodo.temDuplicidadeDeCadastro("tblImpressorasContrato", "serie", serie)) {
                        //JOptionPane.showMessageDialog(null,"O LocalTrabalhosComLocalTrabalho "+nome+" já esta cadastrado!");
                    } else {
                            ctrlImpressoraContrato.salvarImpressoraContrato(objModeloImpressora);
                    }                    
                                  
                    //lendo a proxima linha
                    linha = lerBuf.readLine();

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao tentar ler o arquivo!");
            }
        }
        JOptionPane.showMessageDialog(null, "Os dados foram importados com sucesso!");

    }


    private void btnImportarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarDadosActionPerformed
        if(umabiblio.permissaoLiberada()){
            ImportarTXT();
            btnVoltarActionPerformed(evt);
        }

    }//GEN-LAST:event_btnImportarDadosActionPerformed

    private void mostrarDadosRegSelecionado(int iCodigo) {
        //seta o nivel de acesso do usuario ao clicar na tabela e mostra a seção do mesmo
        sql = "select c.*, m.modelo, d.nome as unidade from tblImpressorasContrato c, tblmodelos m,  tbldepartamentos d "
            + "where c.unidadeid=d.codigo and c.modeloid=m.codigo and c.codigo=" + iCodigo + " order by d.nome,c.serie,m.modelo";
        
        umaConexao.conectar();
        umaConexao.ExecutarPesquisaSQL(sql);
        try {
            umaConexao.rs.first();
            //mostrando os dados do registro selecionado nos edits
            txtCODIGO.setText(String.valueOf(umaConexao.rs.getInt("codigo")));
            txtUNIDADE.setText(umaConexao.rs.getString("unidade"));
            txtMODELO.setText(umaConexao.rs.getString("modelo"));
            txtSERIE.setText(umaConexao.rs.getString("serie"));
            txtIP.setText(umaConexao.rs.getString("ip"));
            txtOBS.setText(umaConexao.rs.getString("obs"));

            //mostro o status
            cmbStatus.removeAllItems();
            cmbStatus.addItem(umaConexao.rs.getString("status"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar selecionar os dados do registro!\nERRO:" + ex.getMessage());
        } finally {
            umaConexao.desconectar();
        }

        //controla apresentacao dos edits
        txtPESQUISA.setEditable(false);
        txtCODIGO.setEnabled(true);
        txtCODIGO.setEditable(false);
        txtUNIDADE.setEnabled(true);
        txtUNIDADE.setEditable(false);
        txtMODELO.setEnabled(true);
        txtMODELO.setEditable(false);
        txtSERIE.setEnabled(true);
        txtSERIE.setEditable(false);
        txtIP.setEnabled(true);
        txtIP.setEditable(false);
        txtOBS.setEnabled(true);
        txtOBS.setEditable(false);

    }

    private void btnGerarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarExcelActionPerformed
        lstListaCampos.clear();
        F_SELECIONARCAMPOS frm = new F_SELECIONARCAMPOS(new javax.swing.JFrame(), true);
        frm.setVisible(true);
    }//GEN-LAST:event_btnGerarExcelActionPerformed

    private void txtPESQUISAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPESQUISAMouseClicked
        txtPESQUISA.selectAll();
    }//GEN-LAST:event_txtPESQUISAMouseClicked

    private void jTabelaATIVOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaATIVOSMouseClicked
        /*passando o codigo do patrimonio para o metodo => (int) jTabelaATIVOS1ATIVOS1.getValueAt(jTabelaATIVOS1ATIVOS1.getSelectedRow(), 0)
         onde o zero é a coluna que detem o valor que se deseja nesse caso o codigo (coluna 0)*/

        codigoRegSelecionado = (int) jTabelaATIVOS.getValueAt(jTabelaATIVOS.getSelectedRow(), 0);
        mostrarDadosRegSelecionado(codigoRegSelecionado);
        cadastrando = false;
        editando    = false;
        contador    = 1;
        btnEditar.setEnabled(true);
        btnGravar.setEnabled(false);
        btnNovo.setEnabled(false);
        btnVoltar.setEnabled(true);
        btnSair.setEnabled(false);
        btnImportarDados.setEnabled(false);
        btnGerarExcel.setEnabled(false);

        if (umMetodo.tabelaTemInativos(tabela)) {
            jTabbedPane3.setEnabledAt(1, true);
        } else {
            jTabbedPane3.setEnabledAt(1, false);
        }

    }//GEN-LAST:event_jTabelaATIVOSMouseClicked

    private void jTabelaINATIVOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaINATIVOSMouseClicked
        //passando o codigo do patrimonio para o metodo => (int) jTabelaATIVOS1ATIVOS1.getValueAt(jTabelaATIVOS1ATIVOS1.getSelectedRow(), 0) é igual ao codigo do patrimonio selecionado
        codigoRegSelecionado = (int) jTabelaINATIVOS.getValueAt(jTabelaINATIVOS.getSelectedRow(), 0);
        mostrarDadosRegSelecionado(codigoRegSelecionado);

        if (umMetodo.statusRegistroInativo(codigoRegSelecionado, tabela)) {
            btnGravar.setEnabled(true);
            btnNovo.setEnabled(false);
            btnVoltar.setEnabled(true);
            btnGerarExcel.setEnabled(false);
            btnImportarDados.setEnabled(false);
            btnSair.setEnabled(false);
        }
    }//GEN-LAST:event_jTabelaINATIVOSMouseClicked

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        //se clicou na aba INATIVOS
        if (jTabbedPane3.getSelectedIndex() == 1) {
            consultandoInativos = true;
            reativando          = true;
            editando            = false;
            cadastrando         = false;
            btnVoltar.setEnabled(true);
            btnGravar.setEnabled(false);
            btnNovo.setEnabled(false);
            btnGerarExcel.setEnabled(false);
            btnImportarDados.setEnabled(false);
            btnGravar.setText("REATIVAR");
            umMetodo.limparTodosCampos(jBoxDados);
            jTabbedPane3.setEnabledAt(0, false); //desabilitando a pane ATIVOS
            txtPESQUISA.setEditable(true);
            txtPESQUISA.requestFocus();
        }
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void txtSERIEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSERIEKeyPressed
         //se teclar enter estando dentro do txtNOME vá para o campo do rf
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIP.requestFocus();
        }
    }//GEN-LAST:event_txtSERIEKeyPressed

    private void txtIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPKeyPressed
         //se teclar enter estando dentro do txtNOME vá para o campo do rf
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOBS.requestFocus();
        }
    }//GEN-LAST:event_txtIPKeyPressed

    private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
         alterouStatus = true;
    }//GEN-LAST:event_cmbStatusItemStateChanged

    private void txtMODELOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMODELOFocusGained
    
        if(contador == 0){
            //ABRE A LISTA DE MODELOS            
            tabela_da_lista   = "TBLMODELOS";
            F_LISTAMODELOSCONTRATO frm = new F_LISTAMODELOSCONTRATO(new javax.swing.JFrame(), true);
            frm.setVisible(true);                    
            txtMODELO.setText(TipoModelo);  
            txtMODELO.setForeground(Color.red);
            txtSERIE.requestFocus();
            contador++;
        }
        
    }//GEN-LAST:event_txtMODELOFocusGained

    private void txtUNIDADEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUNIDADEMouseClicked
       //JOptionPane.showMessageDialog(null, editando);
        if(editando){
            //ABRE A LISTA DE UNIDADES
            tabela_da_lista   = "TBLDEPARTAMENTOS";
            F_LISTAPADRAO frm = new F_LISTAPADRAO(new javax.swing.JFrame(), true);
            frm.setVisible(true);                    
            txtUNIDADE.setText(nomeDepartamento); 
            txtUNIDADE.setForeground(Color.red);
        }
        
        txtUNIDADE.selectAll();
    }//GEN-LAST:event_txtUNIDADEMouseClicked

    private void txtMODELOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMODELOMouseClicked
        if(editando){
            //ABRE A LISTA DE UNIDADES
            tabela_da_lista   = "TBLMODELOS";
            F_LISTAMODELOSCONTRATO frm = new F_LISTAMODELOSCONTRATO(new javax.swing.JFrame(), true);
            frm.setVisible(true);                    
            txtMODELO.setText(TipoModelo);   
            txtMODELO.setForeground(Color.red);
        }
        txtMODELO.selectAll();
    }//GEN-LAST:event_txtMODELOMouseClicked

    private void txtUNIDADEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUNIDADEMouseMoved
        //alterando o cursor para o formato de mão ao passar o mouse enquanto edita o campo
        if(editando){
            txtUNIDADE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // mao
        }
    }//GEN-LAST:event_txtUNIDADEMouseMoved

    private void txtMODELOMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMODELOMouseMoved
        //alterando o cursor para o formato de mão ao passar o mouse enquanto edita o campo
        if(editando){
            txtMODELO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // mao
        }
    }//GEN-LAST:event_txtMODELOMouseMoved

    private void txtSERIEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSERIEMouseClicked
        txtSERIE.selectAll();
    }//GEN-LAST:event_txtSERIEMouseClicked

    private void txtIPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIPMouseClicked
        txtIP.selectAll();
    }//GEN-LAST:event_txtIPMouseClicked

    private void Edicao() {
        //metodo para quando usuario clicar em Editar
        boolean Habilitar   = true;
        Component[] c       = null;
        editando            = true;
        cadastrando         = false;
        btnNovo.setEnabled(!Habilitar);
        btnSair.setEnabled(!Habilitar);
        btnGravar.setEnabled(Habilitar);
        btnEditar.setEnabled(!Habilitar);
        btnVoltar.setEnabled(Habilitar);
        btnVoltar.setText("Cancelar");

        //mostra o titulo do formulario de acordo com o status
        umMetodo.mostrarTituloDoFormulario();

        //desabilitando os edits para edicao
        c = jBoxDados.getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setEnabled(Habilitar);
        }

        //popular a combo status com opcoes ATIVO pra manter inalterado e INATIVO caso deseje inativar
        cmbStatus.removeAllItems();
        cmbStatus.addItem("ATIVO");
        cmbStatus.addItem("INATIVO");

        PreencherTabelaATIVOS(sqlVazia);
        
        txtUNIDADE.setEditable(false);
        txtMODELO.setEditable(false);
        txtIP.setEditable(true);
        txtOBS.setEditable(true);
        
        //habilitando a pesquisa  e preenchendo a tabela se tiver registros
        c = jBoxPesquisar.getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setEnabled(!Habilitar);
        }
    }

    public void Leitura() {
        //formatacao inicial dos botoes ao abrir o formulario
        contador            = 0;
        boolean Habilitar   = true;
        Component[]c        = null;
        clicouNaTabela      = false;
        consultandoInativos = false;  
        editando            = false;
        cadastrando         = false;
        txtPESQUISA.setEditable(true);
        
        btnImportarDados .setEnabled(Habilitar);      
        btnNovo          .setEnabled(Habilitar);
        btnSair          .setEnabled(Habilitar);
        btnGravar        .setEnabled(!Habilitar);
        btnGravar        .setText("GRAVAR");
        btnEditar        .setEnabled(!Habilitar);
        btnVoltar        .setEnabled(!Habilitar);      
        btnGerarExcel    .setEnabled(Habilitar);  

        txtMODELO.setForeground(Color.blue);
        txtUNIDADE.setForeground(Color.blue);        
                        
        //mostra o titulo do formulario de acordo com o status
        this.setTitle(umMetodo.mostrarTituloDoFormulario()); 
        
        //setar o nome do lblNOME
        nomeTabelaSemTBL = "";
        nomeTabelaSemTBL = tabela.substring(3);        
        
        //desabilitando os edits para edicao
        c = jBoxDados.getComponents();
        for(int i=0; i<c.length; i++)
        {
            c[i].setEnabled(!Habilitar);  
            umMetodo.limparTodosCampos(this);
            txtPESQUISA.requestFocus();
        }
              
        //limpando os combos 
        cmbStatus       .setSelectedIndex(-1);
                          
       if(umMetodo.tabelaEstaVazia(tabela)){
             txtPESQUISA.setEnabled(false);
             btnGerarExcel.setEnabled(false);
        }else{
             txtPESQUISA.setEnabled(true);
             txtPESQUISA.setEditable(true);
             txtPESQUISA.requestFocus();
             jTabbedPane3.setSelectedIndex(0);
             
             //setando os primeiros registros das tabelas
             PreencherTabelaATIVOS(sqlATIVOS);
                //jTabelaATIVOS.addRowSelectionInterval(0,0);                
                
             if(umMetodo.tabelaTemInativos(tabela)){
                PreencherTabelaINATIVOS(sqlINATIVOS);
                jTabelaINATIVOS.addRowSelectionInterval(0,0);
                jTabbedPane3.setEnabledAt(1, true);
             }else{
                jTabbedPane3.setEnabledAt(1, false); 
             }   
            
        }       
              
        //padrao é a tabela de ATIVOS habilitada
        jTabbedPane3.setEnabledAt(0, true);
        
    }

    public void HabilitarDesabilitarBotoes(boolean Habilitar) {
        //ações para quando clicar em cada botão
        Component[] c = null;

        btnNovo.setEnabled(Habilitar);
        btnGravar.setEnabled(!Habilitar);
        btnEditar.setEnabled(Habilitar);
        btnVoltar.setEnabled(!Habilitar);
        btnSair.setEnabled(Habilitar);
        btnImportarDados.setEnabled(!Habilitar);

        //habilitando os edits para edicao
        c = jBoxDados.getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setEnabled(!Habilitar);
        }

        //desabilitando a pesquisa enquanto cadastra
        c = jBoxPesquisar.getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setEnabled(Habilitar);
        }

    }

    public void PreencherTabelaATIVOS(String sql) {
        umaConexao.conectar();

        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        String[] Colunas = new String[]{"Código", "Unidade", "Modelo", "Serie", "Ip"};
        umaConexao.ExecutarPesquisaSQL(sql);
        try {
            umaConexao.ExecutarPesquisaSQL(sql);
            while (umaConexao.rs.next()) {
                dados.add(new Object[]{
                    umaConexao.rs.getInt("codigo"),
                    umaConexao.rs.getString("unidade"),
                    umaConexao.rs.getString("modelo"),
                    umaConexao.rs.getString("serie"),
                    umaConexao.rs.getString("ip")
                });
                totalRegs = umaConexao.rs.getRow(); //passando o total de registros para o titulo
            };

            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabelaATIVOS.setModel(modelo);
            //define tamanho das colunas
            jTabelaATIVOS.getColumnModel().getColumn(0).setPreferredWidth(80);  //define o tamanho da coluna
            jTabelaATIVOS.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaATIVOS.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTabelaATIVOS.getColumnModel().getColumn(1).setResizable(false);
            jTabelaATIVOS.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTabelaATIVOS.getColumnModel().getColumn(2).setResizable(false);
            jTabelaATIVOS.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTabelaATIVOS.getColumnModel().getColumn(3).setResizable(false);
            jTabelaATIVOS.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTabelaATIVOS.getColumnModel().getColumn(4).setResizable(false);
            //define propriedades da tabela
            jTabelaATIVOS.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabelaATIVOS.setAutoResizeMode(jTabelaATIVOS.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabelaATIVOS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha 

        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\nErro: " + ex.getMessage());
        } finally {
            umaConexao.desconectar();
        }
        
    }

    public void PreencherTabelaINATIVOS(String sql) {
        umaConexao.conectar();

        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        String[] Colunas = new String[]{"Código", "Unidade", "Modelo", "Serie", "Ip"};
        umaConexao.ExecutarPesquisaSQL(sql);
        try {
            umaConexao.ExecutarPesquisaSQL(sql);
            while (umaConexao.rs.next()) {
                dados.add(new Object[]{
                    umaConexao.rs.getInt("codigo"),
                    umaConexao.rs.getString("unidade"),
                    umaConexao.rs.getString("modelo"),
                    umaConexao.rs.getString("serie"),
                    umaConexao.rs.getString("ip")
                });
                totalRegs = umaConexao.rs.getRow(); //passando o total de registros para o titulo
            };


            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabelaINATIVOS.setModel(modelo);
            //define tamanho das colunas
            jTabelaINATIVOS.getColumnModel().getColumn(0).setPreferredWidth(80);  //define o tamanho da coluna
            jTabelaINATIVOS.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaINATIVOS.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTabelaINATIVOS.getColumnModel().getColumn(1).setResizable(false);
            jTabelaINATIVOS.getColumnModel().getColumn(2).setPreferredWidth(320);
            jTabelaINATIVOS.getColumnModel().getColumn(2).setResizable(false);
            jTabelaINATIVOS.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTabelaINATIVOS.getColumnModel().getColumn(3).setResizable(false);
            jTabelaINATIVOS.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTabelaINATIVOS.getColumnModel().getColumn(4).setResizable(false);
            //define propriedades da tabela
            jTabelaINATIVOS.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabelaINATIVOS.setAutoResizeMode(jTabelaINATIVOS.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabelaINATIVOS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha 

        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\nErro: " + ex.getMessage());
        } finally {
            umaConexao.desconectar();
        }
       
    }

    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img_icones_forms/LogonDaPMSP.png")));
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
            java.util.logging.Logger.getLogger(F_IMPRESSORASCONTRATO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_IMPRESSORASCONTRATO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_IMPRESSORASCONTRATO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_IMPRESSORASCONTRATO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                F_IMPRESSORASCONTRATO dialog = new F_IMPRESSORASCONTRATO(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGerarExcel;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnImportarDados;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLayeredPane jBoxBotoes;
    private javax.swing.JLayeredPane jBoxDados;
    private javax.swing.JLayeredPane jBoxPesquisar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTabelaATIVOS;
    private javax.swing.JTable jTabelaINATIVOS;
    private javax.swing.JLabel lblNOME;
    private javax.swing.JLabel lblNOME1;
    private javax.swing.JLabel lblNOME2;
    private javax.swing.JLabel lblNOME3;
    private javax.swing.JTextField txtCODIGO;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtMODELO;
    private javax.swing.JTextField txtOBS;
    private javax.swing.JTextField txtPESQUISA;
    private javax.swing.JTextField txtSERIE;
    private javax.swing.JTextField txtUNIDADE;
    // End of variables declaration//GEN-END:variables

}
