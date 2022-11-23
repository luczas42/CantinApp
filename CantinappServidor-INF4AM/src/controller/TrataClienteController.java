/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 *
 * @author lucsa
 */
public class TrataClienteController extends Thread {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket s;
    private int idUnico;

    public TrataClienteController(ObjectInputStream in, ObjectOutputStream out, Socket s, int idUnico) {
        this.in = in;
        this.out = out;
        this.s = s;
        this.idUnico = idUnico;
    }

    @Override
    public void run() {
        String comando;
        System.out.println("Esperando comandos do cliente: "+idUnico);
//        try {
//            comando = (String) in.readObject();
//            while(!comando.equalsIgnoreCase("fim")){
//                //tratando comandos do cliente
//                System.out.println("Cliente "+idUnico+"enviou comando: "+comando);
//                
//                if(comando.equalsIgnoreCase("EfetuarLogin")){
//                    out.writeObject("Ok");
//                    Usuario user = (Usuario) in.readObject();
//                    
//                    //consultar no bd se o usuario existe
//                    
//                    UsuarioDao usdao = new UsuarioDao();
//                    
//                    Usuario userLogado = usdao.efetuarLogin(user);
//                    out.writeObject(userLogado);
//                    
//                } else if(comando.equalsIgnoreCase("MarcaLista")){
//                    MarcaDao mcdao = new MarcaDao();
//                    out.writeObject(mcdao.getLista());
//                }else if(comando.equalsIgnoreCase("MarcaListaNome")){
//                    out.writeObject("ok");
//                    String nome = (String) in.readObject();
//                    MarcaDao mcdao = new MarcaDao();
//                    out.writeObject(mcdao.getListaNome(nome));
//                }else if(comando.equalsIgnoreCase("MarcaInserir")){
//                    out.writeObject("ok");
//                    Marca m = (Marca) in.readObject();
//                    MarcaDao mcdao = new MarcaDao();
//                    System.out.println(m);
//                    if(mcdao.inserir(m) == -1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }else if(comando.equalsIgnoreCase("MarcaEditar")){
//                    out.writeObject("ok");
//                    Marca m = (Marca) in.readObject();
//                    System.out.println(m);
//                    MarcaDao mcdao = new MarcaDao();
//                    if(mcdao.editar(m)== -1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }else if(comando.equalsIgnoreCase("MarcaExcluir")){
//                    out.writeObject("ok");
//                    Marca m = (Marca) in.readObject();
//                    System.out.println(m);
//                    MarcaDao mcdao = new MarcaDao();
//                    if(mcdao.excluir(m)==-1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }else if(comando.equalsIgnoreCase("BikeLista")){
//                    BikeDao bkdao = new BikeDao();
//                    out.writeObject(bkdao.getLista());
//                }else if(comando.equalsIgnoreCase("BikeInserir")){
//                    out.writeObject("ok");
//                    Bike b = (Bike) in.readObject();
//                    BikeDao bkdao = new BikeDao();
//                    if(bkdao.inserir(b)==-1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }else if(comando.equalsIgnoreCase("BikeAlterar")){
//                    out.writeObject("ok");
//                    Bike b = (Bike) in.readObject();
//                    BikeDao bkdao = new BikeDao();
//                    if(bkdao.alterar(b)==-1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }else if(comando.equalsIgnoreCase("BikeExcluir")){
//                    out.writeObject("ok");
//                    Bike b = (Bike) in.readObject();
//                    BikeDao bkdao = new BikeDao();
//                    if(bkdao.excluir(b)==-1){
//                        out.writeObject("ok");
//                    }else{
//                        out.writeObject("nok");
//                    }
//                }
//                //relendo comando
//                comando = (String)in.readObject();
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        //veio fim
        try {
            System.out.println("Cliente"+idUnico+"desconectou");
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
