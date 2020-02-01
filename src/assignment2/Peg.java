package assignment2;
//import GameConfiguration.java;

public class Peg {
    int blackPegsIndex = 0;
    int whitePegsIndex = 1;
    int historyIndex = 0;
    String [] history = new String[GameConfiguration.guessNumber];


     public int[] checkPegs(String guessedCode, String secretCode){
        if (historyIndex == GameConfiguration.guessNumber){
            for (int i = 0; i < GameConfiguration.guessNumber; i++){
                history[i] = null;
            }
            historyIndex = 0;
        }

         String copyGuessedCode = guessedCode;
         int [] num_Black_White_Pegs = {0,0};
         for (int i = 0; i < GameConfiguration.pegNumber; i++) {
             for (int j = 0; j < GameConfiguration.pegNumber; j++) {
                 if (i == j) {
                     if (secretCode.charAt(i) == guessedCode.charAt(j)) {
                         num_Black_White_Pegs[blackPegsIndex]++;
                         StringBuilder code = new StringBuilder(guessedCode);
                         code.setCharAt(j, '-');
                         guessedCode = code.toString();

                         code = new StringBuilder(secretCode);
                         code.setCharAt(i, '*');
                         secretCode = code.toString();
                     }
                 }
             }
         }


         for (int i = 0; i < GameConfiguration.pegNumber; i++) {
             for (int j = 0; j < GameConfiguration.pegNumber; j++) {
                 if (secretCode.charAt(i) == guessedCode.charAt(j)) {
                     num_Black_White_Pegs[whitePegsIndex]++;

                     StringBuilder code = new StringBuilder(guessedCode);
                     code.setCharAt(j, '-');
                     guessedCode = code.toString();

                     code = new StringBuilder(secretCode);
                     code.setCharAt(i, '*');
                     secretCode = code.toString();
                 }
             }
         }

         history[historyIndex] = copyGuessedCode + "        " + num_Black_White_Pegs[blackPegsIndex] + "B" +"_"+num_Black_White_Pegs[whitePegsIndex] + "W";
         historyIndex++;
         return num_Black_White_Pegs;
     }


    public void getHistory(){
        for (int i = 0; i < historyIndex; i++){
            System.out.println(history[i]);
        }

    }


}

