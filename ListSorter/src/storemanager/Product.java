package storemanager;

public class Product {

    private final String name;
    private double price;
    private Department department;

    public Product(String name, double price, Department department) {
        this.name = name.toLowerCase();
        this.price = price;
        this.department = department;

    }

    public Product(String[] item) {
        this.name = item[1].toLowerCase();
        this.price = Double.parseDouble(item[2]);
        this.department = Department.valueOf(item[0].toUpperCase());
    }

    public String getName() {
        return this.name;
    }

    public String priceTag() {
        return String.format("%.2f", this.price);
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getRank() {
        return this.department.getValue();
    }

    @Override
    public String toString() {
        return (this.department + ":" + this.name + ":" + this.priceTag());
    }
}
