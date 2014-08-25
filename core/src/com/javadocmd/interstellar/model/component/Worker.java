package com.javadocmd.interstellar.model.component;

import com.badlogic.ashley.core.Component;
import com.javadocmd.interstellar.model.handle.ProgressHandle;
import com.javadocmd.interstellar.model.work.Job;

public class Worker extends Component implements ProgressHandle {

	private Job.Type type;
	private Job job = null;

	public Worker(Job.Type type) {
		this.type = type;
	}

	public boolean isAvailable(Job.Type type) {
		return (this.type == type && job == null);
	}

	public void start(Job job) {
		this.job = job;
		this.job.start();
	}

	public void update(float delta) {
		if (job == null)
			return;

		job.update(delta);
		if (job.isDone()) {
			job.finish();
			this.job = null;
		}
	}

	@Override
	public String getLabel() {
		return (job == null) ? "" : job.getName();
	}

	@Override
	public float getProgress() {
		return (job == null) ? 0f : job.getWorked() / job.getDuration();
	}
}
