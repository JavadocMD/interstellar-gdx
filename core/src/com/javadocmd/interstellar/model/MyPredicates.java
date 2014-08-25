package com.javadocmd.interstellar.model;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.javadocmd.interstellar.model.Planet.Status;
import com.javadocmd.interstellar.model.Planet.Type;
import com.javadocmd.interstellar.model.component.Components;
import com.javadocmd.interstellar.model.handle.OwnerHandle;
import com.javadocmd.interstellar.model.work.Job;
import com.javadocmd.interstellar.model.work.WorkQueue;

public class MyPredicates {

	public static Predicate<OwnerHandle> owned(final Player owner) {
		return new Predicate<OwnerHandle>() {

			@Override
			public boolean apply(OwnerHandle input) {
				return input.getOwner() == owner;
			}
		};
	};

	public static Predicate<Planet> ownedCity(final Player owner) {
		return Predicates.and(TYPE_IS_CITY, owned(owner));
	}

	public static Predicate<Planet> ownedMetal(final Player owner) {
		return Predicates.and(TYPE_IS_METAL, owned(owner));
	}

	public static Predicate<Planet> ownedGas(final Player owner) {
		return Predicates.and(TYPE_IS_GAS, owned(owner));
	}

	public static Predicate<Planet> TYPE_IS_CITY = new Predicate<Planet>() {

		@Override
		public boolean apply(Planet input) {
			return input.getType() == Type.CITY;
		};
	};

	public static Predicate<Planet> TYPE_IS_METAL = new Predicate<Planet>() {

		@Override
		public boolean apply(Planet input) {
			return input.getType() == Type.METAL;
		};
	};

	public static Predicate<Planet> TYPE_IS_GAS = new Predicate<Planet>() {

		@Override
		public boolean apply(Planet input) {
			return input.getType() == Type.GAS;
		};
	};

	public static final Predicate<WorkQueue> QUEUE_IS_AVAILABLE = new Predicate<WorkQueue>() {

		@Override
		public boolean apply(WorkQueue input) {
			return Components.WORKER.get(input).isAvailable(Job.Type.ADMIN);
		}
	};

	public static final Predicate<Planet> PLANET_NON_BUSY = new Predicate<Planet>() {
		
		@Override
		public boolean apply(Planet input) {
			return input.getStatus() == Status.NOT_BUSY;
		}
	};
}
