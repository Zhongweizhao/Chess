package me.zhongezhao.Chess.Pieces;

import me.zhongezhao.Chess.Game.CheckmateHandler;
import me.zhongezhao.Chess.Game.NoPieceBetweenHandler;
import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;
import me.zhongezhao.Chess.Game.PlayerType;

public class King extends Piece {
	
	/**
	 * A boolean indicating whether the king has previously moved
	 */
	private boolean isMoved;

	public King(int x, int y, Player player) {
		super(x, y, player);
		whiteCodePoint = "\u2654";
		blackCodePoint = "\u265a";
		setMoved(false);
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		Piece destPiece = player.game.gameBoard.boardArray[finalX][finalY];
		
		if (x == finalX && y == finalY) {
			return false;
		} else if (Math.abs(finalX - x) < 2 && Math.abs(finalY - y) < 2) {
			if (destPiece != null) {
				// cannot eat piece from the same team
				if (destPiece.player == player) {
					return false;
				}
			}
			if (player.playerType == PlayerType.Black) {
				if (CheckmateHandler.doesThisMoveCauseBlackBeenChecked(player.game, x, y, finalX, finalY)) {
					return false;
				}
			} else {
				if (CheckmateHandler.doesThisMoveCauseWhiteBeenChecked(player.game, x, y, finalX, finalY)) {
					return false;
				}
			}
			return true;
		}
		
		if (!isMoved) {
			Piece leftEndPiece = player.game.gameBoard.boardArray[x][0];
			Piece rightEndPiece = player.game.gameBoard.boardArray[x][7];
			
			if (leftEndPiece instanceof Rook) {
				if (!((Rook) leftEndPiece).isMoved()) {
					if (NoPieceBetweenHandler.assertNoPieceBetween(player.game, x, y, leftEndPiece.x, leftEndPiece.y)) {
						if (finalX == x && finalY == y - 2) {
							return true;
						}
					}
				}
			}
			if (rightEndPiece instanceof Rook) {
				if (!((Rook) rightEndPiece).isMoved()) {
					if (NoPieceBetweenHandler.assertNoPieceBetween(player.game, x, y, rightEndPiece.x, rightEndPiece.y)) {
						if (finalX == x && finalY == y + 2) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	@Override
	public PieceType getType() {
		return PieceType.King;
	}

	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	
}
