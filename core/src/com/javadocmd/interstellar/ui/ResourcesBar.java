package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.handle.ResourcesHandle;

public class ResourcesBar extends Group {

	private ResourcesHandle handle;

	private Label credits;
	private Label metals;
	private Label crystals;

	public ResourcesBar(ResourcesHandle handle, float centerX, float y) {
		setSize(320, 70);
		setPosition(centerX - getWidth() / 2, y - 10);

		credits = new Label("0", Art.labelStyle);
		credits.setPosition(50, 20);
		credits.setSize(46, 30);
		credits.setAlignment(Align.center);

		metals = new Label("0", Art.labelStyle);
		metals.setPosition(146, 20);
		metals.setSize(46, 30);
		metals.setAlignment(Align.center);

		crystals = new Label("0", Art.labelStyle);
		crystals.setPosition(242, 20);
		crystals.setSize(46, 30);
		crystals.setAlignment(Align.center);

		this.addActor(credits);
		this.addActor(metals);
		this.addActor(crystals);

		this.handle = handle;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		update(handle.getResources());
	}

	public void update(Resources resources) {
		credits.setText(Integer.toString(resources.getCredits()));
		metals.setText(Integer.toString(resources.getMetals()));
		crystals.setText(Integer.toString(resources.getGas()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// batch.setColor(Color.DARK_GRAY);
		// batch.draw(Art.pixture, getX(), getY(), getWidth(), getHeight());
		//
		// batch.setColor(Color.LIGHT_GRAY);
		// batch.draw(Art.pixture, getX() + 10, getY() + 10, 30, 30);
		// batch.draw(Art.pixture, getX() + 106, getY() + 10, 30, 30);
		// batch.draw(Art.pixture, getX() + 202, getY() + 10, 30, 30);

		batch.setColor(Color.WHITE);
		batch.draw(Art.resources, getX(), getY(), getWidth(), getHeight());
		super.draw(batch, parentAlpha);
	}
}
