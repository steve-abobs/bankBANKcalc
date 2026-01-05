public class Customer {
    private static int nextId = 1;
    
    private int id;
    private String fName;
    
    public Customer(String fName) {
        this.id = nextId++;
        this.fName = fName;
    }
    
    public int getId() {
        return id;
    }
    
    public String getFullName() {
        return fName;
    }
    
    @Override
    public String toString() {
        return "Customer{id=" + id + ", fullName='" + fName + "'}";
    }
}

