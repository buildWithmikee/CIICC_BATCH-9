// Shape interface
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// AbstractShape class
abstract class AbstractShape implements Shape {
    protected String color;
    protected double length;
    protected double width;

    public AbstractShape(String color, double length, double width) {
        this.color = color;
        this.length = length;
        this.width = width;
    }
}

// Circle class
class Circle extends AbstractShape {
    private double radius;

    public Circle(String color, double radius) {
        super(color, radius, radius); 
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Rectangle class
class Rectangle extends AbstractShape {

    public Rectangle(String color, double length, double width) {
        super(color, length, width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle("Red", 5);
        Rectangle rectangle = new Rectangle("Blue", 4, 7);

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());

        System.out.println("\nRectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());
    }
}
