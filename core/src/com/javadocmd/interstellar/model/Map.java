package com.javadocmd.interstellar.model;

import static com.javadocmd.interstellar.model.MyPredicates.owned;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public class Map {

	private Set<Planet> planets;
	private Set<Connection> connections;

	public Map(Collection<Planet> planets, Collection<Connection> connections) {
		this.planets = new LinkedHashSet<Planet>(planets);
		this.connections = new LinkedHashSet<Connection>(connections);
	}

	public Set<Planet> getPlanets() {
		return planets;
	}

	public Set<Planet> getPlanets(Predicate<? super Planet> predicate) {
		return Sets.filter(planets, predicate);
	}

	public Set<Connection> getConnections() {
		return connections;
	}

	public Set<Connection> getConnections(
			Predicate<? super Connection> predicate) {
		return Sets.filter(connections, predicate);
	}

	public Set<Connection> getAvailableConnections(Player player) {
		Set<Planet> ownedPlanets = getPlanets(owned(player));

		Set<Connection> availableConnections = new HashSet<Connection>();
		for (Planet p : ownedPlanets) {
			for (Connection c : p.getConnections()) {
				if (c.getOwner() == null
						&& c.getStatus() != Connection.Status.BUILDING)
					availableConnections.add(c);
			}
		}

		return availableConnections;
	}

	public Set<Connection> getAvailableConnectionsToPlanet(Player player,
			Predicate<? super Planet> planetPredicate) {
		Set<Connection> matchingConnections = new HashSet<Connection>();
		for (Connection c : getAvailableConnections(player)) {
			// Pick un-owned planet, then match against it.
			Planet p = null;
			if (c.getA().getOwner() == null)
				p = c.getA();
			else if (c.getB().getOwner() == null)
				p = c.getB();
			else
				continue;
			if (planetPredicate.apply(p))
				matchingConnections.add(c);
		}
		return matchingConnections;
	}
}
