package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Planet.Type;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.work.Jobs;

public class PlanetActor extends Group {

	final private Planet model;

	final private PlanetBody body;

	final private Group buttons;
	final private Group allyButtons;
	final private Group enemyButtons;

	public PlanetActor(final Planet model) {
		Vector2 p = model.getPosition();

		setSize(160, 160);
		setCenterPosition(p.x, p.y);
		setTouchable(Touchable.childrenOnly);

		this.model = model;
		this.body = new PlanetBody(80, 80, model.getType());
		addActor(this.body);

		allyButtons = makeSubGroup();
		enemyButtons = makeSubGroup();
		buttons = makeSubGroup();
		buttons.addActor(allyButtons);
		buttons.addActor(enemyButtons);
		setupButtons(model.getType());

		addActor(this.buttons);
	}

	private Group makeSubGroup() {
		Group g = new Group();
		g.setSize(getWidth(), getHeight());
		g.setTouchable(Touchable.childrenOnly);
		g.setVisible(false);
		return g;
	}

	private void setupButtons(Planet.Type type) {
		if (type == Planet.Type.CITY) {
			Button collectTaxes = new Button(new Vector2(80, 140)) {
				
				@Override
				public void clicked() {
					Player user = GameController.get().getUser();
					user.tryJob(Jobs.collectTaxes(user, model));
				}

				@Override
				public String getTip() {
					Player user = GameController.get().getUser();
					Resources revenue = Jobs.revenueCollectTaxes(user);
					return "Collect Taxes: gain " + revenue;
				}
				
				@Override
				public Texture getTexture() {
					return Art.buttonMineCredits;
				}
			};
			allyButtons.addActor(collectTaxes);
			
			Button hireAdmin = new Button(new Vector2(37.6f, 122.4f)) {

				@Override
				public void clicked() {
					Player user = GameController.get().getUser();
					user.tryJob(Jobs.hireAdmin(user, model));
				}

				@Override
				public String getTip() {
					Player user = GameController.get().getUser();
					Resources cost = Jobs.costHireAdmin(user);
					return "Hire Admin: " + cost;
				}
				
				@Override
				public Texture getTexture() {
					return Art.buttonHireAdmin;
				}
			};
			allyButtons.addActor(hireAdmin);
			
			Button claim = new Button(new Vector2(20, 80)) {

				@Override
				public void clicked() {
					Player user = GameController.get().getUser();
					user.tryJob(Jobs.claim(user, model));
				}

				@Override
				public String getTip() {
					Player user = GameController.get().getUser();
					Resources cost = Jobs.costClaim(user);
					return "Claim Trade Council Leadership (Victory): " + cost;
				}
				
				@Override
				public Texture getTexture() {
					return Art.buttonClaim;
				}
			};
			allyButtons.addActor(claim);
			
		} else {
			final Texture tex = (model.getType() == Type.METAL) ? Art.buttonMineMetals : Art.buttonMineGas;
			Button mine = new Button(new Vector2(80, 140)) {

				@Override
				public void clicked() {
					Player user = GameController.get().getUser();
					user.tryJob(Jobs.mine(user, model));
				}

				@Override
				public String getTip() {
					Player user = GameController.get().getUser();
					Resources cost = Jobs.costMine(user);
					return "Mine: " + cost;
				}
				
				@Override
				public Texture getTexture() {
					return tex;
				}
			};
			allyButtons.addActor(mine);
		}
	}

	private Planet.Status lastStatus = null;
	private Player lastOwner = null;

	@Override
	public void act(float delta) {
		super.act(delta);

		Planet.Status newStatus = model.getStatus();
		if (newStatus != lastStatus)
			updateStatus(newStatus);

		Player newOwner = model.getOwner();
		if (newOwner != lastOwner)
			updateOwner(newOwner);
	}

	private void updateStatus(Planet.Status status) {
		switch (status) {
			default:
			case BUSY:
				buttons.setVisible(false);
				break;

			case NOT_BUSY:
				buttons.setVisible(true);
				break;
		}
		lastStatus = status;
	}

	private void updateOwner(Player owner) {
		if (GameController.get().getUser() == owner) {
			// show ally controls
			allyButtons.setVisible(true);
			enemyButtons.setVisible(false);
		} else if (owner != null) {
			// show enemy controls
			allyButtons.setVisible(false);
			enemyButtons.setVisible(true);
		} else {
			// hide all controls
			allyButtons.setVisible(false);
			enemyButtons.setVisible(false);
		}
		lastOwner = owner;
		body.updateOwner(owner);
	}
}
