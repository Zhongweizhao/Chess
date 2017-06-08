package me.zhongezhao.Chess.Game;

import me.zhongezhao.Chess.Pieces.Piece;

public class Log {
	
	private Game game;

	public Log(Game game) {
		this.game = game;
	}
	
	public void saveCapture(Piece from, Piece to, boolean promotion, boolean castling) {
		System.out.println(from.toString() + " captures " + to.toString());
	}
	
	public void saveMove(Piece from, int toX, int toY, boolean promotion, boolean castling) {
		System.out.println(from.toString() + " moves to " + toX + ", " + toY);
	}
	
	public void previous() {
		
	}
	
}
