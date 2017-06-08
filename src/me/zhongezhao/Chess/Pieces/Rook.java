package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.NoPieceBetweenHandler;
import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;

public class Rook extends Piece {
	
	/**
	 * A boolean indicating whether the Rook has previously moved
	 */
	private boolean moved;

	public Rook(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2656";
		blackCodePoint = "\u265c";
		setMoved(false);
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];
		
		if (x == finalX && y == finalY) {
			return false;
		} else if (x == finalX || y == finalY) {
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
		return PieceType.Rook;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	
	

}
