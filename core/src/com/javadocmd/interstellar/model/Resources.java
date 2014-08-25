package com.javadocmd.interstellar.model;

public class Resources {

	public static final Resources ZERO = new Resources(0, 0, 0);

	private int credits;
	private int metals;
	private int gas;

	public Resources(int credits, int metals, int gas) {
		this.credits = credits;
		this.metals = metals;
		this.gas = gas;
	}

	public Resources minus(Resources that) {
		return new Resources(this.credits - that.credits, this.metals
				- that.metals, this.gas - that.gas);
	}

	public Resources plus(Resources that) {
		return new Resources(this.credits + that.credits, this.metals
				+ that.metals, this.gas + that.gas);
	}

	public boolean isAtLeast(Resources that) {
		return this.credits >= that.credits && this.metals >= that.metals
				&& this.gas >= that.gas;
	}

	public int getCredits() {
		return credits;
	}

	public int getMetals() {
		return metals;
	}

	public int getGas() {
		return gas;
	}

	@Override
	public String toString() {
		String s = "";
		if (credits > 0)
			s += credits + " cr. ";
		if (metals > 0)
			s += metals + " m. ";
		if (gas > 0)
			s += gas + " g.";
		return s;
	}

}
