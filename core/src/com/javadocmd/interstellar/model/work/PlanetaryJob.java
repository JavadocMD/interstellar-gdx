package com.javadocmd.interstellar.model.work;

import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Resources;

abstract public class PlanetaryJob extends Job {

	private Planet planet;
	
	public PlanetaryJob(Type type, String name, Planet planet, Resources cost, float duration) {
		super(type, name, cost, duration);
		this.planet = planet;
	}

	@Override
	public void start() {
		planet.setStatus(Planet.Status.BUSY);
	}

	@Override
	public void finish() {
		planet.setStatus(Planet.Status.NOT_BUSY);
	}
}
