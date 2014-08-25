package com.javadocmd.interstellar;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.javadocmd.interstellar.input.MapScroll;
import com.javadocmd.interstellar.input.QuitButton;
import com.javadocmd.interstellar.model.Connection;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.MapBuilder;
import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.work.WorkQueue;
import com.javadocmd.interstellar.system.AiSystem;
import com.javadocmd.interstellar.system.WorkSystem;
import com.javadocmd.interstellar.ui.Art;
import com.javadocmd.interstellar.ui.FieldController;
import com.javadocmd.interstellar.ui.HudController;

public class InterstellarGame extends ApplicationAdapter {

	private Stage fieldStage;
	private Stage hudStage;

	// private ShapeRenderer shapes;
	private MapScroll mapScroll;

	private Engine engine;

	@Override
	public void create() {
		fieldStage = new Stage(new ScreenViewport());
		hudStage = new Stage(new ScreenViewport());
		// shapes = new ShapeRenderer();
		engine = new Engine();

		Art.init();

		List<Player> players = new ArrayList<Player>();
		players.add(new Player("User", true));
		players.add(new Player("AI", false));

		MapBuilder mb = new MapBuilder(players);
		mb.readFile(Gdx.files.internal("map.txt"));
		Map map = mb.build();

		GameController game = new GameController(players, map);
		GameController.init(game);

		engine.addSystem(new WorkSystem());
		engine.addSystem(new AiSystem());
		// engine.addSystem(new RevenueSystem());
		engine.addEntityListener(HudController.init(engine, hudStage));
		engine.addEntityListener(new FieldController(fieldStage));

		for (Connection c : map.getConnections())
			engine.addEntity(c);
		for (Planet p : map.getPlanets())
			engine.addEntity(p);
		for (Player p : players) {
			engine.addEntity(p);
			for (WorkQueue q : p.getWorkQueues())
				engine.addEntity(q);
		}

		mapScroll = new MapScroll(fieldStage.getCamera());
		Gdx.input.setInputProcessor(new InputMultiplexer(mapScroll,
				new QuitButton(), fieldStage));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// shapes.setProjectionMatrix(fieldStage.getCamera().combined);
		// shapes.begin(ShapeType.Line);
		// shapes.setColor(Color.GRAY);
		// shapes.line(200, 0, 200, 768);
		// shapes.line(0, 200, 1024, 200);
		// shapes.end();

		float delta = Gdx.graphics.getDeltaTime();
		mapScroll.update(delta);

		engine.update(delta);

		fieldStage.act();
		fieldStage.draw();

		hudStage.act();
		hudStage.draw();
	}

	@Override
	public void dispose() {
		fieldStage.dispose();
		Art.dispose();
	}
}
