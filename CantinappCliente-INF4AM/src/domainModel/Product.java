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
    private float preco;
    private byte[] photo;

    public Product(String name, float preco, byte[] photo) {
        this.name = name;
        this.preco = preco;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    
    
}
