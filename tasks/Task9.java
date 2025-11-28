import static java.lang.Math.*;

public class Task9 {
    public static int add(int a, int b){
    return Math.addExact(a, b);
}   

public static int subtract(int a, int b){
    return Math.subtractExact(a, b);
}

public static int multiply(int a, int b){
    return  Math.multiplyExact(a, b);
}

public static float divide(int a, int b){
    return  Math.floorDiv(a, b);
}

public static void main(String[] args){
    int a = 100;
    int b = 5;

    System.out.println("Addition: " + add(a,b));
    System.out.println("Subtraction: " + subtract(a,b));
    System.out.println("Multiplication: " + multiply(a,b));
    System.out.println("Division: " + divide(a,b));


 }
}


