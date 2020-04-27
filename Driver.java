package Connect4;

public class Driver {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//Connect4_Gui gui = new Connect4_Gui();
				Starting_Frame gui1 = new Starting_Frame();
			}
		});

	}


}
