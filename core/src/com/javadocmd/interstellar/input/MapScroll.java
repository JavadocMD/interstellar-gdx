package com.javadocmd.interstellar.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class MapScroll extends InputAdapter {

	private Camera camera;
	private float scrollSpeed = 8f;
	
	private Vector2 scroll = new Vector2(0, 0);
	
	private boolean middleDrag = false;
	private int[] dragLast = new int[2];

	public MapScroll(Camera camera) {
		this.camera = camera;
	}

	public void update(float delta) {
		Vector2 move = scroll.cpy().nor().scl(scrollSpeed);
		camera.translate((int) move.x, (int) move.y, 0);
		camera.update();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (Input.Buttons.MIDDLE == button) {
			middleDrag = true;
			dragLast = new int[]{ screenX, screenY };
			return true;
		}
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (middleDrag) {
			int dx = dragLast[0] - screenX;
			int dy = screenY - dragLast[1];
			camera.translate((float) dx, (float) dy, 0f);
			dragLast[0] = screenX;
			dragLast[1] = screenY;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (Input.Buttons.MIDDLE == button) {
			middleDrag = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.A:
				scroll.add(-1, 0);
				return true;

			case Input.Keys.D:
				scroll.add(1, 0);
				return true;

			case Input.Keys.W:
				scroll.add(0, 1);
				return true;

			case Input.Keys.S:
				scroll.add(0, -1);
				return true;

			default:
				return false;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.A:
				scroll.add(1, 0);
				return true;

			case Input.Keys.D:
				scroll.add(-1, 0);
				return true;

			case Input.Keys.W:
				scroll.add(0, -1);
				return true;

			case Input.Keys.S:
				scroll.add(0, 1);
				return true;

			default:
				return false;
		}
	}
}
