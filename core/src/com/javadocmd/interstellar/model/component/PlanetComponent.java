package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Planet;

public class PlanetComponent extends Component {

	final public Planet planet;

	public PlanetComponent(Planet planet) {
		this.planet = planet;
	}
}
