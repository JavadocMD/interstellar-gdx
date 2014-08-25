package com.javadocmd.interstellar.model.work;

import com.javadocmd.interstellar.model.Resources;

abstract public class Job {

	public static enum Type {
		ADMIN;
	}
	
	private Type type;
	private String name;
	private Resources cost;
	private float duration;
	private float worked = 0f;
	
	public Job(Type type, String name, Resources cost, float duration) {
		this.type = type;
		this.name = name;
		this.cost = cost;
		this.duration = duration;
	}
	
	public void update(float delta) {
		this.worked += delta;
	}
	
	public boolean isDone() {
		return worked >= duration; 
	}
	
	abstract public void start();
	abstract public void finish();

	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public Resources getCost() {
		return cost;
	}
	
	public float getDuration() {
		return duration;
	}

	public float getWorked() {
		return worked;
	}
}
