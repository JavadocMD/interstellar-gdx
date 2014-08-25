package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Path extends Actor {

	public Path(Vector2 atob) {
		setSize(atob.len(), 10);
		setPosition(0, -getHeight() / 2);
		setOrigin(0, getHeight() / 2);
		setRotation(atob.angle());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(Art.path, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), 1f, 1f, getRotation(), 0, 0, 10, 10,
				false, false);
	}
}
