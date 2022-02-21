package chess;

public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	public String type() {
		return "King";
	}


	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// More code is needed
		if (this.player() == Player.WHITE) {
			//top 3 squares
			if ((move.toRow + 1 == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
					move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn ))){
				if ((board[move.toRow][move.toColumn] == null)) {
					return true;
				}
				if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
					return true;
				}
			}
			//middle 3 squares
			if((move.toRow == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
					move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn ))){
				if ((board[move.toRow][move.toColumn] == null)) {
					return true;
				}
				if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
					return true;
				}
			}
		}
		//bottom 3 squares
		if((move.toRow - 1 == move.fromRow && (move.toColumn + 1 == move.fromColumn ||
				move.toColumn - 1 == move.fromColumn || move.toColumn == move.fromColumn ))){
			if ((board[move.toRow][move.toColumn] == null)) {
				return true;
			}
			if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
				return true;
			}
		}
// 			FIRST ATTEMPT AT THE CODE
//			if (move.toRow + 1 == move.fromRow || move.toRow - 1 == move.fromRow ||
//					move.toColumn + 1 == move.fromColumn || move.toColumn - 1 == move.fromColumn) {
//				if ((board[move.toRow][move.toColumn] == null)) {
//					return true;
//				}
//				if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
//					return true;
//				}
//
//			}

		return false;
	}
}


