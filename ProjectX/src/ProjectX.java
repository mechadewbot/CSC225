import java.util.*;
import java.security.*;
import java.io.*;






public class ProjectX {
	
	public static final String FILE = "C:\\Users\\dave.dewar\\testDir\\projectX.txt";
	public static final String[][] BOARD = new String[5][5];
	public static int eRow;
	public static int eCol;
	public static int wRow;
	public static int wCol;
	public static char saveStat;
	public static int p1Count;
	public static char player;
	public static int p2Count;
	
	//Making fancy colors
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	

	public static void main(String[] args) throws java.io.IOException {
		/* 
		 * Fixed dumb error regarding Wookiee finding the Wookiee. Whoops. 
		 * 
		 * Changed boolean win from public static to variable initialized in main()
		 * 
		 * TODO: write player move count to text file, initialize player move count from text file
		 * 
		 */
			
		intro();
			player = 'E';
			p1Count = 0;
			p2Count = 0;
			saveStat = ' ';
			
			
			while(saveStat == ' ')
			{
				if(player == 'E')
				{
					loadGame(BOARD);
					BOARD[eRow][eCol] = "|_|";
					System.out.println("Ewok moves now: ");
					move(eRow,eCol);
					p1Count++;
					saveGame(BOARD);
					BOARD[eRow][eCol] = "|E|";
					saveGame(BOARD);
					boolean win = winCheck();
					if(win == true)
					{
						System.out.println("Ewok has found the Wookiee! Congratulations!");
						System.out.println("Ewok has won the game in "+p1Count+1+" moves.");
						saveStat = 'Q';
					}
					else
					{
					System.out.println("Ewok has moved "+p1Count+" times");
					player = 'W';
					}
				}
				else
				{
					loadGame(BOARD);
					BOARD[wRow][wCol] = "|_|";
					System.out.println("Wookiee moves now: ");
					move(wRow,wCol);
					p2Count++;
					saveGame(BOARD);
					BOARD[wRow][wCol] = "|W|";
					saveGame(BOARD);
					boolean win = winCheck();
					if(win == true)
					{
						System.out.println(ANSI_RED_BACKGROUND+"Wookiee has found the Ewok! Congratulations!");
						System.out.println("Wookiee has won the game in "+p2Count+" moves.");
						saveStat = 'Q';
					}
					else
					{
					System.out.println("Wookiee has moved "+p2Count+" times");
					player = 'E';
					}
					
				}
			}
			
		
	} //End Main
	
	
	
	public static void loadGame(String[][] b) throws java.io.IOException
	{
		Scanner input = new Scanner(new File(FILE));
		String ltr = "";
		for(int r = 0;r<b.length;r++)
		{
			for(int c = 0;c<b[r].length;c++)
			{
				ltr = input.next();
				if(ltr.equals("o"))
					b[r][c] = "|_|";
				else
					b[r][c] = ltr.trim();
				
				if(ltr.equals("|E|"))
				{
					eRow = r;
					eCol = c;
				}
				if(ltr.equals("|W|"))
				{
					wRow = r;
					wCol = c;
				}
				System.out.print(b[r][c]);
			}
			System.out.println();
			
		}
		input.close();
		
		
}//End loadGame
	
	
	
	public static void move(int r, int c) 
	{
		
		if(r == 0 && c == 0)
		{
			System.out.println("Which direction will you move (D/R): ");
		}
		else if(r == 0)
		{
			System.out.println("Which direction will you move (D/L/R): ");
		}
		else if(c == 0)
		{
			System.out.println("Which direction will you move (U/D/R): ");
		}
		else if(r == 4 && c == 4)
		{
			System.out.println("Which direction will you move (U/L): ");
		}
		else if(c == 4)
		{
			System.out.println("Which direction will you move (U/D/L): ");
		}
		else if(r == 4)
		{
			System.out.println("Which direction will you move (U/L/R): ");
		}
		else 
		{
			System.out.println("Which direction will you move (U/D/L/R): ");
		}
		
		Scanner in = new Scanner(System.in);
		char dir = in.next().charAt(0);
		dir = Character.toUpperCase(dir);
		
		if(r == 4 && dir == 'D')
		{
			System.out.println("Invalid choice.");
			move(wRow,wCol);
		}
		else if(c == 4 && dir == 'R')
		{
			System.out.println("Invalid choice.");
			move(wRow,wCol);
		}
		else if( r == 0 && dir == 'U')
		{
			System.out.println("Invalid choice.");
			move(wRow,wCol);
		}
		else if(c == 0 && dir == 'L')
		{
			System.out.println("Invalid choice.");
			move(wRow,wCol);
		}
		if(player == 'W')
		{
		switch(dir)
		{
		case 'U': wRow = wRow - 1;break;
		case 'D': wRow = wRow + 1;break;
		case 'L': wCol = wCol - 1;break;
		case 'R': wCol = wCol + 1;break;
		case 'Q': saveStat = 'Q';break;
		default: System.out.println("Invalid choice");
		move(wRow,wCol);
		in.close();
		}
		}
		else
		{
			switch(dir)
			{
			case 'U': eRow = eRow - 1;break;
			case 'D': eRow = eRow + 1;break;
			case 'L': eCol = eCol - 1;break;
			case 'R': eCol = eCol + 1;break;
			case 'Q': saveStat = 'Q';break;
			default: System.out.println("Invalid choice");
			move(eRow,eCol);
			in.close();
			}
			
		}
		
}//End Move
	
	public static void saveGame(String[][] board) throws java.io.IOException
	
	
	{
		
		PrintWriter output = new PrintWriter(FILE);
		String letter = " ";
		for(int r = 0;r<5;r++)
		{
			for(int c = 0;c<5;c++)
			{
				letter = board[r][c];
				if(letter ==("|_|"))
					board[r][c]="o";
				
				output.println(board[r][c]);
			}
		}
		
		output.close();
	}//End saveGame
	public static boolean winCheck()
	{
		boolean win = false;
		
		if(player == 'E')
		{
			if(BOARD[eRow][eCol] == BOARD[wRow][wCol])
			{
				win = true;
			}
			else
			{
				win = false;
			}
			
		}
		else if(player == 'W')
		{
			if(BOARD[wRow][wCol] == BOARD[eRow][eCol])
			{
				win = true;
			}
			else
			{
				win = false;
			}
		}
		return win;
	}//End winCheck
	
	public static void intro()
	{
		System.out.println(ANSI_BLUE_BACKGROUND+"Nobody knows what an Ewok was doing in the Dagobah System, let alone why a Wookiee was there.");
		System.out.println(ANSI_BLUE_BACKGROUND+"Yet here they were...");
		
	}
	
	
	
} //End Class
