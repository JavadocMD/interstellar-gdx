package com.javadocmd.interstellar.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

abstract public class ButtonListener extends ClickListener {

	abstract public void clicked();

	abstract public void mouseOver();

	abstract public void mouseOut();

	@Override
	public void clicked(InputEvent event, float x, float y) {
		super.clicked(event, x, y);
		clicked();
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer,
			Actor fromActor) {
		super.enter(event, x, y, pointer, fromActor);
		// Gdx.app.log("ButtonListener",
		// String.format("mouseOver %f,%f,%d", x, y, pointer));
		if (pointer == -1)
			mouseOver();
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer,
			Actor toActor) {
		super.exit(event, x, y, pointer, toActor);
		// Gdx.app.log("ButtonListener",
		// String.format("mouseOut %f,%f,%d", x, y, pointer));
		if (pointer == -1)
			mouseOut();
	}
}
