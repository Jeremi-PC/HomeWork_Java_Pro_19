package org.example.digger;


public class Digger extends Thread {
    private final Trench trench;
    private String name;


    public Digger(Trench trench, String name) {
        this.trench = trench;
        this.name = name;
    }

    @Override
    public void run() {
        dig();
        System.out.println("General current length is :" + trench.getCurrentLength());
    }

    private void dig() {
        while (trench.currentLength < trench.givenLength) {
            trench.setCurrentLength(trench.getCurrentLength() + 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.name + " dug 1 meter more");
        }
    }

}