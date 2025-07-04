package org.interview.practice.design_patterns.singleton;

public class Main {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        Singleton obj3 = Singleton.getInstance();

        System.out.println("Obj1 : " + obj1);
        System.out.println("Obj2 : " + obj2);
        System.out.println("Obj3 : " + obj3);
    }
}
