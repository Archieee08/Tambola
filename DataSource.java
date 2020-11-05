package myGame.classes;

import java.util.ArrayList;

public class DataSource {
    public boolean gameFinished = false;
    public static int numberOfPlayers = 4;
    public final int maxCount = 3;
    public Object lock1 = new Object();
    public static boolean modTurn = true;
    public int NumberDisplayed;
    public static ArrayList<String> winners = new ArrayList<String>();
    public static boolean playerTurnDone[] = new boolean[numberOfPlayers];
}   