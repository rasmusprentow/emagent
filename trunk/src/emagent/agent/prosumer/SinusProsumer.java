package emagent.agent.prosumer;

import emagent.agent.I2IFunction;

public class SinusProsumer extends VariableProsumer {


	public SinusProsumer(final int mean, final int origo, final int minimum) {
		this(mean,origo,minimum,24);
	}
	public SinusProsumer(final int mean, final int origo, final int minimum, final int frequency) {
		super(new I2IFunction() {

			@Override
			public long map(long arg) {
				double result = (-Math.cos((arg+origo)*Math.PI/(frequency/2))*(mean-minimum))+mean;
				return (long)result;
			}
		});
	}

}
