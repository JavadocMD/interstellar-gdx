package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Player;

public class PlanetBody extends Actor {

	private Color auraColor = null;
	private Texture tex;

	public PlanetBody(float x, float y, Planet.Type type) {
		setSize(50, 50);
		setCenterPosition(x, y);
		setColor(type.getColor());
		tex = type.getTexture();
	}

	public void updateOwner(Player owner) {
		if (owner == null) {
			auraColor = null;
		} else if (owner.isUser()) {
			auraColor = Art.allyBlue.cpy();
		} else {
			auraColor = Art.enemyRed.cpy();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (auraColor != null) {
			batch.setColor(auraColor);
			batch.draw(Art.aura, getX() - 10, getY() - 10, getWidth() + 20,
					getHeight() + 20);
		}

		batch.setColor(getColor());
		batch.draw(tex, getX(), getY(), getWidth(), getHeight());
	}
}
