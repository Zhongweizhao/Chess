package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;

public class Knight extends Piece{

	public Knight(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2658";
		blackCodePoint = "\u265e";
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		int dx = finalX - x;
		int dy = finalY - y;
		
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];

		if ((Math.abs(dx) == 1 && Math.abs(dy) == 2) ||
			(Math.abs(dx) == 2 && Math.abs(dy) == 1)) {
			if (destPiece != null) {
				// cannot eat piece from the same team
				if (destPiece.player == player) {
					return false;
				}
			}
			return true;
		}
		else 
			return false;
	}
	
	@Override
	public PieceType getType() {
		return PieceType.Knight;
	}
}
