package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.javadocmd.interstellar.model.handle.ProgressHandle;

public class QueueBar extends Actor {

	private static Color progressColor = new Color(19f/255f, 191f/255f, 48f/255f, 1f);
	
	private ProgressHandle handle;
	private float progress = 0f;

	public QueueBar(ProgressHandle handle, float x, float y) {
		setPosition(x, y);
		setSize(150, 20);
		this.handle = handle;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		setProgress(handle.getProgress());
	}
	
	public void setProgress(float progress) {
		progress = Math.max(0, progress);
		progress = Math.min(1f, progress);
		this.progress = progress;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Art.allyBlue);
		batch.draw(Art.pixture, getX(), getY(), 20, 20);
		
		batch.setColor(Color.DARK_GRAY);
		batch.draw(Art.pixture, getX() + 25, getY(), getWidth(), getHeight());
		
		if (progress > 0f) {
			batch.setColor(progressColor);
			batch.draw(Art.pixture, getX() + 25, getY(), getWidth() * progress,
					getHeight());
		}
	}
}
