package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.ai.AiProgram;

public class Ai extends Component {

	final public Player player;
	final public AiProgram program;

	public Ai(Player player, AiProgram program) {
		this.player = player;
		this.program = program;
	}
}
