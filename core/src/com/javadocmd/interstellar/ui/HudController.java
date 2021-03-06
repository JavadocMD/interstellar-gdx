package com.javadocmd.interstellar.ui;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.javadocmd.interstellar.InterstellarGame;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.PlayerComponent;
import com.javadocmd.interstellar.model.component.WorkQueueComponent;
import com.javadocmd.interstellar.model.work.WorkQueue;

public class HudController implements EntityListener {

	private static HudController INSTANCE = null;

	public static HudController init(InterstellarGame game, Stage stage) {
		HudController hudCont = new HudController(game, stage);
		INSTANCE = hudCont;
		return hudCont;
	}

	public static HudController get() {
		return INSTANCE;
	}

	private InterstellarGame game;
	private Stage stage;
	private Map<Entity, Actor> entityActors;

	private ToolTip toolTip;

	private float viewportWidth;
	private boolean frozen = false;

	// private float viewportHeight;

	private HudController(InterstellarGame game, Stage stage) {
		this.game = game;
		this.stage = stage;
		this.entityActors = new HashMap<Entity, Actor>();
		this.viewportWidth = stage.getViewport().getScreenWidth();
		// this.viewportHeight = stage.getViewport().getScreenHeight();

		this.toolTip = new ToolTip(viewportWidth / 2, 60);
		stage.addActor(toolTip);
	}

	private void add(WorkQueue workQueue) {
		QueueBar bar = new QueueBar(Components.WORKER.get(workQueue), 10,
				10 + 30 * workQueue.getWorkQueueIndex());
		entityActors.put(workQueue, bar);
		stage.addActor(bar);
	}

	private void add(Player player) {
		ResourcesBar resBar = new ResourcesBar(player, viewportWidth / 2, 10);
		entityActors.put(player, resBar);
		stage.addActor(resBar);
	}

	public void setToolTip(String text) {
		if (!frozen)
			toolTip.setText(text);
	}

	@Override
	public void entityAdded(Entity entity) {
		if (!Components.isHudEnabled(entity))
			return;

		PlayerComponent playerComp = Components.PLAYER.get(entity);
		WorkQueueComponent workQueueComp = Components.WORK_QUEUE.get(entity);

		if (workQueueComp != null) {
			add(workQueueComp.workQueue);

		} else if (playerComp != null) {
			add(playerComp.player);
		}
	}

	@Override
	public void entityRemoved(Entity entity) {
		// Actor a = entityActors.get(entity);
		// if (a != null)
		// a.remove();
	}

	public void freezeGame(String message) {
		frozen = true;
		toolTip.setText(message);
		Gdx.input.setInputProcessor(null);
		game.logicFreeze();
	}
}
