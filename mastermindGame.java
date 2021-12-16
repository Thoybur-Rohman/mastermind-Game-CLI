import java.util.Scanner;
import java.util.Random;


class mastermindGame {

    static String[] coloursAvailable = {"R", "G", "B", "Y", "P", "O"};
    static String[] gameSequence = {"1", "2", "3", "4"};
    static int playerLives = 12;
    static int playerScore = 0;
    static boolean bchance, bfifty;
    static boolean play = false;
    static Scanner input1 = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);
    static Scanner input3 = new Scanner(System.in);
    static Scanner dupliCon = new Scanner(System.in);
    static Scanner startAgain = new Scanner(System.in);
    public static Object generateSequence() {
        Random rand = new Random();
        boolean dupliCates = false, bzero, bone, btwo, bthree, bfour, bfive;
        bzero = false;
        bone = false;
        btwo = false;
        bthree = false;
        bfour = false;
        bfive = false;
//Random number array that will be used as an index for coloursAvailable[]
        Integer[] ranNArr = {1, 2, 3, 4};
        System.out.println("Chance of duplicate colours in the sequence?"
                + "\nY for duplicates or anything else for No:");
        String userDupli = dupliCon.next();
        userDupli = userDupli.toUpperCase();
//Allows the presence of duplicate colours in the sequence, should there be any
        if (userDupli.charAt(0) == 'Y') {
            dupliCates = true;
        }
//Generates the random numbers for ranNArr[]
        for(int p = 0; p < 4; p++) {
            int ranNum = rand.nextInt(5);
            ranNArr[p] = ranNum;
        }
//Goes through each number in ranNArr and uses booleans to see which numbers are inside
        for (int s = 0; s < ranNArr.length; s++) {
            if (ranNArr[s] == 0) {
                bzero = true;
            }
            else if (ranNArr[s] == 1) {
                bone = true;
            }
            else if (ranNArr[s] == 2) {
                btwo = true;
            }
            else if (ranNArr[s] == 3) {
                bthree = true;
            }
            else if (ranNArr[s] == 4) {
                bfour = true;
            }
            else if (ranNArr[s] == 5) {
                bfive = true;
            }
        }
//Uses the booleans created for the numbers to look for any duplicates in the array. If found, they are replaced.
        if (dupliCates == false) {
            for (int r = 0; r < 3; r++) {
                for (int q = r + 1; q < 4; q++) {
                    if (ranNArr[r] == ranNArr[q]) {
                        if (bzero == false) {
                            ranNArr[q] = 0;
                            bzero = true;
                        }
                        else if (bone == false) {
                            ranNArr[q] = 1;
                            bone = true;
                        }
                        else if (btwo == false) {
                            ranNArr[q] = 2;
                            btwo = true;
                        }
                        else if (bthree == false) {
                            ranNArr[q] = 3;
                            bthree = true;
                        }
                        else if (bfour == false) {
                            ranNArr[q] = 4;
                            bfour = true;
                        }
                        else if (bfive == false) {
                            ranNArr[q] = 5;
                            bfive = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {

       /* Uses the randomly generated numbers as an index for the coloursAvailable array
       and add them to the gameSequence array list */
            gameSequence[i] = coloursAvailable[ranNArr[i]];
        }


        return(gameSequence);
    }
    public static int gamePlay() {
        boolean gameSession = true;
        bchance = false;
        bfifty = false;
        while (gameSession == true) {
// Converts gameSequence (currently an array) into a string
            String gameSequenceString = String.join("", gameSequence);
            while(playerLives > 0) {
//Input the player's guess
                System.out.print("\nEnter your guess"
                        + "\nor C for Chance "
                        + "\nor F for 50/50: ");
                String userInputString = input1.next();
                userInputString = userInputString.toUpperCase();
/*Checks whether Chance or 50/50 have been used.
If it has, access is denied and they are prompted to enter a sequence. */
                if (userInputString.charAt(0) == 'C') {
                    if (bchance == false) {
                        chance();
                        System.out.println("Enter your guess: ");
                        userInputString = input1.next();
                        userInputString = userInputString.toUpperCase();
                    }
                    else {
                        System.out.println("You've already used this!");
                        System.out.println("Enter your guess: ");
                        userInputString = input1.next();
                        userInputString = userInputString.toUpperCase();
                    }
                }
                if(userInputString.charAt(0) == 'F') {
                    if (bfifty == false) {
                        fiftyFifty();
                        System.out.println("Enter your guess: ");
                        userInputString = input1.next();
                        userInputString = userInputString.toUpperCase();
                    }
                    else {
                        System.out.println("You've already used this!");
                        System.out.println("Enter your guess: ");
                        userInputString = input1.next();
                        userInputString = userInputString.toUpperCase();
                    }
                }
// Decrease player lives by 1
                playerLives --;
// Convert user input and game sequence into an array to be traversed
                char[] userInputCA = userInputString.toCharArray();
                char[] gameSequenceCA = gameSequenceString.toCharArray();
// Checks whether the player got the sequence exactly right
                if (userInputString.equals(gameSequenceString)) {
                    System.out.println("You've won!");
                    playerScore ++;
                    System.out.println("Your score is " + playerScore);
                    gameSession = false;
                    break;
                }
                else if (playerLives == 0 && !(userInputString.equals(gameSequenceString))) {
                    System.out.println("You've lost! The sequence was " + gameSequenceString + "." + " Better luck next time :)");
                    gameSession = false;
                    break;
                }
// Gets the length of user input. This will be used as an index.
                int n = 0;
                if (userInputCA.length < 4) {
                    n = userInputCA.length;
                }
                else {
                    n = 4;
                }
//Checks if there are any colours in user input that also exist in the same place in gameSequence
                for (int w = 0; w < n; w++) {
                    if (userInputCA[w] == gameSequenceCA[w]) {
                        System.out.print("+");
                    }
                }
/*The following if statements go through each user input and check them against
colours in gameSequence that aren't in the same position */
                if (userInputCA[0] == gameSequenceCA[1] && 0 < n) {
                    System.out.print("-");
                }
                if (userInputCA[0] == gameSequenceCA[2] && 0 < n) {
                    System.out.print("-");
                }
                if (userInputCA[0] == gameSequenceCA[3] && 0 < n) {
                    System.out.print("-");
                }
                if (n > 1) {
                    if (userInputCA[1] == gameSequenceCA[0]) {
                        System.out.print("-");
                    }
                    if (userInputCA[1] == gameSequenceCA[2]) {
                        System.out.print("-");
                    }
                    if (userInputCA[1] == gameSequenceCA[3]) {
                        System.out.print("-");
                    }
                }
                if (n > 2) {
                    if (userInputCA[2] == gameSequenceCA[0]) {
                        System.out.print("-");
                    }
                    if (userInputCA[2] == gameSequenceCA[1]) {
                        System.out.print("-");
                    }
                    if (userInputCA[2] == gameSequenceCA[3]) {
                        System.out.print("-");
                    }
                }
                if (n > 3) {
                    if (userInputCA[3] == gameSequenceCA[0]) {
                        System.out.print("-");
                    }
                    if (userInputCA[3] == gameSequenceCA[1]) {
                        System.out.print("-");
                    }
                    if (userInputCA[3] == gameSequenceCA[2]) {
                        System.out.print("-");
                    }
                }
                System.out.println("\n\tGuesses left: " + playerLives);
            }
        }

        return playerScore;
    }
    public static boolean fiftyFifty() {
        String fifty1 = null, fifty2 = null;
        boolean oneGot, twoGot, bRed, bGre, bBlu, bYel, bPur, bOra;
        oneGot = false;
        twoGot = false;
        bRed = false;
        bGre = false;
        bBlu = false;
        bYel = false;
        bPur = false;
        bOra = false;
        Random rand = new Random();
        int ranNum = rand.nextInt(3);
//Establishes what colours are in the sequence with booleans
        for (int s = 0; s < gameSequence.length; s++) {
            if (gameSequence[s] == "R") {
                bRed = true;
            }
            else if (gameSequence[s] == "G") {
                bGre = true;
            }
            else if (gameSequence[s] == "B") {
                bBlu = true;
            }
            else if (gameSequence[s] == "Y") {
                bYel = true;
            }
            else if (gameSequence[s] == "P") {
                bPur = true;
            }
            else if (gameSequence[s] == "O") {
                bOra = true;
            }
        }
//Assigns a colour from gameSequence to a variable
        while (oneGot == false) {
            fifty1 = gameSequence[ranNum];
            oneGot = true;
        }
/* Assigns a colour not in gameSequence to a second variable
using the previously assigned booleans */
        while (twoGot == false) {
            if (bRed == false) {
                fifty2 = "R";
            }
            else if (bGre == false) {
                fifty2 = "G";
            }
            else if (bBlu == false) {
                fifty2 = "B";
            }
            else if (bYel == false) {
                fifty2 = "Y";
            }
            else if (bPur == false) {
                fifty2 = "P";
            }
            else if (bOra == false) {
                fifty2 = "O";
            }
            twoGot = true;
        }
        System.out.println(fifty1 + " or " + fifty2 + " is in the sequence!");
//While bfifty is true, fiftyFifty cannot be accessed again.
//This is reset to false after every new game.
        bfifty = true;
        return bfifty;
    }
    public static boolean chance() {
        Random rand = new Random();
        boolean second, notinS;
        second = false;
        notinS = false;
// Converts gameSequence (currently an array) into a string
        String gameSequenceString = String.join("", gameSequence);
        char[] gameSequenceCA = gameSequenceString.toCharArray();
//Randomly assigns two different numbers to two chance variables.
//These are what the player will aim to guess.
        int ranC1, ranC2;
        ranC1 = rand.nextInt(4);
        ranC2 = rand.nextInt(4);
        if (ranC1 == ranC2) {
            ranC2 = rand.nextInt(3);
        }
        System.out.println("Enter a number between 0 and 3");
        int playerChance = input3.nextInt();
        while (second == false) {
//If the player guesses the first number correctly, the game outputs a random colour from the sequence.
            if (playerChance == ranC1) {
                System.out.println(gameSequence[ranC1] + " is in the sequence!");
                second = true;
            }
//If the player guesses the second correctly, they are given a chance to verify whether a colour is part
// of the gameSequence
            if (playerChance == ranC2) {
                second = true;
                System.out.println("Enter a colour: ");
                String playerChanceGuess = input2.next();
                playerChanceGuess = playerChanceGuess.toUpperCase();
                char firstChar = playerChanceGuess.charAt(0);
                for (int w = 0; w < 4; w++) {
                    if (firstChar != gameSequenceCA[w]) {
                        notinS = true;
                        continue;
                    }
                    else {
                        System.out.println(firstChar + " is in the sequence.");
                        notinS = false;
                        break;
                    }
                }
                if (notinS == true) {
                    System.out.println(playerChanceGuess.charAt(0) + " is not in the sequence.");
                }
            }
//Commiseration message
            if (playerChance != ranC1 && playerChance != ranC2) {
                System.out.println("Unfortunately you've guessed incorrectly :(");
                second = true;
            }
        }
//Ensures that chance cannot be accessed again the same game
        bchance = true;
        return bchance;
    }
    public static boolean playAgain() {

        System.out.println("Enter Y to play again, "
                + "\notherwise enter anything else: ");
        String playAgain = startAgain.next();
        playAgain = playAgain.toUpperCase();
//Verifies whether they want to play again
        if (playAgain.charAt(0) == 'Y') {
            play = true;
        }
        else {
            play = false;
        }
//Triggers the methods again
        while (play == true) {
            generateSequence();
            gamePlay();
            playAgain();
        }
        return play;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind!"
                + "\n This is a game between you and the computer."
                + "\n You have 12 guesses."
                + "\n The computer generates a random sequence of four letters."
                + "\n It is your job to guess this from the following available."
                + "\n R, G, B, Y, P, O "
                + "\n You enter your guess as a sequence of four with no spaces."
                + "\n for example: RGBY"
                + "\n The computer might return a + or a - depending on the accuracy of your guess."
                + "\n A + means you have a colour present in the answer and in the right place."
                + "\n A - means you have a colour present but it isn't in the right place."
                + "\n + are displayed first, followed by - if applicable."
                + "\n Good luck!");
        generateSequence();
        gamePlay();
        playAgain();
    }
}
