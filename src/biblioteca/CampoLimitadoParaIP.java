package biblioteca;
//Limita a digitação dentro de jTextField somente com numeros
//EX de uso : txtPESQUISA.setDocument(new CampoLimitadoParaIP()); */

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class CampoLimitadoParaIP extends PlainDocument{
    @Override
    public void insertString(int Offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(Offset, str.toUpperCase().replaceAll("[^0-9^ ]", ""), attr);
    }
    public void replace(int Offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(Offset, str.toUpperCase().replaceAll("[^0-9^ ]", ""), attr);
    }
}

