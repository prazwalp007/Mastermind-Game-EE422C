package assignment2;

public class Driver {

    public static void main(String[] args) {
    int arg = Integer.parseInt(args[0]);
    boolean gameMode = false;

    if (arg == 1){
        gameMode = true;
    }

    Game masterMind = new Game(gameMode);
    masterMind.runGame();
    }

}
