package org.example.digger;


public class Trench {
    int length;

    public Trench(int length) {
        this.length = length;
    }

    public synchronized int dig() {
        if (length <= 0) {
            System.out.println("trench complitly dug");
            return 0;
        }
        length--;
        System.out.println("trench dug 1 meter more");
        return 1;
    }
}

