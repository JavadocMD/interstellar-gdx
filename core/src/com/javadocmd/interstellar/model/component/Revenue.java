package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Resources;

public class Revenue extends Component {

	private Resources value;

	public Revenue(int credits, int metals, int crystals) {
		this.value = new Resources(credits, metals, crystals);
	}
	
	public Revenue(Resources value) {
		this.value = value;
	}

	public Resources getValue() {
		return value;
	}
}
