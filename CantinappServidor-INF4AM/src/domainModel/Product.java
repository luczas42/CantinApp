package domainModel;

import java.io.Serializable;

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
    
    
}
