package chess;

public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}


	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;

		//Verify is a generally valid move
        if(!(this.isValidMove(move, board)))
			valid = false;

		//Check for valid Pawn move


		return valid;
	}
}
