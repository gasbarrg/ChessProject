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
		boolean valid = false;

		//Check that piece is inside board
		if(move.fromRow > 8 && move.toColumn > 8)
			return false;

		//Verify this piece is located at [move.fromRow, move.fromColumn] on the board.
		if(!(this == board[move.fromRow][move.fromColumn]))
			return false;

		//Verify that the board at location [move.toRow, move.toColumn]
		//does not contain a piece belonging to the same player
		//TODO

		//Check that the piece is not in the same position
		if (!((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)))
			return true;

		//All other cases, return false
		return false;
	}
}
