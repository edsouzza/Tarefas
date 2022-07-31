package Dao;
import static biblioteca.VariaveisPublicas.dataDoDia;
import static biblioteca.VariaveisPublicas.sql;
import conexao.ConnConexao;
import static biblioteca.VariaveisPublicas.totalRegs;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.ImpressoraContrato;
import javax.swing.JOptionPane;

public class DAOImpressorasContrato {

    ConnConexao conexao  = new ConnConexao();  
    
    public boolean salvarImpressoraContratoDAO(ImpressoraContrato pImpressoraContrato) 
    {
        conexao.conectar();
        try 
        {
            String sql = "INSERT INTO TBLIMPRESSORASCONTRATO (unidadeid,modeloid,serie,ip,status,obs) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setInt(1, pImpressoraContrato.getUnidadeid());  
            pst.setInt(2, pImpressoraContrato.getModeloid());  
            pst.setString(3, pImpressoraContrato.getSerie().toUpperCase());  //caso importe dados com textos em letras minusculas gravar em caixa alta
            pst.setString(4, pImpressoraContrato.getIp().toUpperCase());  //caso importe dados com textos em letras minusculas gravar em caixa alta
            pst.setString(5, "ATIVO");
            pst.setString(6, pImpressoraContrato.getObs().toUpperCase());  //caso importe dados com textos em letras minusculas gravar em caixa alta           
            pst.executeUpdate(); 
            pst.close(); 
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível executar o comando sql, \n"+e+", o sql passado foi \n"+sql);  
            return false;
        } finally {
            conexao.desconectar();
        }
    }
    
    public boolean AtualizarImpressoraContratoDAO(ImpressoraContrato pImpressoraContrato)
    {
        conexao.conectar();
        try
        {            
            String sql = "UPDATE TBLIMPRESSORASCONTRATO SET unidadeid=?, modeloid=?, serie=?, ip=?, status=?, obs=? WHERE codigo=?";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setInt(1, pImpressoraContrato.getUnidadeid());  
            pst.setInt(2, pImpressoraContrato.getModeloid());   
            pst.setString(3, pImpressoraContrato.getSerie());  
            pst.setString(4, pImpressoraContrato.getIp()); 
            pst.setString(5, pImpressoraContrato.getStatus()); 
            pst.setString(6, pImpressoraContrato.getObs()); 
            pst.setInt(7, pImpressoraContrato.getCodigo()); 
            pst.executeUpdate(); 
            pst.close(); 
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível atualizar o registro, \n "+e+" , o sql passado foi \n"+sql);  
            return false;
        }finally{
            conexao.desconectar();
        }
        
    }
    
   public boolean InativarTodasImpressorasContratoDAO() 
   {
        //Inativando as Impressoras de Contrato
        DateFormat df            = new SimpleDateFormat("dd/MM/yyyy");
        Date dataDia             = dataDoDia; 
        String motivoInativacao  = "\nContrato expirado em "+df.format(dataDia)+", foi assinado novo contrato com outra empresa."; 
        String obsInativacao     = "\nContrato expirado em "+df.format(dataDia)+", foi assinado novo contrato com outra empresa."; 
                
        conexao.conectar();
        try
        {
            sql = "update tblpatrimonios set status='INATIVO',motivo = '"+motivoInativacao+"', observacoes = '"+obsInativacao+"' where contrato='S' and tipoid=3";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);            
            pst.executeUpdate();
            pst.close();  
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível atualizar o registro, \n "+e+" , o sql passado foi \n"+sql);  
            return false;
        }finally{
            conexao.desconectar();
        }
        
        //PRECISA INATIVAR TODOS OS MODELOS TAMBÉM.
    } 
   
   public boolean temImpressorasContratoDAO() 
   {                        
        conexao.conectar();
        try
        {
            String sql1 = "select * from tblpatrimonios where tipoid=3 and contrato='S'";
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql1);            
            pst.executeQuery();
            totalRegs = conexao.rs.getRow(); //passando o total de registros para o titulo
            pst.close(); 
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível selecionar os registros das impressoras de contrato, \n "+e+" , o sql passado foi \n"+sql);  
            return false;
        }finally{
            conexao.desconectar();
        }
        
    } 
   
   public int qdeImpressorasContratoATIVOSDAO()
    {        
        conexao.conectar();
        sql = "select count(codigo) as total from tblpatrimonios p where tipoid=3 and contrato='S' and status='ATIVO'";
        conexao.ExecutarPesquisaSQL(sql);            
        try {
            if (conexao.rs.next())
               return conexao.rs.getInt("total");
            else
                return 0; 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a pesquisa! "+ex);
            return 0;
        }finally{
            conexao.desconectar();
        }                 
    }
      
    public boolean ExcluirImpressoraContratoDAO(int pCodigo)
    {
        conexao.conectar();    
        try
        {  
            String sql = "DELETE FROM TBLIMPRESSORASCONTRATO WHERE codigo = '" + pCodigo + "'";            
            conexao.ExecutarPesquisaSQL(sql);            
            return true;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possível excluir o registro, \n"+e+", o sql passado foi \n"+sql);
            return false;
       } finally {
            conexao.desconectar();
        }        
    }    
           
    public boolean RegistroDuplicado(ImpressoraContrato pImpressoraContrato)
    {
        conexao.conectar();                        
        try 
        {              
            String sql = "SELECT * FROM TBLIMPRESSORASCONTRATO WHERE serie = '"+pImpressoraContrato.getSerie()+"'";
            conexao.ExecutarPesquisaSQL(sql);            
            if(conexao.rs.next())
            {
                //tipo de msg = 1 é a de informacao msg = 2 é a de exclamação  msg = 3 é a de interrogação
                JOptionPane.showMessageDialog(null,"Atenção este tipo de despacho já esta cadastrado, verifique!","Duplicidade de cadastro!",2);                
                return true;
            }else{ return false; }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a pesquisa sobre duplicidade! "+ex);
             return false;
        }finally{
            conexao.desconectar();
        }
               
    }    
    
    public ImpressoraContrato pesquisarImpressoraContratoDAO(ImpressoraContrato pImpressoraContrato) 
    {       
        try
        {
            conexao.conectar();
            String sql = "SELECT * FROM TBLIMPRESSORASCONTRATO WHERE codigo = '" + pImpressoraContrato.getCodigo() + "' OR serie = '" + pImpressoraContrato.getSerie() + "'";           
            conexao.ExecutarPesquisaSQL(sql);
            while (conexao.rs.next()) {
                pImpressoraContrato.setCodigo(conexao.rs.getInt("codigo"));
                pImpressoraContrato.setUnidadeid(conexao.rs.getInt("unidadeid"));
                pImpressoraContrato.setModeloid(conexao.rs.getInt("modeloid"));
                pImpressoraContrato.setSerie(conexao.rs.getString("serie"));
                pImpressoraContrato.setIp(conexao.rs.getString("ip"));
                pImpressoraContrato.setStatus(conexao.rs.getString("status"));
                pImpressoraContrato.setObs(conexao.rs.getString("obs"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível executar o comando sql, \n"+e+", o sql passado foi \n"+sql); 
        } finally {
            conexao.desconectar();
        }
        return pImpressoraContrato;
    }         
}