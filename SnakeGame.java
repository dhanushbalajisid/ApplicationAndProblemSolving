import java.util.*;

public class SnakeGame {

    private static Random random=new Random(); // Random object to generate food position
    private static int foodrow;  // Row position of food
    private static int foodcol;  // Column position of food
    private static int count=0;  // Counter for score (number of foods eaten)
    private static char user;    // User input for direction
    private static char direction='R'; // Initial direction set to Right
    private static List<int []> snakebody=new ArrayList<>(); // To store body of snake as list of (row, col) positions

    public static void main(String[] args) {
        char snakehead='>'; // Character to represent snake's head
        int currow=0; // Current row of snake's head
        int curcol=0; // Current column of snake's head
        int row=6; // Rows of grid
        int col=6; // Columns of grid
        snakebody.add(new int[]{currow,curcol}); // Add initial position of snake's head to the body list
        foodplace(); // Place the first food on the board
        printboard(row,col,currow,curcol,snakehead,user); // Print initial state of the board
        userchoice(row,col,currow,curcol,snakehead); // Start taking user input to move snake
    }

    private static void userchoice(int row,int col,int currow,int curcol,char snakehead){
        Scanner obj=new Scanner(System.in); // Scanner object to take input from user
        while(true){
            System.out.println("(Up-W / Down-S / Right-D / Left-A)"); // Instructions for input
            user=obj.next().toUpperCase().charAt(0); // Read user input and convert to uppercase
            switch (user) {
                case 'W': // Move Up
                    if (direction != 'S') { // Prevent moving directly opposite
                        currow--;
                        snakehead = '^'; // Change snake head direction
                        direction = 'W';
                    }
                    break;
                case 'S': // Move Down
                    if (direction != 'W') { // Prevent moving directly opposite
                        currow++;
                        snakehead = 'v'; // Change snake head direction
                        direction = 'S';
                    }
                    break;
                case 'D': // Move Right
                    if (direction != 'A') { // Prevent moving directly opposite
                        curcol++;
                        snakehead = '>';
                        direction = 'D';
                    }
                    break;
                case 'A': // Move Left
                    if (direction != 'D') { // Prevent moving directly opposite
                        curcol--;
                        snakehead = '<';
                        direction = 'A';
                    }
                    break;
                default: // Handle invalid input
                    System.err.println("Invalid Input");
                    continue;
            }
            // Check if new position is within bounds
            if(currow>=0&&currow<=row-1&&curcol>=0&&curcol<=col-1) {
                if((currow==foodrow&&curcol==foodcol)){ // If snake eats food
                    count++; // Increase score
                    foodplace(); // Place new food
                }
                else{
                    snakebody.remove(0); // Remove tail segment to maintain snake length
                }
                snakebody.add(new int[]{currow,curcol}); // Add new head position
                printboard(row, col, currow, curcol, snakehead,user); // Print updated board
            }
            else{
                System.err.println("Game Over!!!"); // Snake hits the wall
                System.out.println("Your Score is: "+count);
                return; // Exit the game
            }
        }
    }

    private static void printboard(int rows,int cols,int currow,int curcol,char snakehead,char user){
        char board[][]=new char[rows][cols]; // Create board
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++) {
                board[i][j] = '■'; // Fill board with default character
            }
        }
        board[foodrow][foodcol]='x'; // Place food on board

        for(int i=0;i<snakebody.size()-1;i++){
            int segment[]=snakebody.get(i);
            board[segment[0]][segment[1]]='.'; // Mark snake body segments
        }

        if(board[currow][curcol]!='.') {
            board[currow][curcol] = snakehead; // Place snake head
        }
        else {
            System.err.println("Game Over!!!"); // Snake hits itself
            System.out.println("Your Score is: "+count);
            System.exit(0); // Exit the game
        }
        if(count==rows*cols-1){ // Check if all cells except one are filled (win condition)
            System.out.println("YOU WON :)");
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++) {
                    System.out.print(board[i][j]+"  ");
                }
                System.out.println();
            }
            System.exit(0);
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++) {
                System.out.print(board[i][j]+"  "); // Print board
            }
            System.out.println();
        }
    }

    private static void foodplace(){
        do {
            foodrow = random.nextInt(6); // Random row between 0 and 5
            foodcol = random.nextInt(6); // Random column between 0 and 5
        }while (isfoodplace()); // Ensure food is not placed on snake's body
    }

    private static boolean isfoodplace(){
        for(int []segment:snakebody){
            if(segment[0]==foodrow&&segment[1]==foodcol){ // Check if food is placed on snake body
                return true;
            }
        }
        return false; // Valid food placement
    }

}
