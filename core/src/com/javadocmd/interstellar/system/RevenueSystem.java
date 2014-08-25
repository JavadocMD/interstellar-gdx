package com.javadocmd.interstellar.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.Owner;
import com.javadocmd.interstellar.model.component.Revenue;

public class RevenueSystem extends EntitySystem {

	@SuppressWarnings("unchecked")
	private static final Family FAMILY = Family.getFor(Revenue.class,
			Owner.class);

	private ImmutableArray<Entity> revenueEntities;

	final private float period = 20f;
	private float elapsed;

	@Override
	public void addedToEngine(Engine engine) {
		this.revenueEntities = engine.getEntitiesFor(FAMILY);
	}

	@Override
	public void update(float delta) {
		elapsed += delta * GameController.get().getTimeScale();
		while (elapsed > period) {
			elapsed -= period;
			execute();
		}
	}

	public void execute() {
		// Give revenue for all built connections.
		int size = revenueEntities.size();
		for (int i = 0; i < size; i++) {
			Entity entity = revenueEntities.get(i);

			Player owner = Components.OWNER.get(entity).getOwner();
			if (owner == null)
				continue;

			Resources revenue = Components.REVENUE.get(entity).getValue();
			owner.giveResources(revenue);
		}
	}
}
