/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.TrataClienteController;
import factory.Conector;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
/**
 *
 * @author lucsa
 */
public class Principal {
    //criando metodo main q exec o server
    public static void main(String[] args) {
      Connection con;
      con = Conector.getConnection();
        try {
            ServerSocket servidor = new ServerSocket(12345); 
            System.out.println("Servidor Conectado");
            
            ConectaServidor s1 = new ConectaServidor(servidor, con);
            //iniciando thread
            s1.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}

class ConectaServidor extends Thread{
    private ServerSocket servidor;
    private int idUnico = 0;
    private Connection con;

    public ConectaServidor(ServerSocket servidor, Connection con) {
        this.servidor = servidor;
        this.con = con;
    }

    @Override
    public void run() {
        try {
            while(true){
                Socket cliente = this.servidor.accept();
                System.out.println("um novo cliente conectou "+cliente);
                
                //criando entrada e saida
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                idUnico++;
                System.out.println("Inicializando uma thread para o cliente: "+idUnico);
                TrataClienteController trataCliente = new TrataClienteController(in, out, cliente, idUnico);
                trataCliente.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}