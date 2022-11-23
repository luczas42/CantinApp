/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucsa
 */
public class ProductDao {

    private Connection con;
    private int codigo;
    private String modelo;
    private String marca;
    private String preco;
    private String dataLancamento;
    private byte[] imagem;

    public ProductDao() {
        con = Conector.getConnection();
    }

//    public ArrayList<Bike> getLista() {
//        PreparedStatement stmt = null;
//        ArrayList<Bike> listaBike = new ArrayList<>();
//
//        try {
//            String sql = "select * from bike join marca on (marca.codmarca = bike.codmarca)";
//
//            //criar stmt e trocar param
//            stmt = con.prepareStatement(sql);
//            //exe script
//
//            ResultSet res = stmt.executeQuery();
//
//            //se tem resultado
//            while (res.next()) {
//                Marca m = new Marca(res.getInt("codmarca"), res.getString("nomemarca"));
//                Bike b = new Bike(res.getInt("codbike"), res.getString("modelo"), m, res.getFloat("preco"), res.getDate("datalancamento"), res.getBytes("imagem"));
//                System.out.println(m);
//
//                //adiciona marca na lista
//                listaBike.add(b);
//            }
//            res.close();
//            stmt.close();
//            con.close();
//            return listaBike;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public int inserir(Bike b) {
//        PreparedStatement stmt = null;
//        try {
//            try {
//                con.setAutoCommit(false);
//                String sql = "insert into bike(modelo, codmarca, preco, imagem, datalancamento) values (?,?,?,?,?)";
//                stmt = con.prepareStatement(sql);
//
//                stmt.setString(1, b.getModelo());
//                stmt.setInt(2, b.getMarca().getCodMarca());
//                stmt.setDouble(3, b.getPreco());
//                stmt.setBytes(4, b.getImagem());
//                stmt.setDate(5, new java.sql.Date(b.getDataLancamento().getTime()));
//
//                stmt.execute();
//                con.commit();
//                return -1;
//            } catch (SQLException e) {
//                try {
//                    con.rollback();
//                    e.printStackTrace();
//                    return e.getErrorCode();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    return ex.getErrorCode();
//                }
//            }
//        } finally {
//            try {
//                //garante q sera executado mesmo com erro
//                stmt.close();
//                con.setAutoCommit(true);
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public int alterar(Bike b) {
//        PreparedStatement stmt = null;
//        try {
//            try {
//                con.setAutoCommit(false);
//                String sql = "update bike set preco = ?, modelo = ?, codmarca = ?, imagem = ?, datalancamento = ? where codbike = ?";
//                stmt = con.prepareStatement(sql);
//
//                stmt.setString(1, b.getModelo());
//                stmt.setInt(2, b.getMarca().getCodMarca());
//                stmt.setDouble(3, b.getPreco());
//                stmt.setBytes(4, b.getImagem());
//                stmt.setDate(5, new java.sql.Date(b.getDataLancamento().getTime()));
//                stmt.setInt(6, b.getCodBike());
//
//                stmt.execute();
//                con.commit();
//                return -1;
//            } catch (SQLException e) {
//                try {
//                    con.rollback();
//                    e.printStackTrace();
//                    return e.getErrorCode();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    return ex.getErrorCode();
//                }
//            }
//        } finally {
//            try {
//                //garante q sera executado mesmo com erro
//                stmt.close();
//                con.setAutoCommit(true);
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public int excluir(Bike b) {
//        PreparedStatement stmt = null;
//        try {
//            try {
//                con.setAutoCommit(false);
//
//                String sql = "delete from bike where codbike = ?";
//
//                stmt = con.prepareStatement(sql);
//                
//                stmt.setInt(1, b.getCodBike());
//                
//                stmt.execute();
//                con.commit();
//                return -1;
//            } catch (SQLException e) {
//                try {
//                    con.rollback();
//                    e.printStackTrace();
//                    return e.getErrorCode();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    return ex.getErrorCode();
//                }
//            }
//        } finally {
//            try {
//                //garante q sera executado mesmo com erro
//                stmt.close();
//                con.setAutoCommit(true);
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
