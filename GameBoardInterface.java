package Connect4;

import javax.swing.JButton;

public interface GameBoardInterface {
	
	//public boolean drop_marker_in_Board(JButton btn);
	public void displayBoard();
	public void clearBoard();
	public void takeTurn();
	public void displayWinner();
	public boolean isFull();
	public boolean isEmpty();
	public boolean isWinner();
	public boolean isWinner(String currPlayerName);
	

}
