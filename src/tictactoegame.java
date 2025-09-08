import java.util.Random;
import java.util.Scanner;
public class tictactoegame {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void PrintBox(char[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }
    public static char CheckWin(char[][] matrix) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] != ' ' && matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2]) {
                return matrix[i][0];
            }
            if (matrix[0][i] != ' ' && matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i]) {
                return matrix[0][i];
            }
        }
        // Check diagonals
        if (matrix[0][0] != ' ' && matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
            return matrix[0][0];
        }
        if (matrix[0][2] != ' ' && matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) {
            return matrix[0][2];
        }
        return ' ';
    }
    public static boolean isBoardFull(char[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public static int[] ComputerAttack(char[][] matrix, char computerch )
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(matrix[i][j]==' ')
                {
                    matrix[i][j]=computerch;
                    if(CheckWin(matrix)==computerch)
                    {
                        matrix[i][j]=' ';
                        return new int[]{i,j};
                    }
                    matrix[i][j]=' ';
                }
            }
        }
        return new int[]{-1,-1};


    }
    public static int[] ComputerDefend(char[][] matrix, char userch )
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(matrix[i][j]==' ')
                {
                    matrix[i][j]=userch;
                    if(CheckWin(matrix)==userch)
                    {
                        matrix[i][j]=' ';
                        return new int[]{i,j};
                    }
                    matrix[i][j]=' ';
                }
            }
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        char[][] matrix = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Enter your charachter (X or O):");
        char user = input.next().charAt(0);
        char computer = (user == 'X') ? 'O' : 'X'; 
        int row,column; 
        char currentch = 'X';
        while (CheckWin(matrix) == ' ') {
            clearScreen();
            PrintBox(matrix);
            
            if (currentch == user)
            {
                System.out.println("Your turn");
                System.out.println("Enter the order of row and column (from 1 to 3)");
                row = input.nextInt() - 1;
                column = input.nextInt() - 1;
                while (matrix[row][column] != ' ') {
                    System.out.println("Invalid move! Try again.");
                    row = input.nextInt() - 1;
                    column = input.nextInt() - 1;
                    
                }
                matrix[row][column] = user;

            }
             
            else
            {
                System.out.println("Computer's turn");
                int[] attackMove = ComputerAttack(matrix, computer);
                int[] defendMove = ComputerDefend(matrix, user);
                if (attackMove[0] != -1) {
                    row = attackMove[0];
                    column = attackMove[1];
                    matrix[row][column]=computer;
                }
                else if(defendMove[0]!= -1)
                {
                    row = defendMove[0];
                    column = defendMove[1];
                    matrix[row][column]=computer;

                }
                else
                {
                    row = random.nextInt(3);
                    column = random.nextInt(3);
                    while (matrix[row][column] != ' ') {
                        row = random.nextInt(3);
                        column = random.nextInt(3);
                    }
                    matrix[row][column] = computer;
                }


            }
            if (CheckWin(matrix) != ' ') {
                clearScreen();
                PrintBox(matrix);
                char winner = CheckWin(matrix);
                if (winner == user) {
                    System.out.println("Congratulations! You win!");
                } else {
                    System.out.println("Computer wins! Better luck next time.");
                }
                break;
                
            }
            if(isBoardFull(matrix)==true)
            {
                clearScreen();
                PrintBox(matrix);
                System.out.println("No one won ");
                break;
            }
            currentch = (currentch == 'X') ? 'O' : 'X';

            
            
        }
        
        
    }
}
