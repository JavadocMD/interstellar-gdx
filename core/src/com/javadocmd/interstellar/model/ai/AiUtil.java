package com.javadocmd.interstellar.model.ai;

import static com.javadocmd.interstellar.model.MyPredicates.PLANET_NON_BUSY;
import static com.javadocmd.interstellar.model.MyPredicates.TYPE_IS_CITY;
import static com.javadocmd.interstellar.model.MyPredicates.TYPE_IS_GAS;
import static com.javadocmd.interstellar.model.MyPredicates.TYPE_IS_METAL;
import static com.javadocmd.interstellar.model.MyPredicates.owned;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.Planet;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.work.Jobs;

public class AiUtil {

	public static Resources calcCostDeficit(Player player, Map map,
			Resources cost) {
		if (player.getResources().isAtLeast(cost))
			return Resources.ZERO;

		Resources r = player.getResources().minus(cost);
		int c = Math.min(0, r.getCredits());
		int m = Math.min(0, r.getMetals());
		int g = Math.min(0, r.getGas());
		return new Resources(-c, -m, -g);
	}

	public static void workOnDeficit(Player player, Map map, Resources deficit) {
		// Just try everything until something sticks.
		if (deficit.getGas() > 0) {
			Planet p = pickNonBusyPlanet(player, map, TYPE_IS_GAS);
			if (p != null)
				player.tryJob(Jobs.mine(player, p));
		}
		if (deficit.getMetals() > 0) {
			Planet p = pickNonBusyPlanet(player, map, TYPE_IS_METAL);
			if (p != null)
				player.tryJob(Jobs.mine(player, p));
		}
		if (deficit.getCredits() > 0) {
			tryCollectTaxes(player, map);
		}
	}
	
	public static void tryCollectTaxes(Player player, Map map) {
		Planet p = pickNonBusyPlanet(player, map, TYPE_IS_CITY);
		if (p != null)
			player.tryJob(Jobs.collectTaxes(player, p));
	}

	public static Planet pickNonBusyPlanet(Player player, Map map,
			Predicate<Planet> typePredicate) {
		@SuppressWarnings("unchecked")
		Predicate<? super Planet> pred = Predicates.and(typePredicate,
				owned(player), PLANET_NON_BUSY);
		Set<Planet> planets = map.getPlanets(pred);
		if (planets.size() > 0)
			return planets.iterator().next();
		else
			return null;
	}
}
