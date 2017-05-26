package UserInterface;

import filemanager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import productmanager.*;
import java.util.Scanner;
import shoppinglist.ShoppingList;

public class ListManager {

    private final String name;
    private final ShoppingList shoppingList;
    private ProductManager store;
    private final FileManager saveFile;
    private final Scanner reader;
    private final GetCommand c;

    public ListManager() {
        this.name = "Tyler";
        this.shoppingList = new ShoppingList();
        this.store = new ProductManager();
        this.saveFile = new FileManager("Shopping List", "src\\testOutput.txt");
        this.reader = new Scanner(System.in);
        this.c = new GetCommand();

    }
    
    // THIS CLASS DOES TOO MUCH.  MOVE SOME FUNCTIONS TO SL AND PM.  
    // POSSIBLY CREATE NEW CLASS JUST FOR INPUTS. 

    private List<String> menu() {
        // MAIN MENU OPTIONS
        ArrayList<String> m = new ArrayList<>();
        m.add(store.getName().toUpperCase());
        m.add("");
        m.add("(C) Change store");
        m.add("(A) Add to your list");
        m.add("(F) Full " + store.getName() + " audit");
        m.add("(P) Print shopping list");
        m.add("");
        m.add("(S) Save and Exit");
        m.add("(X) Exit without saving");

        return m;
    }

    public void start() {
        validateList();
        while (true) {
            System.out.println("");
            DrawMenu d = new DrawMenu(menu());
            System.out.print("What would you like to do?  ");
            String command = c.next();
            if (command.equals("x")) {
                break;
            }
            if (command.equals("s")) {
                shoppingList.validateList(store);
                shoppingList.save(store);
                store.save();
                break;
            }
            if (command.equals("p")) {
                store.print();
                System.out.println("enter to continue");
                c.next();
            }
            if (command.equals("c")) {
                System.out.println("Changing store.");
                changeStore();
            }
            if (command.equals("e")) {
                System.out.println("haven't added edit yet");
            }
            if (command.equals("a")) {
                Product newItem = newProduct();
                if (newItem != null) {
                    shoppingList.add(newItem.getName());
                }
            }
            if (command.equals("f")) {
                fullAudit();
            }
        }
    }

    private void changeStore() {
        System.out.print("Where shold we shop? ");
        String newStore = c.next(); // user entry will be store name and file name.  Location and extension are static. 
        if (newStore.equals("x")) {
            return;    //  User input of X ends this function and will return to menu. 
        }

        File newFile = new File("src\\" + newStore + ".txt");
        try {
            if (!newFile.exists()) {
                System.out.println("File does not exist.  Do you wish to create it?: (Y/N)");
                String command = c.next();
                if (command.equals("y")) {
                    this.store = new ProductManager(newStore);
                    System.out.println("Now we're shopping at " + store.getName());
                } else {
                    System.out.println("returning to menu");
                    return;
                }
            } else {
                this.store = new ProductManager(newStore);
            }
            System.out.println("");

        } catch (Exception e) {
            System.out.println("unable to find or create store for " + newStore);
        }

    }

    private boolean updateProduct(String n) {
        while (true) {
            List<String> m = new ArrayList<>();
            m.add(store.getName());
            m.add(store.getProduct(n).getName() + " $" + String.format("%.2f", store.getProduct(n).priceTag()) + ", " + store.getProduct(n).getDepartment().name());
            m.add("");
            m.add("(P) Change Price");
            m.add("(D) Change Department");
            m.add("(X) Delete " + n);
            m.add("(ENTER) Save item");
            DrawMenu menu = new DrawMenu(m);

            String command = c.next();
            if (command.equals("x")) {
                return false;
            }
            if (command.equals("p")) {
                store.getProduct(n).setPrice(c.price());

            }
            if (command.equals("d")) {
                store.getProduct(n).setDepartment(c.department());
            }
            if (command.equals("")) {
                break;
            }
        }
        return true;
    }

    private Product newProduct() {
        System.out.print("Enter the name of this product: ");
        String n = c.name(store);
        if (n.equals("x") || n.equals("")) {
            System.out.println("abort");
            return null;
        }
        if (exists(n)) {
            return store.getProduct(n);
        }
        double p = c.price();
        Department d = c.department();
        Product product = new Product(n, p, d);
        System.out.println(product.toString());
        store.addProduct(product);
        store.save();
        return product;
    }

    private void fullAudit() {
        Iterator<String> iterator = store.getProducts().keySet().iterator();
        List<String> remove = new ArrayList<>();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.print("\nNext up, " + store.getProduct(next).toString() + ". Enter to continue. X to Stop: ");
            if (c.next().equals("x")) {
                break;
            }
            Boolean update = updateProduct(next);
            if (update) {
                store.save();
            } else {
                remove.add(next);
            }
        }
        for (String each : remove) {
            store.removeProduct(each);
        }
    }

    private void validateList() {
        List<String> failed = shoppingList.validateList(store);
        // list validated in ShoppingList class.  Function returns only non-matches.
        if (failed.isEmpty()) {
            System.out.println("All items checked out!");
            return;
        } else if (failed.size() == 1) {
            System.out.println("Looks like we just need to correct one item");
        } else {
            System.out.println("Looks like we need to correct " + failed.size() + " items");
        }
        // each failed match will be given to the user and a request made for correction or creation of item
        for (String each : failed) {
            while (!exists(each)) {
                System.out.println("");
                List<String> m = new ArrayList<>();
                m.add(each + " not found at " + store.getName() + ".");
                m.add("");
                m.add("(C) Change name");
                m.add("(A) Add new item");
                m.add("(X) Skip item (delete)");
                DrawMenu menu = new DrawMenu(m);

                String command = c.next();
                if (command.equals("a")) {
                    Product p = newProduct();
                    if (p != null) {
                        each = p.getName();
                    }
                }
                if (command.equals("c")) {
                    String n = c.name(store);
                    if (!n.equals("x") || !n.equals("")) {
                        each = n;
                    }
                }
                if (command.equals("x")) {
                    break;
                }
            }
            if (exists(each)) {
                shoppingList.add(each);
            }
        }
    }
    
    private String getCost(ProductManager store) {
        // obtains total cost of current shopping list from specified store
        return shoppingList.getCost(store);
    }
    
    private boolean exists(String n) {
        
        return store.exists(n);
    }
    
    /*
    private String getCommand() {
        // takes command from user
        String command = reader.nextLine().toLowerCase();
        return command;
    }
    
    
    OBSOLETE CODE CHUNKS.  METHODS/FUNCTIONS MOVED TO GetCommand CLASS
    
    private String setName() {
// dedicated to setting  name for products. Informs user of product existence.  
        System.out.println("Enter a name for this product: ");
        String n = getCommand();
        while (true) {
            if (exists(n)) {
                System.out.println("Found listing for " + n);
                System.out.println(store.getProduct(n).toString());
            } else {
                System.out.println("No listing found for " + n);
            }
            System.out.print("Enter new name, or hit return to keep: ");
            String command = getCommand();
            if (command.equals("")) {
                return n;
            }
        }
    }
    
    
    private double setPrice() {
// dedicated to error fre price setting for products.
        double d = -1;
        while (d == -1) {
            try {
                System.out.print("Enter the price for this item: ");
                d = Double.parseDouble(getCommand());
            } catch (Exception e) {
                System.out.println("invalid entry. \"0\" to give up");
            }
        }
        return d;
    }
    
    private Department setDepartment() {
// dedicated to error free department designations for products
        while (true) {
            try {
                System.out.print("Enter the Department for this item: ");
                String d = getCommand().toUpperCase();
                Department dept = Department.valueOf(d);
                return dept;

            } catch (Exception e) {
                System.out.println("not a valid Department.");
            }
        }
    }
    */

}
