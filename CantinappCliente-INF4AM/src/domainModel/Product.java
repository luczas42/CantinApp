/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.io.Serializable;

/**
 *
 * @author aluno
 */
public class Product implements Serializable{
    private static final long serialVersioUID = 123456789L;
    private String name;
    private float price;
    private byte[] photo;

    public Product(String name, float price, byte[] photo) {
        this.name = name;
        this.price = price;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPreco() {
        return price;
    }

    public void setPreco(float preco) {
        this.price = preco;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    
    
}
