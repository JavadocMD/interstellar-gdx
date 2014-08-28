package com.javadocmd.interstellar.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class MapBuilder {

	private List<Player> players;
	private Player user;

	private java.util.Map<String, Planet> planets;
	private List<Connection> connections;

	private Set<String> connected;

	public MapBuilder(List<Player> players) {
		this.players = players;
		this.user = players.get(0);

		planets = new HashMap<String, Planet>();
		connections = new ArrayList<Connection>();

		connected = new HashSet<String>();
	}

	public void readFile(FileHandle file) {
		BufferedReader reader = file.reader(1024);
		try {
			while (reader.ready()) {
				String line = reader.readLine();
				readLine(line);
			}
		} catch (IOException e) {
			Gdx.app.log("MapBuilder", "Could not load map file.", e);
		}
	}

	public void readStringArray(String[] lines) {
		for (String s : lines)
			readLine(s);
	}

	public void readLine(String line) {
		if (line.isEmpty())
			return;

		String[] parts = line.split("\\s");
		if (parts[0].equals("p")) {
			// Planet
			Integer ownerIndex = (parts.length == 6) ? Integer
					.parseInt(parts[5]) : null;
			Player owner = null;
			if (ownerIndex != null) {
				owner = players.get(ownerIndex);
			}

			addPlanet(parts[1], Float.parseFloat(parts[2]),
					Float.parseFloat(parts[3]), Planet.Type.valueOf(parts[4]),
					owner);

		} else if (parts[0].equals("c")) {
			// Connection
			addConnection(parts[1], parts[2]);
		}
	}

	public void addPlanet(String name, float x, float y, Planet.Type type,
			Player owner) {
		Planet p = new Planet(name, new Vector2(x, y), type);
		p.setOwner(owner);
		planets.put(name, p);
	}

	public void addConnection(String a, String b) {
		String hash = connString(a, b);
		String reverseHash = connString(b, a);
		if (connected.contains(hash) || connected.contains(reverseHash))
			throw new IllegalArgumentException("Planets " + a + " and " + b
					+ " are already connected.");

		Planet pa = planets.get(a);
		Planet pb = planets.get(b);
		Connection c = new Connection(pa, pb, new Resources(1, 0, 0));
		connections.add(c);
		connected.add(hash);

		pa.getConnections().add(c);
		pb.getConnections().add(c);
	}

	private String connString(String a, String b) {
		return a + ":" + b;
	}

	public Map build() {
		// Set initial connection availability.
		for (Planet p : planets.values()) {
			if (p.getOwner() == user) {
				for (Connection c : p.getConnections()) {
					c.setStatus(Connection.Status.AVAILABLE);
				}
			}
		}

		return new Map(planets.values(), connections);
	}
}
