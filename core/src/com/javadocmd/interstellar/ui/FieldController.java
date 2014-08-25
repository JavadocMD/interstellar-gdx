package com.javadocmd.interstellar.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.javadocmd.interstellar.model.Connection;
import com.javadocmd.interstellar.model.Planet;

public class FieldController implements EntityListener {

	private Stage stage;

	public FieldController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void entityAdded(Entity entity) {
		if (entity instanceof Connection) {
			Connection c = (Connection) entity;
			ConnectionActor a = new ConnectionActor(c);
			stage.addActor(a);

		} else if (entity instanceof Planet) {
			Planet p = (Planet) entity;
			PlanetActor a = new PlanetActor(p);
			stage.addActor(a);
		}
	}

	@Override
	public void entityRemoved(Entity entity) {
		
	}
}
