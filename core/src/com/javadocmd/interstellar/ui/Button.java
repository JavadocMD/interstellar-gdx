package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.javadocmd.interstellar.util.ButtonListener;

abstract public class Button extends Actor {

	public Button(Vector2 position) {
		setSize(40, 40);
		setCenterPosition(position.x, position.y);

		addListener(new ButtonListener() {

			@Override
			public void clicked() {
				Button.this.clicked();
			}

			@Override
			public void mouseOver() {
				HudController.get().setToolTip(Button.this.getTip());
			}

			@Override
			public void mouseOut() {
				HudController.get().setToolTip("");
			}
		});
	}

	abstract public void clicked();

	abstract public String getTip();

	public Texture getTexture() {
		return Art.pixture;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
	}
}
