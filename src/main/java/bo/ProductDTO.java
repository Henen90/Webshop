package bo;

public class ProductDTO {
    private int id;
    private String name;
    private String descr;
    private float price;
    private String category;

    public ProductDTO(int id, String name, String descr, String category, float price) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.category = category;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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


}
