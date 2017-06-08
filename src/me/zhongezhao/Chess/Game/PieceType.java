package me.zhongezhao.Chess.Game;

public enum PieceType {
	King, Queen, Rook, Bishop, Knight, Pawn;
	
	@Override
	public String toString() {
		switch(this) {
		case King: return "King";
		case Queen: return "Queen";
		case Rook: return "Rook";
		case Bishop: return "Bishop";
		case Knight: return "Knight";
		case Pawn: return "Pawn";
		default: throw new IllegalArgumentException();
		}
	}
	
	
}
