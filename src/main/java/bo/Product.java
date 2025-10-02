package bo;


public class Product {
    private String name;
    private String descr;
    private int id;
    private String category;
    private float price;

    protected Product(int id, String name, String descr, String category, float price){
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.category = category;
        this.price=price;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
