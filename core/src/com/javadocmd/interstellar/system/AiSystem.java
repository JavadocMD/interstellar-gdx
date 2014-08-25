package com.javadocmd.interstellar.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.ai.AiProgram;
import com.javadocmd.interstellar.model.ai.AiProgram.Goal;
import com.javadocmd.interstellar.model.ai.AiUtil;
import com.javadocmd.interstellar.model.component.Ai;
import com.javadocmd.interstellar.model.component.Components;

public class AiSystem extends EntitySystem {

	@SuppressWarnings("unchecked")
	private static final Family FAMILY = Family.getFor(Ai.class);

	private ImmutableArray<Entity> ais;

	final private float period = 0.5f;
	private float elapsed;

	@Override
	public void addedToEngine(Engine engine) {
		this.ais = engine.getEntitiesFor(FAMILY);
	}

	@Override
	public void update(float delta) {
		elapsed += delta * GameController.get().getTimeScale();
		while (elapsed > period) {
			elapsed -= period;

			for (int i = 0; i < ais.size(); i++) {
				Entity e = ais.get(i);
				execute(Components.AI.get(e));
			}
		}
	}

	private void execute(Ai ai) {
		Map map = GameController.get().getMap();
		Player player = ai.player;
		AiProgram program = ai.program;

		// No use trying anything while all our queues are busy.
		if (player.getAvailableWorkQueues().size() == 0)
			return;

		// Check goals for completion.
		Goal goal = program.currentGoal();
		while (goal.isAccomplished(player, map)) {
			// Gdx.app.log("AiSystem", "Accomplished " +
			// goal.getClass().getSimpleName());
			program.goalAccomplished();
			goal = program.currentGoal();
		}

		// Gdx.app.log("AiSystem", "Goal is now " +
		// goal.getClass().getSimpleName());
		// Gdx.app.log("AiSystem", "AI has " +
		// player.getResources().toString());

		// Act on next goal.
		goal.attempt(player, map);

		// If we have any left over work queues, try to collect taxes.
		if (player.getAvailableWorkQueues().size() > 0) {
			AiUtil.tryCollectTaxes(player, map);
		}
	}
}
