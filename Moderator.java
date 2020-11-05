package myGame.classes;

import java.util.ArrayList;

class Moderator implements Runnable {
    DataSource data = new DataSource();
    private ArrayList<Integer> boardNumbers = new ArrayList<Integer>();

    Moderator() {
        for (int i = 0; i < 10; i++) {
            this.boardNumbers.add((int) Math.random() * 50);
        }
    }

    // public void run() {
    // synchronized (data.lock1) {
    // System.out.println("kidhr");
    // while (boardNumbers.size() != 0 && data.winners.size() == 0 && data.modTurn)
    // {
    // System.out.println("aao");
    // for (int i = 0; i < data.numberOfPlayers; i++) {
    // data.playerTurnDone[i] = false;
    // }
    // data.NumberDisplayed = boardNumbers.get(0);
    // boardNumbers.remove(0);
    // data.modTurn = false;
    // data.lock1.notifyAll();
    // for (int i = 0; i < data.numberOfPlayers; i++) {
    // if (!data.playerTurnDone[i]) {
    // try {
    // data.lock1.wait();
    // } catch (InterruptedException e) {
    // System.out.println(e);
    // }
    // }
    // }
    // ;
    // }
    // }
    // if (data.winners.size() == 0) {
    // System.out.println("No Winner");
    // } else {
    // for (int i = 0; i < data.winners.size(); i++) {
    // System.out.println(data.winners.get(i));
    // }
    // }
    // }

    public void run() {
        while (boardNumbers.size() != 0 && data.winners.size() != 0) {
            try {
                generateNumber();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private void generateNumber() throws InterruptedException {
        synchronized (data.lock1) {
            if (data.modTurn) {
                for(int i=0;i<data.numberOfPlayers;i++){
                    data.playerTurnDone[i]=false;
                }
                data.NumberDisplayed = boardNumbers.get(0);
                boardNumbers.remove(0);
                data.modTurn = false;
                data.lock1.wait();
            }
            Thread.sleep(500);
            data.lock1.notifyAll();
        }
    }
}
