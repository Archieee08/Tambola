package myGame.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;                         

public class Game {
    Moderator md = new Moderator();

    public static void main(String args[]) {
        Moderator moderator = new Moderator();
        DataSource data = new DataSource();
        String Name;
        ArrayList<Player> players = new ArrayList<Player>();
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader bufferInput = new BufferedReader(input);
        Player p1 = new Player();
        for (int i = 0; i < data.numberOfPlayers; i++) {
            try {

                if (i == 0) {
                    Name = bufferInput.readLine();
                    p1.setName(Name);
                    p1.setID(i + 1);
                    players.add(p1);
                } else {
                    Name = bufferInput.readLine();
                    Player player = (Player) p1.clone();
                    player.setName(Name);
                    player.setID(i + 1);
                    players.add(player);
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (CloneNotSupportedException c) {
                System.out.println(c);
            }
        }
        Thread mThread = new Thread(moderator);
        Thread[] playerThread = new Thread[data.numberOfPlayers];
        for (int i = 0; i < data.numberOfPlayers; i++) {
            playerThread[i] = new Thread(players.get(i));
        }
        mThread.start();
        for (int i = 0; i < data.numberOfPlayers; i++) {
            playerThread[i].start();
        }
    }
}