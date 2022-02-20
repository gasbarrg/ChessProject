package chess;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		//Check that piece is inside board
		if(move.toRow > 8 || move.toColumn > 8)
			return false;

		//Verify this piece is located at [move.fromRow, move.fromColumn] on the board.
		if(!(this == board[move.fromRow][move.fromColumn]))
			return false;

		//Verify that the board at location [move.toRow, move.toColumn]
		//does not contain a piece belonging to the same player
		if (board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == owner)
			return false;

		//Check that the piece is not in the same position
		if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn))
			return false;

		//All other cases, return false
		return true;
	}
}
