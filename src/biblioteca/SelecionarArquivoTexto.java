/*
 * Abre opção para o usuário selecionar uma arquivo texto retornando o caminho completo do arquivo
 */
package biblioteca;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelecionarArquivoTexto {
    JFileChooser fileChooser = new JFileChooser();
    private long retornaQdRegs = 0;
    
    public String ImportarTXT(){
        
        fileChooser.setDialogTitle("Escolha o arquivo de texto");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
        fileChooser.setFileFilter(filtro);
        int resposta  = fileChooser.showOpenDialog(new JDialog());
        File file = new File("");
        
        if (resposta == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            
            // contando quantas linhas tem no arquivo primeiro pega o tamanho	   
            try {
                long tamanhoArquivo = file.length();
                FileInputStream fs = null;
                fs = new FileInputStream(file);
           
                DataInputStream in = new DataInputStream(fs);
                LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(in));
                lineRead.skip(tamanhoArquivo);

                // conta o numero de linhas do arquivo, começa com zero, por isso adiciona 1
                int numLinhas = lineRead.getLineNumber();
                setRetornaQdRegs(numLinhas);
                
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao tentar contar as linhas do arquivo!");
            }
            /*retorna somente o nome do arquivo
            txtARQUIVO.setText(file.getName());*/

            //retorna o caminho completo do arquivo
            return file.getAbsolutePath();
            
        } else if (resposta == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null,"Cancelado pelo usuário!");
            return null;
        }else{
            return null;
        }
    }
      

    /**
     * @return the retornaQdRegs
     */
    
    public int getRetornaQdRegs() {
        return (int) retornaQdRegs;
    }

    /**
     * @param retornaQdRegs the retornaQdRegs to set
     */
    public void setRetornaQdRegs(long retornaQdRegs) {
        this.retornaQdRegs = retornaQdRegs;
    }
    
}
