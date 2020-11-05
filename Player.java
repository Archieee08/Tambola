package myGame.classes;

import java.util.ArrayList;

import sun.awt.AWTAccessor.SystemTrayAccessor;

class Player implements Runnable, Cloneable {
    String name;
    int ID;
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    int count;

    Player() {
        this.count = 0;
        for (int i = 0; i < 10; i++) {
            this.numbers.add((int) Math.random() * 50);
        }
    }

    DataSource data = new DataSource();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // public void run() {
    // synchronized (data.lock1) {
    // while (!data.gameFinished && !data.modTurn) {
    // data.modTurn = true;
    // if (this.numbers.contains(data.NumberDisplayed)) {
    // this.count++;
    // this.numbers.remove(new Integer(data.NumberDisplayed));
    // }
    // if (this.count == data.maxCount) {
    // data.winners.add(this.name);
    // }
    // data.playerTurnDone[this.ID - 1] = true;

    // for (int i = 0; i < data.numberOfPlayers; i++) {
    // if (!data.playerTurnDone[i]) {
    // data.modTurn = false;
    // break;
    // }
    // }
    // for (int i = 0; i < data.numberOfPlayers; i++) {
    // System.out.print(data.playerTurnDone[i]+" ");
    // }
    // System.out.println();
    // data.playerTurnDone[this.ID - 1] = true;
    // System.out.println(data.modTurn);
    // data.lock1.notifyAll();
    // if (!data.modTurn) {
    // try {
    // System.out.println("waiting b to compelete");
    // data.lock1.wait();
    // } catch (InterruptedException e) {
    // System.out.println(e);
    // }
    // }
    // // data.lock1.notifyAll();
    // }

    // }
    // }

    public void run() {
        while (!data.modTurn && data.winners.size() != 0) {
            data.modTurn = true;
            try {
                generateNumber();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private void generateNumber() throws InterruptedException {
        synchronized (data.lock1) {
            data.playerTurnDone[this.ID - 1] = true;
            for (int i = 0; i < data.numberOfPlayers; i++) {
                if (!data.playerTurnDone[i]) {
                    data.modTurn = false;
                    break;
                }
            }
            for (int i = 0; i < data.numberOfPlayers; i++) {
                System.out.print(data.playerTurnDone[i]+" ");
            }
            System.out.println();
            data.lock1.wait();

            Thread.sleep(500);
            data.lock1.notifyAll();
        }
    }
}