package Dao;

import biblioteca.MetodosPublicos;
import static biblioteca.VariaveisPublicas.nomeDepartamento;
import conexao.ConnConexao;
import static biblioteca.VariaveisPublicas.sql;
import controle.CtrlNomeEstacao;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelo.NomeEstacao;

/**
 *
 * @author d631863
 */
public class DAONomeEstacao {

    ConnConexao         conexao                  = new ConnConexao();    
    CtrlNomeEstacao     umControleNomeEstacao    = new CtrlNomeEstacao();
    NomeEstacao         umModeloNomeEstacao      = new NomeEstacao();
    MetodosPublicos     umMetodo                 = new MetodosPublicos();
    
    public boolean salvarNomeEstacaoDAO(NomeEstacao pEstacao) 
    {
        conexao.conectar();
        try 
        {
            String sql = "INSERT INTO tblnomestacao (depto, nomestacao, numestacao, status) VALUES (?,?,?,?)";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setString(1, pEstacao.getDepto());
            pst.setString(2, pEstacao.getNomestacao());
            pst.setInt(3, pEstacao.getNumestacao());            
            pst.setString(4, pEstacao.getStatus());
            pst.executeUpdate(); 
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível executar o comando de inserção sql, \n"+e+", o sql passado foi \n"+sql);  
            return false;
        } finally {
            conexao.desconectar();
        }
    }
    
    public boolean atualizarStatusDoNomeEstacaoDAO(NomeEstacao pEstacao) 
    {
        conexao.conectar();
        try
        {
            String sql = "UPDATE tblnomestacao SET status=? WHERE codigo=?";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setString(1, pEstacao.getStatus());
            pst.setInt(2, pEstacao.getCodigo());
            pst.executeUpdate();
            pst.close();  
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível atualizar o registro, \n"+e+", o sql passado foi \n"+sql);  
            return false;
        }finally{
            conexao.desconectar();
        }
        
    }    
    
    public boolean atualizarStatusPeloNomeDaEstacaoDAO(NomeEstacao pEstacao) 
    {
        conexao.conectar();
        try
        {
            
            String sql = "UPDATE tblnomestacao SET status=? WHERE nomestacao=?";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setString(1, pEstacao.getStatus());
            pst.setString(2, pEstacao.getNomestacao());
            pst.executeUpdate();
            pst.close();  
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível atualizar o registro, \n"+e+", o sql passado foi \n"+sql);  
            return false;
        }finally{
            conexao.desconectar();
        }
        
    }    
           
    public boolean excluirNomeEstacaoDao(NomeEstacao pEstacao) 
    {
        try
        {            
            conexao.conectar();            
            sql = "DELETE FROM tblnomestacao WHERE codigo = "+pEstacao.getCodigo();     
            conexao.ExecutarAtualizacaoSQL(sql);      
            return true;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível excluir o registro, \n"+e+", o sql passado foi \n"+sql);
            return false;
       } finally {
            conexao.desconectar();
        }        
    } 
    
    public void indisponibilizarNomeEstacao(String pEstacao)
    {        
        int codigoEst = umMetodo.getCodigoPassandoString("TBLNOMESTACAO", "nomestacao", pEstacao);
        umModeloNomeEstacao.setCodigo(codigoEst);
        umModeloNomeEstacao.setStatus("INDISPONIVEL");
        umControleNomeEstacao.atualizarStatusNomeEstacao(umModeloNomeEstacao);      
    }
    
    public void disponibilizarNomeEstacao(String pEstacao)
    {        
        int codigoEst = umMetodo.getCodigoPassandoString("TBLNOMESTACAO", "nomestacao", pEstacao);
        umModeloNomeEstacao.setCodigo(codigoEst);
        umModeloNomeEstacao.setStatus("DISPONIVEL");
        umControleNomeEstacao.atualizarStatusNomeEstacao(umModeloNomeEstacao);      
    }
    
    public void cadastrarNomeComStatusIndisponivel(String pEstacao, String pDepto)
    {        
        umModeloNomeEstacao.setDepto(pDepto);
        umModeloNomeEstacao.setNomestacao(pEstacao); 
        umModeloNomeEstacao.setNumestacao(umMetodo.somenteDigitos(pEstacao));                            
        umModeloNomeEstacao.setStatus("INDISPONIVEL");
        umControleNomeEstacao.salvarNomeEstacao(umModeloNomeEstacao); 
        umMetodo.indisponibilizarStatusNomeEstacaoTMP(pEstacao); //tblnomestacaotmp
    }
                     
}