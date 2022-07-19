import java.util.*;
public class TwentyFortyEight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] board = new int[4][4];
        int rounds = 0;
        int score = 0;
        assignValue(board, rounds);
        printBoard(board);
        System.out.print("\nEnter Cardinal Direction Initials(N, E, S, W) or type Exit: ");
        String input = (scan.nextLine()).toLowerCase();
        while(!input.equals("exit")) {
            switch (input) {
                case "n" -> {
                    up(board);
                    rounds++;
                    assignValue(board, rounds);
                    printBoard(board);
                }
                case "s" -> {
                    down(board);
                    rounds++;
                    assignValue(board, rounds);
                    printBoard(board);
                }
                case "e" -> {
                    right(board);
                    rounds++;
                    assignValue(board, rounds);
                    printBoard(board);
                }
                case "w" -> {
                    left(board);
                    rounds++;
                    assignValue(board, rounds);
                    printBoard(board);
                }
            }
            System.out.print("\nEnter Cardinal Direction Initials(N, E, S, W) or type Exit: ");
            input = (scan.nextLine()).toLowerCase();
        }

        for (int[] ints : board) {
            for (int c = 0; c < board[0].length; c++) {
                score += ints[c];
            }
        }
        System.out.println("SCORE = " + score);

    }

    public static void printBoard(int[][] arr) {
        for(int[] ints : arr) {
            for(int i = 0; i < arr[0].length; i++) {
                System.out.print(ints[i] + "    ");
            }
            System.out.println("\n");
        }
    }

    public static void assignValue(int[][] arr, int rounds) {
        int counter = 0;
        int num1 = (int)(Math.random() * 4);
        int num2 = (int)(Math.random() * 4);
        while (arr[num1][num2] != 0) {
            num1 = (int)(Math.random() * 4);
            num2 = (int)(Math.random() * 4);
            counter++;
            if (counter > 32) {
                System.out.println("YOU LOSE");
                break;
            }
        }
        if (arr[num1][num2] == 0) {
            if (rounds < 5) {
                arr[num1][num2] = 2;
            } else if (rounds < 10) {
                if(Math.random() >= 0.5)
                    arr[num1][num2] = 4;
                else
                    arr[num1][num2] = 2;
            } else {
                if(Math.random() >= 0.666)
                    arr[num1][num2] = 8;
                else if(Math.random() >= 0.333)
                    arr[num1][num2] = 4;
                else
                    arr[num1][num2] = 2;
            }
        }
    }
    public static void up(int[][] arr) {
        for (int r = 0; r < arr.length; r++) { //loop through each row
            for (int c = 0; c < arr[0].length; c++) { //loop through each column
                if (arr[r][c] != 0) { //if selected tile is not equal to 0
                    for (int i = 0; i < r; i++) { //loop through each tile *above* the selected tile starting from the *highest*
                        if (arr[r][c] == arr[i][c]) { //if selected tile is equal to tile *above*
                            arr[i][c] *= 2; //multiply tile above by 2
                            arr[r][c] = 0; //set selected tile to 0
                        } else if (arr[r][c] != arr[i][c] && arr[i][c] != 0) { //if above tile isn't equal to selected tile and isn't equal to 0
                            if (i+1 == r) {
                                System.out.print("");
                            }//if directly below above tile is equal to selected tile

                            else if (arr[i+1][c] == arr[r][c]) {
                                arr[i+1][c] *= 2;
                                arr[r][c] = 0;
                            } else if (arr[i+1][c] != arr[r][c] && arr[i+1][c] != 0) {
                                if (i+2 == r) {
                                    System.out.print("");
                                }
                                else if (arr[i+2][c] == arr[r][c]) {
                                    arr[i+2][c] *= 2;
                                    arr[r][c] = 0;
                                } else {
                                    if (r == 3) {
                                        continue;
                                    }
                                    arr[i+2][c] = arr[r][c]; //move selected tile to directly below the tile above
                                    arr[r][c] = 0;
                                }
                            } else {
                                arr[i+1][c] = arr[r][c]; //move selected tile to directly below the tile above
                                arr[r][c] = 0;
                            }
                        } else {
                            arr[i][c] = arr[r][c]; //move selected tile to the tile above
                            arr[r][c] = 0;
                        }
                    }
                }
            }
        }
    }

    public static void down(int[][] arr) {
        for (int r = 3; r >= 0; r--) { //loop through each row
            for (int c = 3; c >= 0; c--) { //loop through each column
                if (arr[r][c] != 0) { //if selected tile is not equal to 0
                    for (int i = 3; i > r; i--) { //loop through each tile *below* the selected tile starting from the *lowest*
                        if (arr[r][c] == arr[i][c]) { //if selected tile is equal to tile *below*
                            arr[i][c] *= 2; //multiply tile above by 2
                            arr[r][c] = 0; //set selected tile to 0
                        } else if (arr[r][c] != arr[i][c] && arr[i][c] != 0) { //if above tile isn't equal to selected tile and isn't equal to 0
                            if (i-1 == r) { //if directly below above tile is equal to selected tile
                                System.out.print("");
                            }

                            else if (arr[i-1][c] == arr[r][c]) {
                                arr[i-1][c] *= 2;
                                arr[r][c] = 0;
                            } else if (arr[i-1][c] != arr[r][c] && arr[i-1][c] != 0) {
                                if (i-2 == r) {
                                    System.out.print("");
                                }

                                else if (arr[i-2][c] == arr[r][c]) {
                                    arr[i-2][c] *= 2;
                                    arr[r][c] = 0;
                                } else {
                                    if (r == 0) {
                                        continue;
                                    }
                                    arr[i-2][c] = arr[r][c]; //move selected tile to directly *above* the tile above
                                    arr[r][c] = 0;
                                }
                            } else {
                                arr[i-1][c] = arr[r][c]; //move selected tile to directly *above* the tile above
                                arr[r][c] = 0;
                            }
                        } else {
                            arr[i][c] = arr[r][c]; //move selected tile to the tile above
                            arr[r][c] = 0;
                        }
                    }
                }
            }
        }
    }

    public static void right(int[][] arr) {
        for(int r = 3; r >= 0; r--) { //loop through each row
            for(int c = 0; c < arr[0].length; c++) { //loop through each column
                if(arr[r][c] != 0) { //selecting the non-zero tile
                    for(int i = 3; i > c; i--) { //loop through each tile *right* of the selected tile starting from the furthest *right*
                        if (arr[r][c] == arr[r][i]) { //if selected tile is equal to tile to the *right*
                            arr[r][i] *= 2;
                            arr[r][c] = 0;
                        } else if (arr[r][c] != arr[r][i] && arr[r][i] != 0) {
                            if(i-1 == c) {
                                System.out.print("");
                            } else if (arr[r][i-1] == arr[r][c]) {
                                arr[r][i-1] *= 2;
                                arr[r][c] = 0;
                            } else if (arr[r][i-1] != arr[r][c] && arr[r][i-1] != 0) {
                                if (i-2 == c) {
                                    System.out.print("");
                                } else if (arr[r][i-2] == arr[r][c]) {
                                    arr[r][i-2] *= 2;
                                    arr[r][c] = 0;
                                } else {
                                    if (r == 0) {
                                        continue;
                                    }
                                    arr[r][i-2] = arr[r][c]; //move selected tile to directly *above* the tile above
                                    arr[r][c] = 0;
                                }
                            } else {
                                arr[r][i-1] = arr[r][c]; //move selected tile to directly *above* the tile above
                                arr[r][c] = 0;
                            }
                        } else {
                            arr[r][i] = arr[r][c];
                            arr[r][c] = 0;
                        }
                    }
                }
            }
        }
    }

    public static void left(int[][] arr) {
        for (int r = 0; r < arr.length; r++) { //loop through each row
            for (int c = 0; c < arr[0].length; c++) { //loop through each column
                if (arr[r][c] != 0) { //if selected tile is not equal to 0
                    for (int i = 0; i < c; i++) { //loop through each tile *above* the selected tile starting from the *highest*
                        if (arr[r][c] == arr[r][i]) { //if selected tile is equal to tile *above*
                            arr[r][i] *= 2; //multiply tile above by 2
                            arr[r][c] = 0; //set selected tile to 0
                        } else if (arr[r][c] != arr[r][i] && arr[r][i] != 0) { //if above tile isn't equal to selected tile and isn't equal to 0
                            if (i + 1 == c) {
                                System.out.print("");
                            }//if directly below above tile is equal to selected tile

                            else if (arr[r][i+1] == arr[r][c]) {
                                arr[r][i+1] *= 2;
                                arr[r][c] = 0;
                            } else if (arr[r][i+1] != arr[r][c] && arr[r][i+1] != 0) {
                                if (i + 2 == c) {
                                    System.out.print("");
                                } else if (arr[r][i+2] == arr[r][c]) {
                                    arr[r][i+2] *= 2;
                                    arr[r][c] = 0;
                                } else {
                                    if (r == 3) {
                                        continue;
                                    }
                                    arr[r][i+2] = arr[r][c]; //move selected tile to directly below the tile above
                                    arr[r][c] = 0;
                                }
                            } else {
                                arr[r][i+1] = arr[r][c]; //move selected tile to directly below the tile above
                                arr[r][c] = 0;
                            }
                        } else {
                            arr[r][i] = arr[r][c]; //move selected tile to the tile above
                            arr[r][c] = 0;
                        }
                    }
                }
            }
        }
    }
}
