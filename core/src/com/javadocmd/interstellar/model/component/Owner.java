package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Player;

public class Owner extends Component {

	private Player owner;

	public Owner(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
