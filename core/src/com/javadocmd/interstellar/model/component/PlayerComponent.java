package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Player;

public class PlayerComponent extends Component {

	final public Player player;
	
	public PlayerComponent(Player player) {
		this.player = player;
	}
}
