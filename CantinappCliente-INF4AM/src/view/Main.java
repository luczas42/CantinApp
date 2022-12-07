/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ConectionController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author lucsa
 */
public class Main {
    
    public static ConectionController ccont;
    
    public static void main(String[] args) {
        Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            socket = new Socket("localhost", 12345);
            
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connected to the server");
            
            ccont = new ConectionController(out, in);
            
//            FormLogin formLogin = new FormLogin();
//            formLogin.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}