package com.javadocmd.interstellar.model;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.Owner;
import com.javadocmd.interstellar.model.component.Revenue;
import com.javadocmd.interstellar.model.handle.OwnerHandle;

public class Connection extends Entity implements OwnerHandle {

	public static enum Status {
		UNAVAILABLE, AVAILABLE, BUILDING, BUILT;
	}

	private Planet a;
	private Planet b;
	private Status status;

	public Connection(Planet a, Planet b, Resources value) {
		this.a = a;
		this.b = b;
		this.status = Status.UNAVAILABLE;

		this.add(new Revenue(value));
		this.add(new Owner(null));
	}

	public Planet getA() {
		return a;
	}

	public Planet getB() {
		return b;
	}

	public Vector2 getAtoB() {
		return b.getPosition().sub(a.getPosition());
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

	@Override
	public String toString() {
		return String.format("Connection(%s,%s)", a, b);
	}
}
