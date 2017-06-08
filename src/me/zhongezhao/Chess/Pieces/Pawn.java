package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;
import me.zhongezhao.Chess.Game.PlayerType;

public class Pawn extends Piece {

	public Pawn(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2659";
		blackCodePoint = "\u265f";
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];
		
		if (player.playerType == PlayerType.Black) {
			if (finalX == x + player.game.gameBoard.BLACK_ADVANCING_DIRECTION) {
				if (Math.abs(y - finalY) == 1){
					if (destPiece != null && destPiece.player != player) {
						return true;
					}
				}
				if (y == finalY) {
					if (destPiece == null) {
						return true;
					}
				}
			} else if (finalX == x + 2 * player.game.gameBoard.BLACK_ADVANCING_DIRECTION) {
				if (y == finalY && x == 1 && destPiece == null) {
					return true;
				}
			}
		} else {
			if (finalX == x + player.game.gameBoard.WHITE_ADVANCING_DIRECTION) {
				if (Math.abs(y - finalY) == 1){
					if (destPiece != null && destPiece.player != player) {
						return true;
					}
				}
				if (y == finalY) {
					if (destPiece == null) {
						return true;
					}
				}
			} else if (finalX == x + 2 * player.game.gameBoard.WHITE_ADVANCING_DIRECTION) {
				if (y == finalY && x == 6) {
					return true;
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PieceType getType() {
		return PieceType.Pawn;
	}


}
