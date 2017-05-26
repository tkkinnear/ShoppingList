package productmanager;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import filemanager.*;
import java.util.ArrayList;
import java.util.Collections;

public class ProductManager {

    private Map<String, Product> productList;
    private String path;
    private FileManager file;
    private String name;

    public ProductManager() {
        this.productList = new HashMap<>();
        this.path = "src\\costco.txt";
        this.name = "Costco";
        this.file = new FileManager(name, path);
        initialize();
    }

    public ProductManager(String name) {
        this.productList = new HashMap<>();
        this.path = "src\\" + name.toLowerCase().trim() + ".txt";
        this.name = name;
        this.file = new FileManager(name, path);
        initialize();
    }

    private void initialize() {
        List<String> startList = file.getList();
        for (String each : startList) {
            String[] item = each.split(":");
            try {
                Product product = new Product(item);
                productList.put(item[0].toLowerCase(), product);
            } catch (Exception e) {
                System.out.println("ProductManager(" + this.name + ") error");
                System.out.println(each);
            }
            //System.out.println(productList.get(item[0]) + " imported to active map");
        }
    }

    public String getName() {
        return this.name;
    }

    public Product getProduct(String item) {
        if (productList.containsKey(item)) {
            return productList.get(item);
        }
        //System.out.println(item + " not found in " + this.name  + " (productManager");
        return null;
    }

    public void setProducts(Map<String, Product> list) {
        productList = list;

    }

    public void addProductList(Map<String, Product> list) {
        for (String each : list.keySet()) {
            productList.put(each, list.get(each));
        }
    }

    public void updateProduct(Product p) {
        try {
            productList.put(p.getName(), p);
        } catch (Exception e) {
            System.out.println("PM: Update product: no listing. Adding: " + p.getName());
            this.addProduct(p);
        }

    }

    public boolean exists(String n){
        if (productList.containsKey(n)){
            return true;
        }
        return false;
    }
        
    public void addProduct(Product product) {
        productList.put(product.getName(), product);
    }

    public void addProduct(String name, double price, Department dept) {
        productList.put(name, new Product(name, price, dept));
    }

    public void removeProduct(String n) {
        productList.remove(n);
    }

    public Map<String, Product> getProducts() {
        return this.productList;
    }

    public void save() {
        file.save(getList());
    }
    
    public List<String> getList(){
        // returns a sorted list of all products toString values.  Useful for auditing.
        List<String> newList = new ArrayList<>();
        for (String each : sort()) {
            Product item = productList.get(each);
            newList.add(item.toString());
        }
        return newList;
    }

    public void print() {
        for (String each : getList()){
            System.out.println(each);
        }
    }

    private List<String> sort() {
        // returns a SORTED keySet for the stores product list
        List<String> sort = new ArrayList<>();
        for (String each : productList.keySet()) {
            sort.add(each);
        }
        Collections.sort(sort, new ProductComparator(productList));
        return sort;
    }

}
