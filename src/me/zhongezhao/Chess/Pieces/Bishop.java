package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.NoPieceBetweenHandler;
import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;

public class Bishop extends Piece {

	public Bishop(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2657";
		blackCodePoint = "\u265d";
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];
		
		if (Math.abs(finalX - x) == Math.abs(finalY - y)) {
			if (destPiece != null) {
				// cannot eat piece from the same team
				if (destPiece.player == player) {
					return false;
				}
			}
			
			// can not be blocked by other piece
			return NoPieceBetweenHandler.assertNoPieceBetween(player.game, x, y, finalX, finalY);
		}
		return false;
	}

	@Override
	public PieceType getType() {
		return PieceType.Bishop;
	}

}
