package controle;
import Dao.DAOImpressorasContrato;
import modelo.ImpressoraContrato;

public class CtrlImpressoraContrato
{ 
    public boolean salvarImpressoraContrato(ImpressoraContrato pImpressoraContrato) 
    { 
        return new DAOImpressorasContrato().salvarImpressoraContratoDAO(pImpressoraContrato);
    }    
    
    public boolean AtualizarImpressoraContrato(ImpressoraContrato pImpressoraContrato) 
    {
        return new DAOImpressorasContrato().AtualizarImpressoraContratoDAO(pImpressoraContrato);
    }
       
    public boolean ExcluirImpressoraContrato(int pCodigo)
    {
        return new DAOImpressorasContrato().ExcluirImpressoraContratoDAO(pCodigo);
    }   
        
    public ImpressoraContrato pesquisarImpressoraContrato(ImpressoraContrato pImpressoraContrato) 
    {
        return new DAOImpressorasContrato().pesquisarImpressoraContratoDAO(pImpressoraContrato);
    } 
    
    public boolean InativarTodasImpressorasContrato()
    {
        return new DAOImpressorasContrato().InativarTodasImpressorasContratoDAO();
    } 
    
    public boolean temImpressorasContrato()
    {
        return new DAOImpressorasContrato().temImpressorasContratoDAO();
    } 
    
    public int qdeImpressorasContratoATIVOS()
    {
        return new DAOImpressorasContrato().qdeImpressorasContratoATIVOSDAO();
    } 
    
}