package bo;

import db.ProductDAO;

import java.util.Collection;

public class Product {
    private String name;
    private String descr;
    private int id;

    protected Product(int id, String name, String descr){
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", id=" + id +
                '}';
    }
}
