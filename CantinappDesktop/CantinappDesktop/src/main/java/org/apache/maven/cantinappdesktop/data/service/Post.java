package org.apache.maven.cantinappdesktop.data.service;

public class Post {
    public String name;
    public Float price;

    public Post(String name, Float price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return this.name;
    }

    public Float getPrice() {
        return this.price;
    }

}
