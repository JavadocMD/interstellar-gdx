package com.javadocmd.interstellar.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.ConnectionComponent;
import com.javadocmd.interstellar.model.component.PlanetComponent;

public class FieldController implements EntityListener {

	private Stage stage;

	public FieldController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void entityAdded(Entity entity) {

		PlanetComponent planetComp = Components.PLANET.get(entity);
		ConnectionComponent connectionComp = Components.CONNECTION.get(entity);

		if (connectionComp != null) {
			ConnectionActor a = new ConnectionActor(connectionComp.connection);
			stage.addActor(a);

		} else if (planetComp != null) {
			PlanetActor a = new PlanetActor(planetComp.planet);
			stage.addActor(a);
		}
	}

	@Override
	public void entityRemoved(Entity entity) {

	}
}
