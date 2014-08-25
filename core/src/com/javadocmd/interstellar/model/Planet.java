package com.javadocmd.interstellar.model;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.Owner;
import com.javadocmd.interstellar.model.component.Revenue;
import com.javadocmd.interstellar.model.handle.OwnerHandle;
import com.javadocmd.interstellar.ui.Art;

public class Planet extends Entity implements OwnerHandle {

	public static enum Status {
		NOT_BUSY, BUSY;
	}

	public static enum Type {
		CITY(null, Color.GRAY, Art.planetCity), //
		METAL(new Resources(0, 1, 0), new Color(229f / 255f, 63f / 255f,
				57f / 255f, 1f), Art.planetMetal), //
		GAS(new Resources(0, 0, 1), new Color(19f / 255f, 191f / 255f,
				48f / 255f, 1f), Art.planetGas);

		private Resources mineValue;
		private Color color;
		private Texture texture;

		private Type(Resources mineValue, Color color, Texture texture) {
			this.mineValue = mineValue;
			this.color = color;
			this.texture = texture;
		}

		public Resources getMineValue() {
			return mineValue;
		}

		public Color getColor() {
			return color;
		}

		public Texture getTexture() {
			return texture;
		}
	}

	private String name;
	private Vector2 position;
	private Set<Connection> connections;
	private Type type;
	private Status status = Status.NOT_BUSY;

	public Planet(String name, Vector2 position, Type type) {
		this.name = name;
		this.position = position;
		this.type = type;
		this.connections = new HashSet<Connection>();
		add(new Owner(null));
		if (type == Type.CITY)
			add(new Revenue(1, 0, 0));
	}

	public Vector2 getPosition() {
		return position.cpy();
	}

	public Set<Connection> getConnections() {
		return connections;
	}

	public Resources getMineValue() {
		return type.getMineValue();
	}

	public Type getType() {
		return type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Player getOwner() {
		return Components.OWNER.get(this).getOwner();
	}

	@Override
	public void setOwner(Player owner) {
		Components.OWNER.get(this).setOwner(owner);
	}

	public boolean tryClaim(Player player) {
		Owner o = Components.OWNER.get(this);
		if (o.getOwner() == null) {
			o.setOwner(player);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Planet(%s)", name);
	}
}
