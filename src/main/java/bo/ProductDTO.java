package bo;

public class ProductDTO {
    private String name;
    private String descr;
    private float price;
    private String category;

    public ProductDTO(String name, String descr, float price, String category) {
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.category = category;
    }

    public float getPrice() {
        return price;
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
