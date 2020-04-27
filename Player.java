package Connect4;



public class Player implements Comparable<Player> {
	private String playerName, playerSymbol;
	private int numGames, numWins, numLosses, numDraws;

	public Player(){
		playerName = "Play Doe";
		playerSymbol = "#";
		numGames   = 0;
		numWins    = 0;
		numLosses  = 0;
	}
	
	public Player(String player_name, String player_sym){
		playerName = player_name;
		playerSymbol = player_sym;
		numGames = 0;
		numWins = 0;
		numLosses = 0;
	}
	
	/*protected void setName(String name) {
		this.playerName = name;
		
	}*/
	protected void setSymbol(String symb) {
		this.playerSymbol = symb;
		
	}

	protected void addNumWins(){
		numWins++;
		numGames++;
	}
	protected void addNumLosses(){
		numLosses++;
		numGames++;
	}
	protected void addDraw(){
		numDraws++;
		numGames++;
	}
	public  int getNumGames(){
		return numGames;
	}
	public  int getNumWins(){
		return numWins;
	}
	public  int getNumLosses(){
		return numLosses;
	}
	public int getNumDraws(){
		return numDraws;
	}
	public String getPlayerSymbol(){
		return playerSymbol;
	}
	public String getPlayerName(){
		return playerName;
	}
	@Override
	public String toString(){
//		return "Player [ name="+playerName+", symbol="+playerSymbol+", number of games="+numGames+
//				", wins="+numWins+", losses="+numLosses+", draws="+getNumDraws()+" ]";
		
		return String.format("Player [ name=%20s, symbol=%2s, number of games=%02d, wins=%02d, losses=%02d, draws=%02d ]"
				,playerName,playerSymbol,numGames,numWins,numLosses,getNumDraws());
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Player){
			Player otherP = (Player)o;
			if(this.playerName.equalsIgnoreCase(otherP.playerName)){
				if(this.playerSymbol.equalsIgnoreCase(otherP.playerSymbol)){
					if(this.numGames == otherP.numGames){
						if(this.numWins == otherP.numWins){
							if(this.numLosses == otherP.numLosses){
								return true;
							}
						}
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public int compareTo(Player otherP) {
		if(this.numWins < otherP.numWins){
			return -1;
		}
		else if(this.numWins > otherP.numWins){
			return 1;
		}
		return 0;
	}

}
