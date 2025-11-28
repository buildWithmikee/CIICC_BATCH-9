class Vehicle {
    protected String make;
    protected String model;
    protected int year;


    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}


public class Task13 extends Vehicle {
    private int numberOfDoors;


    public Task13(String make, String model, int year, int numberOfDoors) {
        super(make, model, year); 
        this.numberOfDoors = numberOfDoors; 
    }
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
    public static void main(String[] args) {
        Task13 myCar = new Task13("Toyota", "Camry", 2022, 4);
        myCar.displayDetails();
    }
}

