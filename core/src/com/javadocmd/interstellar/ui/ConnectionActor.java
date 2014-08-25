package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.javadocmd.interstellar.model.Connection;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.work.Jobs;

public class ConnectionActor extends Group {

	final private Connection model;

	final private Path path;
	final private Button button;

	public ConnectionActor(final Connection model) {
		Vector2 a = model.getA().getPosition();
		Vector2 b = model.getB().getPosition();
		Vector2 atob = model.getAtoB();
		Vector2 buttonPos = new Vector2().lerp(atob, 0.5f);

		setPosition(a.x, a.y);
		setSize(b.x - a.x, b.y - a.y);
		setTouchable(Touchable.childrenOnly);

		this.model = model;
		path = new Path(atob);
		button = new Button(buttonPos) {

			@Override
			public void clicked() {
				Player user = GameController.get().getUser();
				user.tryJob(Jobs.buildConnection(user, model));
			}

			@Override
			public String getTip() {
				Player user = GameController.get().getUser();
				Resources cost = Jobs.costBuildConnection(user);
				return "Build Connection: " + cost;
			}
			
			@Override
			public Texture getTexture() {
				return Art.buttonBuildConn;
			}
		};

		addActor(path);
		addActor(button);
		updateStatus(model.getStatus(), model.getOwner());
	}

	private Connection.Status lastStatus = null;
	private Player lastOwner = null;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// Debug.drawDebugSquare(batch, this);
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		Connection.Status newStatus = model.getStatus();
		Player newOwner = model.getOwner();
		if (newStatus != lastStatus || newOwner != lastOwner)
			updateStatus(newStatus, newOwner);
	}
	
	private void updateStatus(Connection.Status status, Player owner) {
		switch (status) {
			default:
			case UNAVAILABLE:
				path.setColor(Color.DARK_GRAY);
				button.setVisible(false);
				break;

			case AVAILABLE:
				path.setColor(Color.DARK_GRAY);
				button.setVisible(true);
				break;

			case BUILDING:
				path.setColor(Art.yellow);
				button.setVisible(false);
				break;

			case BUILT:
				if (owner.isUser())
					path.setColor(Art.allyBlue);
				else
					path.setColor(Art.enemyRed);
				button.setVisible(false);
				break;
		}
		lastStatus = status;
		lastOwner = owner;
	}
}
