package emagent.agent.prosumer;

import emagent.agent.I2IFunction;

public class SinusProsumer extends VariableProsumer {

	public SinusProsumer(final int mean, final int origo, final int minimum) {
		super(new I2IFunction() {

			@Override
			public long map(long arg) {
				double result = (-Math.cos(arg*Math.PI/12.)*(mean-minimum))+mean;
				return (long)result;
			}
		});
	}

}
