package domainModel;

import java.io.Serializable;

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

    public static long getSerialversiouid() {
        return serialVersioUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    
    
    
}
