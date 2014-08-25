package com.javadocmd.interstellar.model.ai;

import static com.javadocmd.interstellar.model.MyPredicates.owned;

import java.util.Set;

import com.javadocmd.interstellar.model.Connection;
import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.Player;
import com.javadocmd.interstellar.model.Resources;
import com.javadocmd.interstellar.model.ai.AiProgram.Goal;
import com.javadocmd.interstellar.model.work.Jobs;

public class HaveConnections implements Goal {

	final private int count;

	public HaveConnections(int count) {
		this.count = count;
	}

	@Override
	public boolean isAccomplished(Player player, Map map) {
		return map.getConnections(owned(player)).size() >= count;
	}

	@Override
	public void attempt(Player player, Map map) {
		Resources cost = Jobs.costBuildConnection(player);
		Resources deficit = AiUtil.calcCostDeficit(player, map, cost);
		if (deficit == Resources.ZERO) {
			Set<Connection> conns = map.getAvailableConnections(player);
			if (conns.size() > 0) {
				player.tryJob(Jobs.buildConnection(player, conns.iterator()
						.next()));
			} else {
				// Oh no, it's impossible!
			}
		} else {
			AiUtil.workOnDeficit(player, map, deficit);
		}
	}
}