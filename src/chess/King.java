package chess;

public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "King";
	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// More code is needed

		//top 3 squares
		if ((move.toRow + 1 == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
				move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn))) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		//middle 3 squares
		if ((move.toRow == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
				move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn))) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}
		//bottom 3 squares
		if ((move.toRow - 1 == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
				move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn))) {
			//if space has nothing on it or occupied by other player
			if ((board[move.toRow][move.toColumn] == null ||
					board[move.toRow][move.toColumn].player() != this.player())) {
				return true;
			}
		}

		return false;
	}
}


