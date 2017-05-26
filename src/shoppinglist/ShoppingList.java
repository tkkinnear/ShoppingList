package shoppinglist;

import filemanager.FileManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import productmanager.*;

public class ShoppingList {

    private List<String> shoppingList;
    private String path;
    private FileManager source;

    public ShoppingList() {
        this.path = "src\\rawList.txt";
        this.source = new FileManager("rawList", path);
        this.shoppingList = new ArrayList<>();

        start();
    }

    private void start() {
        this.shoppingList = source.getList();

    }

    public List<String> getList() {
        return this.shoppingList;
    }

    public List<String> validateList(ProductManager products) {
        Map<String, Product> productList = products.getProducts();
        Iterator<String> iterator = shoppingList.iterator();
        List<String> confirmed = new ArrayList();
        List<String> failed = new ArrayList();

        while (iterator.hasNext()) {
            String item = iterator.next();
            if (productList.containsKey(item)) {
                //System.out.println("Confirmed item " + item);
                confirmed.add(item);
            } else {
                failed.add(item);
                //System.out.println("could not find " + item);
            }
        }
        shoppingList = confirmed;
        return failed;
    }

    public void add(String name) {
        shoppingList.add(name);

    }

    public void subTotal(String s) {
        shoppingList.add("Estimated cost: $" + s);
    }

    public String getCost(ProductManager store) {
        // subtotals the current shopping list.
        Map<String, Product> productList = store.getProducts();
        double total = 0;
        for (String each : shoppingList) {
            if (productList.containsKey(each)) {
                total += productList.get(each).getPrice();
            }
        }
        return String.format("%.2f", total);
    }

    public void save(ProductManager store) {
        FileManager outputFile = new FileManager("Shopping List", "src\\testOutput.txt");
        
        outputFile.save(printList(store));

    }

    public void sort(ProductManager store) {
        validateList(store);
        Collections.sort(shoppingList, new ProductComparator(store.getProducts()));
    }

    public void save(String path, ProductManager store) {
        FileManager outputFile = new FileManager("ShoppingList", path);
        outputFile.save(printList(store));
    }
    
    private int priceSpacing(ProductManager store){
        int spacing = 0;
        for (String each : shoppingList){
            int compare = store.getProduct(each).priceTag().length();
            if (compare > spacing){
                spacing = compare;
            }
        }
        return spacing;
    }

    private List<String> printList(ProductManager store) {
        List<String> printList = new ArrayList<String>();
        if (shoppingList.isEmpty()) {
            System.out.println("Nothing found in list");
            return null;
        } else {
            int space = priceSpacing(store);
            this.sort(store);
            printList.add(shoppingList.size() + " items. \nCost: $" + this.getCost(store));
            printList.add("");
            for (String each : shoppingList) {
                String spaces = "";
                if (store.getProduct(each).priceTag().length() < space){
                    for (int i = 0 ; i < space - store.getProduct(each).priceTag().length() ; i++){
                        spaces += " ";
                    }
                }
                printList.add("$" + store.getProduct(each).priceTag() + spaces + " - " + each);
            }
        }
        return printList;
    }
    
    public void print(ProductManager store){
        for (String each : printList(store)){
            System.out.println(each);
        }
    }
}
