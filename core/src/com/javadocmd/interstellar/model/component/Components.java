package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class Components {

	public static final ComponentMapper<Worker> WORKER = ComponentMapper
			.getFor(Worker.class);
	public static final ComponentMapper<Owner> OWNER = ComponentMapper
			.getFor(Owner.class);
	public static final ComponentMapper<Revenue> REVENUE = ComponentMapper
			.getFor(Revenue.class);
	public static final ComponentMapper<HudEnabled> HUD = ComponentMapper
			.getFor(HudEnabled.class);
	public static final ComponentMapper<Ai> AI = ComponentMapper
			.getFor(Ai.class);

	public static boolean isHudEnabled(Entity entity) {
		return (Components.HUD.get(entity) != null);
	}
}
