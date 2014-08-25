package com.javadocmd.interstellar.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.Worker;

public class WorkSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	private static final Family FAMILY = Family.getFor(Worker.class);

	public WorkSystem() {
		super(FAMILY);
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		Worker w = Components.WORKER.get(entity);
		w.update(deltaTime * GameController.get().getTimeScale());
	}
}
