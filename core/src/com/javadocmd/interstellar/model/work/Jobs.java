package com.javadocmd.interstellar.model.work;

import static com.javadocmd.interstellar.model.MyPredicates.owned;
import static com.javadocmd.interstellar.model.MyPredicates.ownedCity;

import com.javadocmd.interstellar.model.Connection;
import com.javadocmd.interstellar.model.Connection.Status;
import com.javadocmd.interstellar.model.GameController;
import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;

public class Jobs {

	private static Map map() {
		return GameController.get().getMap();
	}

	public static Resources costBuildConnection(Player player) {
		int connections = map().getConnections(owned(player)).size();
		return new Resources(2 + 2 * connections, 2, 0);
	}

	public static Job buildConnection(final Player player, final Connection conn) {
		Resources cost = costBuildConnection(player);
		return new Job(Job.Type.ADMIN, "Build Connection", cost, 10f) {

			@Override
			public void start() {
				conn.setStatus(Status.BUILDING);
			}

			@Override
			public void finish() {
				conn.setStatus(Status.BUILT);
				conn.setOwner(player);
				tryClaimPlanet(conn.getA());
				tryClaimPlanet(conn.getB());
			}

			private void tryClaimPlanet(Planet p) {
				boolean claimed = p.tryClaim(player);

				// Available/Unavailable is a User-facing status only.
				if (claimed && player.isUser()) {
					for (Connection c : p.getConnections()) {
						if (c.getStatus() == Status.UNAVAILABLE)
							c.setStatus(Status.AVAILABLE);
					}
				}
			}
		};
	}

	public static Resources revenueCollectTaxes(Player player) {
		int cityPlanets = map().getPlanets(ownedCity(player)).size();
		int connections = map().getConnections(owned(player)).size();

		return new Resources(cityPlanets + connections, 0, 0);
	}

	public static Job collectTaxes(final Player player, final Planet planet) {
		final Resources revenue = revenueCollectTaxes(player);
		return new PlanetaryJob(Job.Type.ADMIN, "Collect Taxes", planet,
				Resources.ZERO, 7f) {

			@Override
			public void finish() {
				super.finish();
				player.giveResources(revenue);
			}
		};
	}

	public static Resources costMine(Player player) {
		return new Resources(1, 0, 0);
	}

	public static Job mine(final Player player, final Planet planet) {
		Resources cost = costMine(player);
		return new PlanetaryJob(Job.Type.ADMIN, "Mine", planet, cost, 5f) {

			@Override
			public void finish() {
				super.finish();
				player.giveResources(planet.getMineValue());
			}
		};
	}

	public static Resources costHireAdmin(Player player) {
		return new Resources(4, 4, 1);
	}

	public static Job hireAdmin(final Player player, final Planet planet) {
		Resources cost = costHireAdmin(player);
		return new PlanetaryJob(Job.Type.ADMIN, "Hire Admin", planet, cost, 12f) {

			@Override
			public void finish() {
				super.finish();
				player.addQueue(Job.Type.ADMIN);
			}
		};
	}

	// public static Resources costHireMerc(Player player) {
	// return new Resources(6, 2, 4);
	// }
	//
	// public static Job hireMerc(final Player player, final Planet planet) {
	// Resources cost = costHireMerc(player);
	// return new PlanetaryJob(Job.Type.MERC, "Hire Merc", planet, cost, 14f) {
	//
	// @Override
	// public void finish() {
	// super.finish();
	// player.addQueue(Job.Type.MERC);
	// }
	// };
	// }

	public static Resources costClaim(Player player) {
		return new Resources(18, 5, 5);
	}

	public static Job claim(final Player player, final Planet planet) {
		Resources cost = costClaim(player);
		return new PlanetaryJob(Job.Type.ADMIN,
				"Claim Trade Council Leadership (Victory)", planet, cost, 5f) {

			@Override
			public void finish() {
				super.finish();
				GameController.get().win(player);
			}
		};
	}
}
