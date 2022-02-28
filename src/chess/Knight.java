package chess;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;
		// More code is needed
		if (!(super.isValidMove(move, board)))
			return false;


		//if player moves up one and over two to the left or right
		if ((move.toRow + 1 == move.fromRow) && (move.toColumn + 2 == move.fromColumn) ||
				(move.toRow + 1 == move.fromRow) && (move.toColumn - 2 == move.fromColumn)) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		//if player moves down one and over two to the left or right;
		if ((move.toRow - 1 == move.fromRow) && (move.toColumn + 2 == move.fromColumn) ||
				(move.toRow - 1 == move.fromRow) && (move.toColumn - 2 == move.fromColumn)) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		//if the player moves up two rows and over one to the left or right
		if ((move.toRow + 2 == move.fromRow) && (move.toColumn + 1 == move.fromColumn) ||
				(move.toRow + 2 == move.fromRow) && (move.toColumn - 1 == move.fromColumn)) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		//if the player moves down 2 rows and over 1 column to the left or right;
		if ((move.toRow - 2 == move.fromRow) && (move.toColumn + 1 == move.fromColumn) ||
				(move.toRow - 2 == move.fromRow) && (move.toColumn - 1 == move.fromColumn)) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		return false;
	}
}
