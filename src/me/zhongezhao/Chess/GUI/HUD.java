package me.zhongezhao.Chess.GUI;

import java.awt.Font;
import java.awt.Graphics;

import me.zhongezhao.Chess.Game.Game;
import me.zhongezhao.Chess.Game.PlayerType;
import me.zhongezhao.Chess.Util.Constant;

public class HUD {
	
	private Game game;
	private int x, y;
	
	public HUD(Game game) {
		this.game = game;
		x = Constant.HUD_X + 70;
		y = Constant.HUD_Y + 200;
	}

	public void render(Graphics g) {
		
		g.setColor(MyColor.BLACK);
		g.setFont(new Font("Open Sans", Font.PLAIN, 40));
		if (game.currentPlayer.playerType == PlayerType.Black) {
			g.drawString("Black's turn", x, y);
		} else {
			g.drawString("White's turn", x, y);
		}
		
		if (game.winner != null) {
			g.setColor(MyColor.WHITE);
			g.setFont(new Font("Open Sans", Font.PLAIN, 40));
			if (game.winner.playerType == PlayerType.Black) {
				g.drawString("Black Win!", x, y + 40);
			} else {
				g.drawString("White Win!", x, y + 40);
			}
		} else {
			if (game.blackBeenCheckmate) {
				g.setColor(MyColor.WHITE);
				g.setFont(new Font("Open Sans", Font.PLAIN, 40));
				g.drawString("Checkmate!", x, y + 40);
			}
			
			if (game.whiteBeenCheckmate) {
				g.setColor(MyColor.BLACK);
				g.setFont(new Font("Open Sans", Font.PLAIN, 40));
				g.drawString("Checkmate!", x, y + 40);
			}
		}
			
	}
	
}
