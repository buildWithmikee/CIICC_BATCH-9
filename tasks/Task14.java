abstract class abstractClass{
    abstract void abstractMethod();
    void concreteMethod() {// concrete methods are still allowed in abstract classes
        System.out.println("This is a concrete method.");
    }

}

class C extends abstractClass{
    // Your code goes here
}

public class Task14 {
    public static void main(String args[]){
        B b = new B();
        b.abstractMethod();
        b.concreteMethod();
        C c = new C();
        c.abstractMethod();
        c.concreteMethod();
    }
    
}
