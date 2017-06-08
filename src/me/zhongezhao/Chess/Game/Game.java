package me.zhongezhao.Chess.Game;

import java.awt.Graphics;

public class Game {
	
	public Player playerBlack;
	public Player playerWhite;
	public GameBoard gameBoard;
	public Log log;
	public Player currentPlayer;
	public Player winner;
	public boolean whiteBeenCheckmate;
	public boolean blackBeenCheckmate;
	
	public Game() {
		playerBlack = new Player(PlayerType.Black, this);
		playerWhite = new Player(PlayerType.White, this);
		currentPlayer = playerWhite;
		winner = null;
		gameBoard = new GameBoard(this);
		whiteBeenCheckmate = false;
		blackBeenCheckmate = false;
		log = new Log(this);
	}
	
	public void render(Graphics g) {
		gameBoard.render(g);
	}
	
	public void switchPlayer() {
		if (currentPlayer == playerWhite) {
			currentPlayer = playerBlack;
		} else {
			currentPlayer = playerWhite;
		}
	}
}
