package com.javadocmd.interstellar.model.ai;

public class BasicAiProgram implements AiProgram {

	public int current = 0;
	public Goal[] goals = new Goal[] { //
			new OwnMetal(), //
			new OwnGas(), //
			new HaveWorkQueues(2), //
			new HaveConnections(4), //
			new Claim() //
	};

	@Override
	public Goal currentGoal() {
		return goals[current];
	}

	@Override
	public void goalAccomplished() {
		current += 1;
	}
}
