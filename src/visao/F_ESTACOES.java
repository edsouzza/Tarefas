package visao;

import Dao.DAONomeEstacao;
import conexao.ConnConexao;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import biblioteca.Biblioteca;
import biblioteca.ModeloTabela;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import biblioteca.MetodosPublicos;
import static biblioteca.VariaveisPublicas.contador;
import static biblioteca.VariaveisPublicas.totalRegs;
import controle.CtrlNomeEstacao;
import modelo.NomeEstacao;
import relatorios.GerarRelatorios;


public class F_ESTACOES extends javax.swing.JDialog  {

    ConnConexao        conexao                  = new ConnConexao();
    Biblioteca         umaBiblio                = new Biblioteca();
    MetodosPublicos    umMetodo                 = new MetodosPublicos();  
    NomeEstacao        umModeloNomeEstacao      = new NomeEstacao();
    CtrlNomeEstacao    umControleNomeEstacao    = new CtrlNomeEstacao();
    DAONomeEstacao     umEstacaoNomeEstacaoAO   = new DAONomeEstacao();
        
    String inicioRange, finalRange, estacaoInicial, estacaoFinal, strRange, sqlCmb, nomeSecao, nomeDepto, titulo, caminhoTXT, linha, scodigo, snomestacao = "";    
    int codDepto;
    boolean selecionouDepto;    
            

    public F_ESTACOES() 
    {
        initComponents();
        setResizable(false);   //desabilitando o redimencionamento da tela     
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar      
        Leitura();       
           
        umaBiblio.configurarBotoes(btnPesquisar);
        umaBiblio.configurarBotoes(btnImprimir);   
        
        //cofigurações 
        jTabelaESTACOES.setFont(new Font("TimesRoman", Font.BOLD, 12));
        jTabelaESTACOES.setForeground(Color.blue);        
        
        //Impede que formulario seja arrastado na tela
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                setEnabled(false);
                setEnabled(true);
            }
        });//fim addComponentListener      
        
    }  
    
    private void Leitura()
    {
       
        //Populando o combo com os nomes dos departamentos
        sqlCmb = "select nome from tbldepartamentos where status='ATIVO' order by nome";
        umMetodo.PreencherComboVariandoTipo(cmbFILTRARPORDEPTO, sqlCmb, "NOME");
        nomeDepto = cmbFILTRARPORDEPTO.getSelectedItem().toString();
        cmbFILTRARPORDEPTO.setSelectedIndex(-1); 
        
        btnPesquisar.setEnabled(false);
        btnLimparPesquisa.setEnabled(false);
        btnImprimir.setEnabled(false);
        contador  = 0; 
                
        titulo = "CONSULTAR ESTAÇÕES/MICROS POR DEPARTAMENTO";        
        this.setTitle(titulo);         
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        btnPesquisar = new javax.swing.JButton();
        cmbFILTRARPORDEPTO = new javax.swing.JComboBox<String>();
        btnImprimir = new javax.swing.JButton();
        btnLimparPesquisa = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabelaESTACOES = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setPreferredSize(new java.awt.Dimension(1024, 733));
        panelPrincipal.setLayout(null);

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_pesquisa.gif"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnPesquisar);
        btnPesquisar.setBounds(310, 10, 110, 33);

        cmbFILTRARPORDEPTO.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmbFILTRARPORDEPTO.setForeground(new java.awt.Color(51, 51, 255));
        cmbFILTRARPORDEPTO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<ESCOLHA A SEÇÃO>" }));
        cmbFILTRARPORDEPTO.setToolTipText("Escolha um departamento para filtrar");
        cmbFILTRARPORDEPTO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbFILTRARPORDEPTO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFILTRARPORDEPTOItemStateChanged(evt);
            }
        });
        panelPrincipal.add(cmbFILTRARPORDEPTO);
        cmbFILTRARPORDEPTO.setBounds(10, 10, 290, 30);

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_imprimir.gif"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnImprimir);
        btnImprimir.setBounds(550, 10, 110, 33);

        btnLimparPesquisa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimparPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_limpar.gif"))); // NOI18N
        btnLimparPesquisa.setText("Voltar");
        btnLimparPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimparPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparPesquisaActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnLimparPesquisa);
        btnLimparPesquisa.setBounds(430, 10, 110, 33);

        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_sair.gif"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnSair);
        btnSair.setBounds(670, 10, 110, 33);

        jTabelaESTACOES.setAutoCreateRowSorter(true);
        jTabelaESTACOES.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTabelaESTACOES.setToolTipText("");
        jTabelaESTACOES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(jTabelaESTACOES);

        panelPrincipal.add(jScrollPane3);
        jScrollPane3.setBounds(10, 60, 770, 510);

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 790, 600));

        setSize(new java.awt.Dimension(799, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    
    public void Pesquisar(String pDepto) 
    {                     
                
//        JOptionPane.showMessageDialog(rootPane,"CODIGO DO DEPTO : "+ String.valueOf(codDepto));                
//        JOptionPane.showMessageDialog(rootPane,"DEPARTAMENTO : "+ pDepto);                
        
        String sqlPesquisa = "select p.codigo, p.ip, p.estacao, s.nome as secao, t.*, c.nome as usuario from tblpatrimonios p, tblsecoes s, tbltipos t, tblclientes c "
             + "where p.tipoid=t.codigo and p.secaoid=s.codigo and c.codigo=p.clienteid and t.tipopatrimonio = 'PATRIMONIO' and (t.tipo='MICRO') "
             + "and p.status='ATIVO' and p.deptoid = "+codDepto+" order BY RIGHT('000000000000' || p.estacao, 12)";      
                                  
        PreencherTabelaESTACOES(sqlPesquisa);             
         
        btnPesquisar.setEnabled(false);
        btnImprimir.setEnabled(true);
        btnLimparPesquisa.setEnabled(true);   
        cmbFILTRARPORDEPTO.setEnabled(false);
        
        //JOptionPane.showMessageDialog(rootPane,"TOTAL DE REGISTROS ENCONTRADOS : "+ String.valueOf(totalRegs));        
        titulo = pDepto+" - TOTAL DE ESTAÇÕES/MICROS : "+totalRegs+"";          
        this.setTitle(titulo);       
        
        if(totalRegs == 0){
            btnImprimir.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Não foram encontrados registros para "+pDepto+"!","Nenhum registro encontrado!",2);
            btnLimparPesquisaActionPerformed(null);
        }
    }     
    
   private void LimparPesquisa()
   {
       String sqlDefault = "select p.codigo, p.ip, p.estacao, s.nome as secao, t.*, c.nome as usuario from tblpatrimonios p, tblsecoes s, tbltipos t, tblclientes c "
             + "where p.tipoid=t.codigo and p.secaoid=s.codigo and c.codigo=p.clienteid and t.tipopatrimonio = 'PATRIMONIO' and (t.tipo='MICRO') "
             + "and p.status='ATIVO' and s.deptoid = 0 order BY RIGHT('000000000000' || p.estacao, 12)"; 
       
       PreencherTabelaESTACOES(sqlDefault); //deptoid = 0 acima limpa a tabela
       
       btnLimparPesquisa.setEnabled(false);
       btnImprimir.setEnabled(false);
       cmbFILTRARPORDEPTO.setEnabled(true);
       cmbFILTRARPORDEPTO.setSelectedIndex(-1); 
       totalRegs = 0;  
       Leitura();
        
   }
      
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        
        nomeDepto = cmbFILTRARPORDEPTO.getSelectedItem().toString();
        codDepto  = umaBiblio.buscarCodigoGenerico("tbldepartamentos", "nome", cmbFILTRARPORDEPTO.getSelectedItem().toString());
        
        if(contador == 1)
        {
            Pesquisar(nomeDepto);            
        }                

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int idDepto = umMetodo.getCodigoPassandoString("tbldepartamentos", "nome", cmbFILTRARPORDEPTO.getSelectedItem().toString());
                
        GerarRelatorios objRel = new GerarRelatorios();
        try {
            objRel.imprimirPatrimoniosFiltradosPorDepartamento("relatorio/relpatrimoniospordepartamento.jasper",idDepto ,"MICRO","tblpatrimonios");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!\n"+e);                
        }    
        
        btnLimparPesquisaActionPerformed(null);
       
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        contador = 0;
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnLimparPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparPesquisaActionPerformed
       LimparPesquisa();
    }//GEN-LAST:event_btnLimparPesquisaActionPerformed

    private void cmbFILTRARPORDEPTOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFILTRARPORDEPTOItemStateChanged
         contador++;            
         //JOptionPane.showMessageDialog(rootPane,"VL CONTADOR : "+ String.valueOf(contador)); 
         if(contador == 1){
             btnPesquisar.setEnabled(true);
         }
    }//GEN-LAST:event_cmbFILTRARPORDEPTOItemStateChanged
     
      
    
    public void PreencherTabelaESTACOES(String sql) {
        conexao.conectar();
        ArrayList dados = new ArrayList();
        //para receber os dados das colunas(exibe os titulos das colunas)
        String[] Colunas = new String[]{"Código", "Estação", "Ip", "Seção", "Usuário"};
        try {
            conexao.ExecutarPesquisaSQL(sql);
            while (conexao.rs.next()) {
                dados.add(new Object[]{
                    conexao.rs.getInt("codigo"),
                    conexao.rs.getString("estacao"),
                    conexao.rs.getString("ip"),
                    conexao.rs.getString("secao"),
                    conexao.rs.getString("usuario")

                });
                totalRegs = conexao.rs.getRow();
            };
                        
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            jTabelaESTACOES.setModel(modelo);
            
            //define tamanho das colunas
            jTabelaESTACOES.getColumnModel().getColumn(0).setPreferredWidth(70);  //define o tamanho da coluna
            jTabelaESTACOES.getColumnModel().getColumn(0).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaESTACOES.getColumnModel().getColumn(1).setPreferredWidth(120);  //define o tamanho da coluna
            jTabelaESTACOES.getColumnModel().getColumn(1).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaESTACOES.getColumnModel().getColumn(2).setPreferredWidth(100);  //define o tamanho da coluna
            jTabelaESTACOES.getColumnModel().getColumn(2).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaESTACOES.getColumnModel().getColumn(3).setPreferredWidth(130);  //define o tamanho da coluna
            jTabelaESTACOES.getColumnModel().getColumn(3).setResizable(false);    //nao será possivel redimencionar a coluna 
            jTabelaESTACOES.getColumnModel().getColumn(4).setPreferredWidth(320);  //define o tamanho da coluna
            jTabelaESTACOES.getColumnModel().getColumn(4).setResizable(false);    //nao será possivel redimencionar a coluna 

            //define propriedades da tabela
            jTabelaESTACOES.getTableHeader().setReorderingAllowed(false);        //nao podera ser reorganizada
            jTabelaESTACOES.setAutoResizeMode(jTabelaESTACOES.AUTO_RESIZE_OFF);          //nao será possivel redimencionar a tabela
            jTabelaESTACOES.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //so podera selecionar apena uma linha  

        } catch (SQLException ex) {
            //apos a consulta acima abrir o formulario mesmo que a tabela esteja vazia  
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\nErro: " + ex.getMessage());
        } finally {
            conexao.desconectar();
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnImprimir;
    public javax.swing.JButton btnLimparPesquisa;
    public javax.swing.JButton btnPesquisar;
    public javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cmbFILTRARPORDEPTO;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTable jTabelaESTACOES;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
