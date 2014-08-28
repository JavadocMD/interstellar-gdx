package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.work.WorkQueue;

public class WorkQueueComponent extends Component {

	final public WorkQueue workQueue;

	public WorkQueueComponent(WorkQueue workQueue) {
		this.workQueue = workQueue;
	}
}
