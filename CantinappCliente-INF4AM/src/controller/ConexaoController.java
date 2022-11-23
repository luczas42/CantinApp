/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author lucsa
 */
public class ConexaoController {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    //prox atributo armazena user 
//    public Usuario usuario;

    public ConexaoController(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }
    
//    public Usuario efetuarLogin(Usuario user){
//        // implementar comunicacao co servidor
//        String msg;
//        try {
//            out.writeObject("EfetuarLogin");    
//            msg = (String) in.readObject(); //le ok
//            out.writeObject(user);//envia user
//            Usuario usrlogin = (Usuario) in.readObject();
//            return usrlogin;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } 
//    }
//    
//    public ArrayList<Marca> getMarcaLista(){
//        try {
//            out.writeObject("MarcaLista");
//            return (ArrayList<Marca>) in.readObject();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    } 
//    
//        public ArrayList<Bike> getBikeLista(){
//        try {
//            out.writeObject("BikeLista");
//            return (ArrayList<Bike>) in.readObject();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    } 
//    
//    public ArrayList<Marca> getMarcaListaNome(String pesquisa){
//        String msg;
//        try {
//            out.writeObject("MarcaListaNome");
//            msg = (String) in.readObject();
//            out.writeObject(pesquisa);
//            return (ArrayList<Marca>) in.readObject();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    public boolean marcaInserir(Marca m){
//        String msg;
//        try {
//            out.writeObject("MarcaInserir");
//            msg = (String) in.readObject();
//            out.writeObject(m);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//        public boolean marcaEditar(Marca m){
//        String msg;
//        try {
//            out.writeObject("MarcaEditar");
//            msg = (String) in.readObject();
//            out.writeObject(m);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//    public boolean marcaExcluir(Marca m){
//        String msg;
//        try {
//            out.writeObject("MarcaExcluir");
//            msg = (String) in.readObject();
//            out.writeObject(m);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//        public boolean bikeInserir(Bike b){
//        String msg;
//        try {
//            out.writeObject("BikeInserir");
//            msg = (String) in.readObject();
//            out.writeObject(b);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        
//        public boolean bikeAlterar(Bike b){
//        String msg;
//        try {
//            out.writeObject("BikeAlterar");
//            msg = (String) in.readObject();
//            out.writeObject(b);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//        
//        public boolean bikeExcluir(Bike b){
//        String msg;
//        try {
//            out.writeObject("BikeExcluir");
//            msg = (String) in.readObject();
//            out.writeObject(b);
//            msg = (String) in.readObject();
//            if(msg.equals("ok")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
