package com.javadocmd.interstellar.model.ai;

import com.javadocmd.interstellar.model.Map;
import com.javadocmd.interstellar.model.Player;

public interface AiProgram {

	public Goal currentGoal();

	public void goalAccomplished();

	public static interface Goal {

		public boolean isAccomplished(Player player, Map map);

		void attempt(Player player, Map map);
	}
}
