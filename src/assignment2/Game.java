package assignment2;
import java.util.Scanner;

public class Game {
   private Scanner reader = new Scanner(System.in);
   Peg BlackandWhitePegs = new Peg();
   int [] num_Black_White_Pegs = {0,0};
   boolean gameMode = false;

   //Constructor
public Game(boolean userMode){
    gameMode = userMode;
}


    //this is the actual game
public void runGame(){
    int blackPegsIndex = 0;
    int whitePegsIndex = 1;
    int num_Guess = GameConfiguration.guessNumber;


    initialGreeting();
    String userAnswer = reader.nextLine();

    while ((!userAnswer.equals("Y")) && (!userAnswer.equals("N"))){
        userAnswer = reader.nextLine();
    }

    if (userAnswer.equals("Y")) {
        String secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
        if (gameMode){
            System.out.println("The secretCode is " + secretCode);
        }

        while (num_Guess > 0) {

            System.out.println("\nYou have " + num_Guess + " guesses left. ");
            System.out.println("What is your next guess ?");
            System.out.println("Type in the characters for your guess and press enter.");
            System.out.print("Enter guess:");
            String guessedCode = reader.nextLine();


            while (!checkGuessValidity(guessedCode) && !(guessedCode.equals("HISTORY"))) {
                System.out.print(" -> INVALID GUESS\n");
                System.out.println("\nWhat is your next guess ?");
                System.out.println("Type in the characters for your guess and press enter.");
                System.out.print("Enter guess:");
                guessedCode = reader.nextLine();
            }

            if (guessedCode.equals("HISTORY")) {
                BlackandWhitePegs.getHistory();
            }


            if (!(guessedCode.equals("HISTORY"))) {

                num_Black_White_Pegs = BlackandWhitePegs.checkPegs(guessedCode, secretCode);

                if ((num_Black_White_Pegs[blackPegsIndex] == 0) && (num_Black_White_Pegs[whitePegsIndex] == 0)) {
                    System.out.println("\n"+guessedCode + " -> Result: No pegs");
                } else if ((num_Black_White_Pegs[blackPegsIndex] == 0)) {
                    System.out.println("\n"+guessedCode + " -> Result: " + num_Black_White_Pegs[whitePegsIndex] + " white pegs");
                } else if ((num_Black_White_Pegs[whitePegsIndex] == 0)) {
                    System.out.println("\n"+guessedCode + " -> Result: " + num_Black_White_Pegs[blackPegsIndex] + " black pegs");
                } else {
                    System.out.println("\n" + guessedCode + " -> Result: " + num_Black_White_Pegs[blackPegsIndex] + " black pegs, and " + num_Black_White_Pegs[whitePegsIndex] + " white pegs \n");
                }
            }


            if (num_Black_White_Pegs[blackPegsIndex] == GameConfiguration.pegNumber) {
                System.out.print(" - You win !!");
                System.out.println("The secret code is - " + secretCode);

                System.out.println("Are you ready for another game (Y/N):");
                userAnswer = reader.nextLine();

                while ((!userAnswer.equals("Y")) && (!userAnswer.equals("N"))){
                    userAnswer = reader.nextLine();
                }

                if (userAnswer.equals("Y")){

                    secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
                    if (gameMode){
                        System.out.println("The secretCode is " + secretCode);
                    }

                    num_Guess = GameConfiguration.guessNumber;
                }else {
                    num_Guess = 0;
                }
            } else if ((num_Guess > 0) && !(guessedCode.equals("HISTORY"))) {
                num_Guess--;
                if (num_Guess == 0) {
                    System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
                    System.out.println("The secret code is - " + secretCode);

                    System.out.println("Are you ready for another game (Y/N):");
                    userAnswer = reader.nextLine();

                    while ((!userAnswer.equals("Y")) && (!userAnswer.equals("N"))){
                        userAnswer = reader.nextLine();
                    }

                    if (userAnswer.equals("Y")){

                        secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
                        if (gameMode){
                            System.out.println("The secretCode is " + secretCode);
                        }

                        num_Guess = GameConfiguration.guessNumber;
                    }else{
                    num_Guess = 0;
                }

                }
            }
        }
    }
}

//this funtion checks whether the user guess is valid or not
public boolean checkGuessValidity(String guessedCode){
    String colors = "";
    for (int i = 0; i < GameConfiguration.colors.length; i++){
        colors += GameConfiguration.colors[i];
    }


    if (guessedCode.length() != GameConfiguration.pegNumber){
        return false;
    }

    for (int i = 0; i < GameConfiguration.pegNumber; i++){
        if (colors.indexOf(guessedCode.charAt(i)) == -1){
            return false;
        }
    }

    return true;
}

//Initial greeting displayed when game starts for the first time
private void initialGreeting(){
    System.out.println("Welcome to Mastermind. Here are the rules. \n\nThis is a text version of the classic board game Mastermind. " +
                    "\n\nThe  computer  will  think  of  a  secret  code.+ " +
                    "The  code  consists  of  4 \ncolored  pegs. " +
                    "The  pegs  MUST  be  one  of  six  colors:  blue,  green,\norange, purple, red, or yellow. " +
                    "A color may appear more than once in \nthe  code. " +
                    "You  try  to  guess  what  colored  pegs  are  in  the  code  and \nwhat  order  they  are  in. " +
                    "After  you make  a  valid  guess  the  result \n(feedback) will be displayed. " +
                    "\n\nThe  result  consists  of  a  black  peg  for  each  peg  you  have  guessed \nexactly correct (color and position) in your guess. " +
                    "For each peg in \nthe guess that is the correct color, but is out of position, you get \na  white  peg. For  each  peg,  which  is  fully  incorrect,  you  get  " +
                    "no \nfeedback. \n\nOnly the first letter of the color is displayed. B for Blue, R for \nRed, and so forth.When entering guesses you only need " +
                    "to enter the \nfirst character of each color as a capital letter. " +
                    "\n\nYou have 12 guesses to figure out the secret code or you lose the \ngame.  Are you ready to play? (Y/N):");
    }


}


