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

		//Verify is a generally valid move
		if (!(super.isValidMove(move, board)))
			return false;

		// Check for valid Pawn move

		if (this.player() == Player.WHITE) {
			//Allows you to move diagonally up and over one piece to capture
			if (((move.toRow + 1 == move.fromRow) && (move.fromColumn == move.toColumn - 1)) ||
					((move.toRow + 1 == move.fromRow) && (move.fromColumn == move.toColumn + 1))) {
				//Looks to see if other player is there so move can be accomplished
				if (board[move.toRow][move.toColumn] != null &&
						board[move.toRow][move.toColumn].player() != this.player()) {
					return true;
				}
			}
			//Allows the piece to move forward one space
			if (move.toRow + 1 == move.fromRow && move.toColumn == move.fromColumn) {
				//Looks to see if chess piece already in spot trying to be moved to
				if (board[move.toRow][move.toColumn] == null) {
					return true;
				}
			}
			//If in row 6 it's the pawns first move, so it can move one or two if it wants
			if (move.fromRow == 6) {
				if ((move.toRow + 1 == move.fromRow && move.toColumn == move.fromColumn)) {
					//Looks to see if chess piece already in spot trying to be moved to
					if (board[move.toRow][move.toColumn] == null) {
						return true;
					}
				}
				//code that executes the move 2 spaces
				if ((move.toRow + 2 == move.fromRow && move.toColumn == move.fromColumn)) {
					if (board[5][move.fromColumn] == null && board[4][move.fromColumn] == null) {
						return true;
					}
				}
			}

		}
		else if (this.player() == Player.BLACK) {
			//Allows you to move diagonally down and over one piece to capture
			if (((move.toRow - 1 == move.fromRow) && (move.fromColumn == move.toColumn - 1)) ||
					((move.toRow - 1 == move.fromRow) && (move.fromColumn == move.toColumn + 1))) {
				//Looks to see if other player is there so move can be accomplished
				if (board[move.toRow][move.toColumn] != null &&
						board[move.toRow][move.toColumn].player() != this.player()) {
					return true;
				}
			}
			//Allows the piece to move forward one space
			if (move.toRow - 1 == move.fromRow && move.toColumn == move.fromColumn) {
				//Looks to see if chess piece already in spot trying to be moved to
				if (board[move.toRow][move.toColumn] == null) {
					return true;
				}
			}
			//If in row 1 it's the pawns first move, so it can move one or two if it wants
			if (move.fromRow == 1) {
				if ((move.toRow - 1 == move.fromRow && move.toColumn == move.fromColumn)) {
					//Looks to see if chess piece already in spot trying to be moved to
					if (board[move.toRow][move.toColumn] == null) {
						return true;
					}
				}
				//code that executes the move 2 spaces
				if ((move.toRow - 2 == move.fromRow && move.toColumn == move.fromColumn)) {
					if (board[2][move.fromColumn] == null && board[3][move.fromColumn] == null) {
						return true;
					}
				}
			}

		}
		return false;
	}
}
