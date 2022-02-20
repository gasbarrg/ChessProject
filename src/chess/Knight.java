package chess;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid = false;
        // More code is needed

		//For now, return false
		return valid;
		
	}

}
