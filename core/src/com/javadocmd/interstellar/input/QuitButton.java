package com.javadocmd.interstellar.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class QuitButton extends InputAdapter {

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.ESCAPE:
				Gdx.app.exit();
				return true;
			default:
				return false;
		}
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.ESCAPE:
				return true;
			default:
				return false;
		}
	}
}
