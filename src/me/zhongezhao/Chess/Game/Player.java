package me.zhongezhao.Chess.Game;

public class Player {
	public PlayerType playerType;
	public Game game;
	
	public Player(PlayerType playerType, Game game) {
		this.playerType = playerType;
		this.game = game;
	}

}
