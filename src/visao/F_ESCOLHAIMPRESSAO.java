package visao;

import static biblioteca.VariaveisPublicas.numemoParaImprimir;
import static biblioteca.VariaveisPublicas.relPorDestino;
import static biblioteca.VariaveisPublicas.relDestinoMemo;
import javax.swing.JOptionPane;
import relatorios.GerarRelatorios;


public class F_ESCOLHAIMPRESSAO extends javax.swing.JFrame {
    
    public F_ESCOLHAIMPRESSAO() {
        initComponents();
        setResizable(false);  //desabilitando o redimencionamento da tela
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); //desabilitando o botao fechar      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImprimirSemChapa = new javax.swing.JButton();
        btnImprimirComChapa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulário de Impressão");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimirSemChapa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimirSemChapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_Ok1.gif"))); // NOI18N
        btnImprimirSemChapa.setText("SEM CHAPA");
        btnImprimirSemChapa.setToolTipText("");
        btnImprimirSemChapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirSemChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirSemChapaActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimirSemChapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 43));

        btnImprimirComChapa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimirComChapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TICK.PNG"))); // NOI18N
        btnImprimirComChapa.setText("COM CHAPA");
        btnImprimirComChapa.setToolTipText("");
        btnImprimirComChapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirComChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirComChapaActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimirComChapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 150, 43));

        setSize(new java.awt.Dimension(379, 141));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirSemChapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirSemChapaActionPerformed
        
        GerarRelatorios objRelSemChapa = new GerarRelatorios();
        try { 
            if(relPorDestino && relDestinoMemo.equals("BAIXA")){
               objRelSemChapa.imprimirPatrimoniosInserviveis("relatorio/relmemoinserviveissemchapa.jasper", numemoParaImprimir);     
            }else{
               objRelSemChapa.imprimirRelatorioPatrimoniosTransferidos("relatorio/relmemotransferidosemchapa.jasper", numemoParaImprimir);                
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de INSERVIVEIS!\n"+e);                
        }    
        dispose();        
        
    }//GEN-LAST:event_btnImprimirSemChapaActionPerformed

    private void btnImprimirComChapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirComChapaActionPerformed
        
        GerarRelatorios objRelComChapa = new GerarRelatorios();
        try {    
            if(relPorDestino && relDestinoMemo.equals("BAIXA")){
               objRelComChapa.imprimirPatrimoniosInserviveis("relatorio/relmemoinserviveiscomchapa.jasper", numemoParaImprimir);     
            }else{
               objRelComChapa.imprimirRelatorioPatrimoniosTransferidos("relatorio/relmemotransferidocomchapa.jasper", numemoParaImprimir);          
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de INSERVIVEIS!\n"+e);                
        }             
        dispose();
        
    }//GEN-LAST:event_btnImprimirComChapaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(F_IMPRESSAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(F_IMPRESSAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(F_IMPRESSAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(F_IMPRESSAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new F_IMPRESSAO().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirComChapa;
    private javax.swing.JButton btnImprimirSemChapa;
    // End of variables declaration//GEN-END:variables
}
