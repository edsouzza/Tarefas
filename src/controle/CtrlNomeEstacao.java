package controle;
import Dao.DAONomeEstacao;
import modelo.NomeEstacao;

public class CtrlNomeEstacao
{ 
    public boolean salvarNomeEstacao(NomeEstacao pEstacao) 
    { 
        return new DAONomeEstacao().salvarNomeEstacaoDAO(pEstacao);
    }    
    
    public boolean atualizarStatusNomeEstacao(NomeEstacao pEstacao) 
    {
        return new DAONomeEstacao().atualizarStatusDoNomeEstacaoDAO(pEstacao);
    }    
    
    public boolean atualizarStatusPeloNomeDaEstacao(NomeEstacao pEstacao) 
    {
        return new DAONomeEstacao().atualizarStatusPeloNomeDaEstacaoDAO(pEstacao);
    }    
   
    public boolean excluirNomeEstacao(int pCodigo) 
    {
        return new DAONomeEstacao().excluirNomeEstacaoDao(pCodigo);
    }   
    
    public NomeEstacao pesquisarNomeEstacao(NomeEstacao pNomeEstacao) 
    {
        return new DAONomeEstacao().pesquisarNomeEstacaoDAO(pNomeEstacao);
    }  
    
}