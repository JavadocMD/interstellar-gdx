package com.javadocmd.interstellar.model.work;

import com.badlogic.ashley.core.Entity;
import com.javadocmd.interstellar.model.component.Worker;

public class WorkQueue extends Entity {

	private Worker worker;
	private int workQueueIndex;

	public WorkQueue(int workQueueIndex, Job.Type type) {
		this.workQueueIndex = workQueueIndex;
		this.worker = new Worker(type);
		this.add(worker);
	}

	public int getWorkQueueIndex() {
		return workQueueIndex;
	}
}
