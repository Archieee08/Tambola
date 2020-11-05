package myGame.classes;

public class Action {
    private Moderator moderator;
    private GameData gameData;

    Action(GameData gameData, Moderator moderator, Player player1, Player player2) {
        this.gameData = gameData;
        this.moderator = moderator;
        for (int i = 0; i < 10; i++) {

        }
    }
    // public void actionPerformed() {
    //     for (int i = 0; i < 10; i++) {
    //         synchronized (gameData.lock2) {
    //             // moderator.setGivenNumber(i);
    //             System.out.println(i+"tyyjkl");
    //             gameData.lock2.notify();
    //         }
    //         break;
    //     }
    // }
}