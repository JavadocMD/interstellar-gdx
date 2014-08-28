package com.javadocmd.interstellar.model;

import java.util.List;

import com.javadocmd.interstellar.ui.HudController;

public class GameController {

	private static GameController INSTANCE = null;

	public static GameController get() {
		return INSTANCE;
	}

	public static void init(GameController game) {
		INSTANCE = game;
	}

	// ---- Instance ----//

	private float timeScale;
	private List<Player> players;
	private Map map;

	public GameController(List<Player> players, Map map) {
		this.timeScale = 1f;
		this.players = players;
		this.map = map;
	}

	public float getTimeScale() {
		return timeScale;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getUser() {
		return players.get(0);
	}

	public Map getMap() {
		return map;
	}

	public void win(Player player) {
		//Gdx.app.log("GameController", player.toString() + " won");
		
		final String message;
		if (player.isUser()) {
			message = "Player won! (Restart to play again.)";
		} else {
			message = "Player lost. :( (Restart to play again.)";
		}
		HudController.get().freezeGame(message);
	}
}
