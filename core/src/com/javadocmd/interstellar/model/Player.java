package com.javadocmd.interstellar.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.google.common.collect.Sets;
import com.javadocmd.interstellar.model.ai.BasicAiProgram;
import com.javadocmd.interstellar.model.component.Ai;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.component.HudEnabled;
import com.javadocmd.interstellar.model.component.PlayerComponent;
import com.javadocmd.interstellar.model.component.Worker;
import com.javadocmd.interstellar.model.handle.ResourcesHandle;
import com.javadocmd.interstellar.model.work.Job;
import com.javadocmd.interstellar.model.work.WorkQueue;

public class Player extends Entity implements ResourcesHandle {

	private Engine engine = null;

	private String name;
	private boolean isUser;
	private Resources resources;
	private Set<WorkQueue> queues;

	public Player(String name, boolean isUser) {
		this.name = name;
		this.isUser = isUser;
		
		this.add(new PlayerComponent(this));
		if (isUser)
			this.add(new HudEnabled());
		else
			this.add(new Ai(this, new BasicAiProgram()));

		resources = new Resources(4, 2, 0);
		// resources = new Resources(0, 0, 0);
		//resources = new Resources(80, 80, 80);

		queues = new LinkedHashSet<WorkQueue>();
		addQueue(Job.Type.ADMIN);
	}

	public void addQueue(Job.Type type) {
		WorkQueue q = new WorkQueue(queues.size(), type);

		if (Components.isHudEnabled(this))
			q.add(new HudEnabled());

		queues.add(q);

		if (engine != null)
			engine.addEntity(q);
	}

	public boolean tryJob(Job job) {
		if (!resources.isAtLeast(job.getCost()))
			return false;

		// Find available worker.
		Worker worker = null;
		for (WorkQueue q : queues) {
			Worker w = Components.WORKER.get(q);
			if (w.isAvailable(job.getType())) {
				worker = w;
				break;
			}
		}

		if (worker == null)
			return false;

		resources = resources.minus(job.getCost());
		worker.start(job);
		return true;
	}

	public boolean isUser() {
		return isUser;
	}

	public Set<WorkQueue> getWorkQueues() {
		return queues;
	}

	public Set<WorkQueue> getAvailableWorkQueues() {
		return Sets.filter(queues, MyPredicates.QUEUE_IS_AVAILABLE);
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	@Override
	public Resources getResources() {
		return resources;
	}

	@Override
	public Resources giveResources(Resources resources) {
		this.resources = this.resources.plus(resources);
		return this.resources;
	}

	@Override
	public String toString() {
		return name;
	}
}
