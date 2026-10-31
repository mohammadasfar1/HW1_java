package src;

public class Customer {
    private final String customerId; 
    private String name;
    private String phoneNumber;
    private String email;
    private String driverLicenseNumber;

    
   public Customer(String id, String name, String phone, String email, String license) {
        this.customerId = id;
        this.name = name;
        this.phoneNumber = phone;
        this.email = email;
        this.driverLicenseNumber = license;
    }

    
    public Customer(String id, String name, String phone, String license) {
        this(id, name, phone, "N/A", license); 
    }
   
    public void displayCustomerInfo() {
       
        System.out.println("==================================================");
        System.out.println("[Customer] ID: " + customerId 
            + " | Name: " + name 
            + " | Phone: " + phoneNumber 
            + " | License: " + driverLicenseNumber
            + " | Email: " + email);
            System.out.println("==================================================");
    }


    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }
}