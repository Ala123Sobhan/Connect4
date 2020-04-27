package Connect4;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;



public class Connect4_Gui extends JFrame{
	private JPanel jpMain;
	private Connect4board jpboard;
	private ScoreBoard jpScoreBoard;
	
	
	private Player currPlayer;
	private Player player1;
	private Player player2;
	int gameNumber = 0;
	final int NUM_ROWS;
    final int NUM_COLS;
	
	
	
	
	
	
	
	public Connect4_Gui(Player pl1, Player pl2, int row, int col){
		
		//player1 = new Player("Ala", "A");
		//player2 = new Player("Pavel", "P");
	    NUM_ROWS = row;
        NUM_COLS = col;
		
		player1 = pl1;
		player2 = pl2;
		//player1.setSymbol("A");
		//player2.setSymbol("P");
	    currPlayer = player1;
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());

		
		jpboard = new Connect4board();
		jpScoreBoard = new ScoreBoard();
	    
		
		
		jpMain.add(jpScoreBoard, BorderLayout.NORTH);
		jpMain.add(jpboard, BorderLayout.CENTER);
		
		
		this.add(jpMain);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	private class Connect4board extends JPanel implements GameBoardInterface, ActionListener{
		
		private JButton [][] board;
		//private static final int NUM_ROWS = 6;
		//private static final int NUM_COLS = 7;
	
	
		
		public Connect4board(){
			setLayout(new GridLayout(NUM_ROWS,NUM_COLS));
			displayBoard();
		}
		
		
		
		
		public boolean drop_marker_in_Board(int col1) {
			
			boolean res = false;
			if(!(board[0][col1].getText().equals("")))
			{
				
				return false;
			}
			for(int r = board.length-1; r>=0 ; r--)
			{
				if(board[r][col1].getText().equals(""))
				{
					if(currPlayer == player1)
					{
						//System.out.println("here");
						board[r][col1].setBackground(Color.RED);
						board[r][col1].setText(player1.getPlayerSymbol());
						board[r][col1].setEnabled(false);
						return true;
					}
					else {
						board[r][col1].setBackground(Color.GREEN);
						board[r][col1].setText(player2.getPlayerSymbol());
						board[r][col1].setEnabled(false);
						return true;
					}
					
					
				}
			}
			
		
		return res;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton btnclk = (JButton) e.getSource();
			
			jpScoreBoard.currPlayerName.setText(currPlayer.getPlayerName());
			
			
            int col = 0;
            for(int r =0; r<board.length; r++ )
            {
            	for(int c=0; c<board[r].length; c++)
            	{
            		if(board[r][c] == btnclk)
            		{
            			col = c;
            			break;
            		}
            	}
            }
            if( drop_marker_in_Board(col))

            {
            	
            	if(isWinner(currPlayer.getPlayerSymbol()))
            	{
            		 gameNumber++;
            		 JOptionPane.showMessageDialog(null, "Winner Is " + currPlayer.getPlayerName());
            		 displayWinner();
            		 String msg = "The game number ("+gameNumber+") is won by "+currPlayer.getPlayerName();
            		 writetoFile("Connect4-Results.txt",msg);
            		 promptAgain();
            		 
            	}
            	
            	else if(isFull())
            	{    
            		 gameNumber++;
            		 JOptionPane.showMessageDialog(null, "DRAW");
            		 String msg_2 = "The game number ("+gameNumber+") is draw ";
            		 writetoFile("Connect4-Results.txt",msg_2);
            		 promptAgain();
            	}
				
            }
            takeTurn();
			
		}


		@Override
		public void displayBoard() {
			board = new JButton[NUM_ROWS][NUM_COLS];
			for(int r = 0; r<board.length; r++){
				for(int c = 0; c < board[r].length; c++){
					board[r][c] = new JButton();
					Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
					board[r][c].setOpaque(true);
					board[r][c].setBackground(Color.WHITE);
					board[r][c].setFont(bigFont);
					board[r][c].addActionListener(this);
					board[r][c].setEnabled(true);
					board[r][c].setText("");
					this.add(board[r][c]);
					
				}
			}
			
		}

		@Override
		public void clearBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col < board[row].length; col++){
					board[row][col].setOpaque(true);
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setText("");
					board[row][col].setEnabled(true);
				}
			}
			
		}

		@Override
		public void takeTurn() {
			if(currPlayer.equals(player1)){
				currPlayer = player2;
			}
			else{
				currPlayer = player1;
			}
			
			
		}

		@Override
		public void displayWinner() {
			currPlayer.addNumWins();
			jpScoreBoard.lastWinnerName.setText(currPlayer.getPlayerName());
			if(currPlayer.equals(player1)){
				jpScoreBoard.player1Score.setText(String.format("%02d",player1.getNumWins()));
			}
			else{
				jpScoreBoard.player2Score.setText(String.format("%02d",player2.getNumWins()));
			}
			
			
		}
		
		private boolean isDraw() {
			if(isFull())
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
 
		private void promptAgain(){
			String contents;
			
			
			int yesNo = JOptionPane.showConfirmDialog(null,  "Play Again?", "Yes or No",JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION){ //want to play more
				clearBoard();
				
			}
			else{   //no more play, update champ
				if(player1.getNumWins() > player2.getNumWins())
				{
					jpScoreBoard.champName.setText(player1.getPlayerName());
				    contents = "CHAMPION is "+player1.getPlayerName()+"\n";
					contents += "Player1: "+player1.getPlayerName()+" has won "+player1.getNumWins()+" game in this round"+"\n";
					contents += "Player2: "+player2.getPlayerName()+" has won "+player2.getNumWins()+" game in this round"+"\n";
					writetoFile("Connect4-Results.txt",contents);
					
				}
				else if(player1.getNumWins() < player2.getNumWins()) {
					jpScoreBoard.champName.setText(player2.getPlayerName());
					contents = "CHAMPION is "+player2.getPlayerName()+"\n";
					contents += "Player1: "+player1.getPlayerName()+" has won "+player1.getNumWins()+" game in this round"+"\n";
					contents += "Player2: "+player2.getPlayerName()+" has won "+player2.getNumWins()+" game in this round"+"\n";
					writetoFile("Connect4-Results.txt",contents);
					
				}
				else {
					contents = "The round is a DRAW!!"+"\n";
					contents += "Player1: "+player1.getPlayerName()+" has won "+player1.getNumWins()+" game in this round"+"\n";
					contents += "Player2: "+player2.getPlayerName()+" has won "+player2.getNumWins()+" game in this round"+"\n";
					writetoFile("Connect4-Results.txt",contents);
					
				}
				
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		private void writetoFile(String fileName, String content)
		{
			
			PrintWriter outStream = null;
			try {
				
//				outStream = new PrintWriter(fileName);//connect to the file on the system
				FileOutputStream fOutStrm = new FileOutputStream(fileName, true);
				outStream = new PrintWriter(fOutStrm);
				outStream.println(content); //write to the file
      			outStream.flush();//to be sure content is written to destination we flush
				
			} catch (FileNotFoundException e) {
				System.out.println("Could not write to file "+ fileName);
				e.printStackTrace();
			}
			finally{
				if(outStream != null){
					outStream.close();//close the connection to the file
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@Override
		public boolean isFull() {
			 for ( int row = 0 ; row < this.board.length ; row++ ) {
	             for ( int col = 0 ; col < this.board[row].length ; col++ ) {
	                 if (board[row][col].getText().equals("") && board[row][col].getBackground().equals(Color.WHITE)) {
	                     return false;
	                 }
	             }
	         }
	         return true;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWinner() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWinner(String currPlayerSymbol) {
			
			//row check 0,1,2,3
			for(int r= 0 ;r <board.length; r++ )
			{
				for(int c =0 ; c<board[r].length-3; c++)
				{
					if(!(board[r][c].getText().equals(""))&& 
						(board[r][c].getText().equals(currPlayerSymbol))&&
						(board[r][c].getText().equals( board[r][c+1].getText()))&& 
						(board[r][c].getText().equals( board[r][c+2].getText())) &&
				        (board[r][c].getText().equals( board[r][c+3].getText())))
					    {
						  return true;
					}
				}
			}
			//col check same column different rows --4 at a stretch r0,r1,r2,r3
			for(int c=0 ; c< board[0].length; c++)
			{
				for(int r= 0; r<board.length -3; r++)
				{
					if( !(board[r][c].getText().equals(""))&&
						 (board[r][c].getText().equals(currPlayerSymbol))&&
						 (board[r][c].getText().equals(board[r+1][c].getText()))&&
						 (board[r][c].getText().equals(board[r+2][c].getText()))&&
						 (board[r][c].getText().equals(board[r+3][c].getText())))
					     {
						 return true;
					}
				}
			}	
			//main diagonal check 00 11 22 33 at a stretch
			for(int r=0 ; r <board.length -3; r++)
			{
				for(int c =0 ; c<board[r].length -3; c++)
				{
					if(!(board[r][c].getText().equals(""))&& 
					    (board[r][c].getText().equals(currPlayerSymbol))&&
					    (board[r][c].getText().equals(board[r+1][c+1].getText()))&&
					    (board[r][c].getText().equals(board[r+2][c+2].getText()))&&
					    (board[r][c].getText().equals(board[r+3][c+3].getText())))
					   {
						return true;
					}
					
				}
			}
			// secondary diagonal check 03 12 21 30
			for(int r=0 ; r <board.length -3; r++)
			{
				for(int c =3 ; c<board[r].length; c++)
				{
					if(!(board[r][c].getText().equals(""))&&
						(board[r][c].getText().equals(currPlayerSymbol))&&
						(board[r][c].getText().equals(board[r+1][c-1].getText()))&&
						(board[r][c].getText().equals(board[r+2][c-2].getText()))&&
						(board[r][c].getText().equals(board[r+3][c-3].getText())))
                        {
						return true;
					}
					
				}
			}
			
			
			
			
			return false;
		}
		
	}
	private class ScoreBoard extends JPanel{
		private JPanel genScoreInfo;
	    private JLabel labelForChamp, champName;
	    private JLabel labelForLastWinner, lastWinnerName;
	   
	    private JPanel playScoreInfo;
	    private JLabel labelPlaceHolder, labelForPlayer1Name, labelForPlayer2Name;
	    private JLabel labelForPlayerNames, player1Name, player2Name;
	    private JLabel labelForPlayerScore, player1Score, player2Score;
	    
	    private JPanel currPlayerInfo;
	    private JLabel labelForCurrPlayer, currPlayerName;
	    public ScoreBoard() {
	        setLayout(new BorderLayout());
	        genScoreInfo = new JPanel(new GridLayout(2, 2));
	        genScoreInfo.setBackground(Color.YELLOW);
	       
	        playScoreInfo = new JPanel(new GridLayout(3, 3));
	        playScoreInfo.setBackground(Color.CYAN);
	        
	        currPlayerInfo = new JPanel(new GridLayout(1,2));
	        currPlayerInfo.setBackground(Color.LIGHT_GRAY);
	       
	        labelForChamp = new JLabel("Champion");
	        champName = new JLabel("----------");
	       
	        labelForLastWinner = new JLabel("Last Winner");
	        lastWinnerName = new JLabel("----------");
	        
	         
	        JLabel[] genLabels = {labelForChamp, champName,
	                                labelForLastWinner, lastWinnerName};
	       
	        for ( int i = 0 ; i < genLabels.length ; i++ ) {
	            genLabels[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
	            genScoreInfo.add(genLabels[i]);
	        }
	       
	        labelPlaceHolder = new JLabel("          ");
	        labelForPlayer1Name = new JLabel("Player 1");
	        labelForPlayer2Name = new JLabel("Player 2");
	       
	        labelForPlayerNames = new JLabel("Name");
	        player1Name = new JLabel(player1.getPlayerName());
	        player2Name = new JLabel(player2.getPlayerName());
	       
	        labelForPlayerScore = new JLabel("Score");
	        player1Score = new JLabel("----------");
	        player2Score = new JLabel("----------");

	        JLabel[] playLabels = {labelPlaceHolder, labelForPlayer1Name, labelForPlayer2Name,
	                                labelForPlayerNames, player1Name, player2Name,
	                                labelForPlayerScore, player1Score, player2Score};
	       
	        for ( int i = 0 ; i < playLabels.length ; i++ ) {
	            playLabels[i].setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
	            playScoreInfo.add(playLabels[i]);
	        }
	       
	        
	        labelForCurrPlayer = new JLabel("Current Player") ;
	        labelForCurrPlayer.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
	        currPlayerInfo.add(labelForCurrPlayer);
	        
	        currPlayerName = new JLabel("----------");
	        currPlayerName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
	        currPlayerInfo.add(currPlayerName);
	        
	        add(playScoreInfo, BorderLayout.CENTER);
	        add(genScoreInfo, BorderLayout.NORTH);
	        add(currPlayerInfo,BorderLayout.AFTER_LAST_LINE);
	        
	    }
	   
	
	
	}//END OF SCOREBOARD
	
	
}
