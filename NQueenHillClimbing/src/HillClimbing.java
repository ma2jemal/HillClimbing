// Course: CS3642
// Student name: Muna Jemal
// Student ID: 000972037
// Assignment #: #2
// Due Date: 11/08/2020

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HillClimbing {     //RandomRestartHillClimbing
        public static int n;       //the number of n queens from the user
        public static int heuristic = 0;  // heuristic value of a board
        public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the number of queen: ");
            n = input.nextInt();    //take input from the user and assign it to n
            long startTime = System.currentTimeMillis();
            if(n>=4){           //check if n is equal or greater than four
                System.out.println("The solution to " + n +  " queen on " + n + "x" +n+ " board using Random Restart Hill Climbing is: ");
                start();        // start the hillClimbing
            }
            else                        // if n is less than four print a message it can not be played
                System.out.println("The number of queen cannot be less than 4!");
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed);
        }
        public static void start(){
            // start from a random board
            NQueen[] board = generateBoard();
            int currentHeuristic = theHeuristic(board); //take the heuristic of the board

            while(currentHeuristic != 0){
                board = nextBoard(board);
                currentHeuristic = heuristic;
            }
            printBoard(board);

        }
        public static NQueen[] generateBoard(){
            NQueen[] board = new NQueen[n];
            Random rndm = new Random();
            for(int i = 0; i < n; i++){
                board[i] = new NQueen(rndm.nextInt(n),i);
            }
            return board;
        }
        public static int theHeuristic(NQueen[] board){
            int count = 0;
            for(int i = 0; i < board.length; i++){
                for(int j = i+1; j < board.length; j++){
                    if(board[i].inAttack(board[j]))
                        count++;
                }
            }
            return count;
        }
        public static NQueen[] nextBoard(NQueen[] board) {
            NQueen[] nextBoard = new NQueen[n];
            NQueen[] tmpBoard = new NQueen[n];
            int presentHeuristic = theHeuristic(board);
            int bestHeuristic = presentHeuristic;
            int tempH;

            for (int i = 0; i < n; i++) {
                nextBoard[i] = new NQueen(board[i].getRow(), board[i].getColumn());
                tmpBoard[i] = nextBoard[i];
            }

            for (int i = 0; i < n; i++) {
                if (i > 0)
                    tmpBoard[i - 1] = new NQueen(board[i - 1].getRow(), board[i - 1].getColumn());
                tmpBoard[i] = new NQueen(0, tmpBoard[i].getColumn());
                for (int j = 0; j < n; j++) {
                    tempH = theHeuristic(tmpBoard);
                    if (tempH < bestHeuristic) {
                        bestHeuristic = tempH;
                        for (int k = 0; k < n; k++) {
                            nextBoard[k] = new NQueen(tmpBoard[k].getRow(), tmpBoard[k].getColumn());
                        }
                    }
                    if (tmpBoard[i].getRow() != n - 1)
                        tmpBoard[i].move();
                }
            }
            if(bestHeuristic == presentHeuristic){
                nextBoard = generateBoard();
                heuristic = theHeuristic(nextBoard);
            }
            else
                heuristic = bestHeuristic;
            return nextBoard;
        }
        public static void printBoard(NQueen[] board){
            int[][] tempBoard = new int[n][n];
            for(int i = 0; i < n; i++){
                tempBoard[board[i].getRow()][board[i].getColumn()] = 1;
            }
            System.out.println();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    System.out.print(tempBoard[i][j] + " ");
                }
                System.out.println();
            }

        }
}