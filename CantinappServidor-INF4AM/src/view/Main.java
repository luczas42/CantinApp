/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ClientController;
import factory.Connector;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
/**
 *
 * @author lucsa
 */
public class Main {
    //criando metodo main q exec o server
    public static void main(String[] args) {
      Connection con;
      con = Connector.getConnection();
        try {
            ServerSocket servidor = new ServerSocket(12345); 
            System.out.println("Server connected");
            
            ServerConnect s1 = new ServerConnect(servidor, con);
            //iniciando thread
            s1.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}

class ServerConnect extends Thread{
    private ServerSocket server;
    private int uniqueId = 0;
    private Connection con;

    public ServerConnect(ServerSocket server, Connection con) {
        this.server = server;
        this.con = con;
    }

    @Override
    public void run() {
        try {
            while(true){
                Socket client = this.server.accept();
                System.out.println("A new client connected "+client);
                
                //criando entrada e saida
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                uniqueId++;
                System.out.println("Initializing client thread: "+uniqueId);
                ClientController clientController = new ClientController(in, out, client, uniqueId);
                clientController.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}