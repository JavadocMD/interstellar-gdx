package com.javadocmd.interstellar.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.javadocmd.interstellar.ui.Art;

public class Debug {

	public static final Color DEBUG_COLOR = Color.RED.cpy();
	static {
		DEBUG_COLOR.a = .2f;
	}

	public static void drawDebugSquare(Batch batch, Actor actor) {
		batch.setColor(DEBUG_COLOR);
		batch.draw(Art.pixture, actor.getX(), actor.getY(), actor.getWidth(),
				actor.getHeight());
	}

	public static void drawDebugSquare(Batch batch, Group group) {
		batch.setColor(DEBUG_COLOR);
		batch.draw(Art.pixture, group.getX(), group.getY(), group.getWidth(),
				group.getHeight());
	}
}
