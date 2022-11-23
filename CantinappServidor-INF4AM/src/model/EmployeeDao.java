/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import factory.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucsa
 */
public class EmployeeDao {
    
    private Connection con;
    
    public EmployeeDao() {
        con = Connector.getConnection();
    }
    
    //metodo que retorna todas as marcas cadastradas
//    public ArrayList<Marca> getLista(){
//        PreparedStatement stmt = null;
//        ArrayList<Marca> listaMarca = new ArrayList<>();
//        
//        try {
//            String sql = "select * from marca";
//            
//            //criar stmt e trocar param
//            
//           stmt = con.prepareStatement(sql);         
//           //exe script
//           
//           ResultSet res = stmt.executeQuery();
//           
//           //se tem resultado
//           while(res.next()){
//               Marca m = new Marca(res.getInt("codmarca"), res.getString("nomemarca"));
//               System.out.println(m);
//               
//               //adiciona marca na lista
//               listaMarca.add(m);
//           }
//           res.close();
//           stmt.close();
//           con.close();    
//           return listaMarca;        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        
//    } 
//        
//        public ArrayList<Marca> getListaNome(String nomeMarca){
//        PreparedStatement stmt = null;
//        ArrayList<Marca> listaMarca = new ArrayList<>();
//        
//        try {
//            String sql = "select * from marca where nomemarca like ?";
//            
//            //criar stmt e trocar param
//            
//           stmt = con.prepareStatement(sql);      
//           
//           stmt.setString(1,"%"+nomeMarca+"%");
//           //exe script
//           
//           ResultSet res = stmt.executeQuery();
//           
//           //se tem resultado
//           while(res.next()){
//               Marca m = new Marca(res.getInt("codmarca"), res.getString("nomemarca"));
//               System.out.println(m);
//               
//               //adiciona marca na lista
//               listaMarca.add(m);
//           }
//           res.close();
//           stmt.close();
//           con.close();    
//           return listaMarca;        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//}
//        
//        public int inserir(Marca m){
//            PreparedStatement stmt = null;
//            try {
//                try {
//                    con.setAutoCommit(false);
//                    String sql = "insert into marca (nomemarca) values (?)";
//                    stmt = con.prepareStatement(sql);
//                    
//                    stmt.setString(1, m.getNomeMarca());
//                    
//                    stmt.execute();
//                    con.commit();
//                    
//                    return -1;
//                    
//                } catch (SQLException e) {
//                    try {
//                        con.rollback();
//                        e.printStackTrace();
//                        return e.getErrorCode();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        return ex.getErrorCode();
//                    }
//                }
//            } finally{
//                try {
//                    //garante q sera executado mesmo com erro
//                    stmt.close();
//                    con.setAutoCommit(true);
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        public int editar(Marca m){
//            PreparedStatement stmt = null;
//            try {
//                try {
//                    con.setAutoCommit(false);
//                    String sql = "update marca set nomemarca = ? where codmarca = ?";
//                    stmt = con.prepareStatement(sql);
//                    
//                    stmt.setString(1, m.getNomeMarca());
//                    stmt.setInt(2,m.getCodMarca());
//                    
//                    stmt.execute();
//                    con.commit();
//                    
//                    return -1;
//                    
//                } catch (SQLException e) {
//                    try {
//                        con.rollback();
//                        e.printStackTrace();
//                        return e.getErrorCode();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        return ex.getErrorCode();
//                    }
//                }
//            } finally{
//                try {
//                    //garante q sera executado mesmo com erro
//                    stmt.close();
//                    con.setAutoCommit(true);
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        
//        public int excluir(Marca m){
//            PreparedStatement stmt = null;
//            try {
//                try {
//                    con.setAutoCommit(false);
//                    String sql = "delete from marca where codmarca = ?";
//                    stmt = con.prepareStatement(sql);
//                    
//                    stmt.setInt(1,m.getCodMarca());
//                    
//                    stmt.execute();
//                    con.commit();
//                    
//                    return -1;
//                    
//                } catch (SQLException e) {
//                    try {
//                        con.rollback();
//                        e.printStackTrace();
//                        return e.getErrorCode();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        return ex.getErrorCode();
//                    }
//                }
//            } finally{
//                try {
//                    //garante q sera executado mesmo com erro
//                    stmt.close();
//                    con.setAutoCommit(true);
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
}
