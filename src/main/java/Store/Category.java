package Store;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    //private Map<String, Product> products = new HashMap<>(); // Артикул, Продукт
    private List<Product> products = new ArrayList<>();

    public Category(String name) throws IllegalArgumentException {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Необходимо указать наименование категории");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }*/

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        //products.put(product.getArticul(), product);
        products.add(product);
    }
}
