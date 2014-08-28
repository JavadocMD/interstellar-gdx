package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.Connection;

public class ConnectionComponent extends Component {

	final public Connection connection;
	
	public ConnectionComponent(Connection connection) {
		this.connection = connection;
	}
}
