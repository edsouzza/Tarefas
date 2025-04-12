package visao;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class F_BARRAPROGRESSOLENDOTXT extends JFrame {
    public JProgressBar barraProgresso;
    public JLabel label;

    public F_BARRAPROGRESSOLENDOTXT() {
        setTitle("Lendo Arquivo TXT...");
        setSize(400, 100);
        setLocationRelativeTo(null); // centraliza
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // impede fechar manualmente

        barraProgresso = new JProgressBar();
        barraProgresso.setStringPainted(true); // mostra % dentro da barra

        label = new JLabel("Iniciando leitura...");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout simples com BorderLayout
        setLayout(new BorderLayout());
        add(barraProgresso, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }

    public void atualizarProgressoPelaQdeRegs(int valorAtual, int valorMaximo) {
        barraProgresso.setMaximum(valorMaximo);  // define o máximo
        barraProgresso.setValue(valorAtual);     // atualiza valor atual
        label.setText("Processando linha " + valorAtual + " de " + valorMaximo);
    }
    
    public void atualizarProgressoPorPorcentagem(int progresso) {
        barraProgresso.setValue(progresso);
        label.setText("Carregando... " + progresso + "%");
    }

}

