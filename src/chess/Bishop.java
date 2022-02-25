package chess;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		//Verify is a generally valid move
		if (!(super.isValidMove(move, board)))
			return false;

		if (this.player() == Player.WHITE) {
			for (int i = 0; i <= 7  ; i++) {
				if ((move.toRow + i == move.fromRow && (move.toColumn + i == move.fromColumn)) ||
						(move.toRow + i == move.fromRow && (move.toColumn  - i == move.fromColumn))) {
					return true;
				}
				if ((move.toRow - i == move.fromRow && (move.toColumn + i == move.fromColumn)) ||
						(move.toRow - i == move.fromRow && (move.toColumn  - i == move.fromColumn))) {
					return true;
				}
			}
		}

		//For now, return false
		return false;
		// More code is needed


	}
}
