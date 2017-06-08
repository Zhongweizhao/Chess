package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.NoPieceBetweenHandler;
import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;

public class Queen extends Piece {

	public Queen(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2655";
		blackCodePoint = "\u265b";
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];
		
		if (x == finalX && y == finalY) {
			return false;
		} else if (x == finalX || y == finalY || Math.abs(finalX - x) == Math.abs(finalY - y)) {
			if (destPiece != null) {
				// cannot eat piece from the same team
				if (destPiece.player == player) {
					return false;
				}
			}
			
			// cannot be blocked
			return NoPieceBetweenHandler.assertNoPieceBetween(player.game, x, y, finalX, finalY);
		}
		return false;
	}

	@Override
	public PieceType getType() {
		return PieceType.Queen;
	}


}
