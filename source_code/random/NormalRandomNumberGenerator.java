package random;

import java.util.Random;

public class NormalRandomNumberGenerator implements RandomNumberGenerator
{
	private UniformRandomGenerator random = new UniformRandomGenerator(System.currentTimeMillis(), Integer.MAX_VALUE);

	// Basic Box Muller tranformation
	// Transforms U(0,1) pseudo-random numbers into N(0,1)
	// Return x[0], x[1], a pair of N(0,1) Independent Standard Normal Distributed
	// random numbers
	public double[] gaussianRandom()
	{
		// UniformRandomGenerator random = new UniformRandomGenerator(0, 100);
		double[] u = new double[] { random.nextRandom(), random.nextRandom() };
		double[] x = new double[] { (double) (Math.sqrt(-2 * Math.log(u[0])) * Math.cos(2 * Math.PI * u[1])),
				(double) (Math.sqrt(-2 * Math.log(u[0])) * Math.sin(2 * Math.PI * u[1])) };
		//System.out.println("u0 is: " + u[0] + "; u1 is: " + u[1]);
		return x;
	}

	// From the interface RandomNumberGenerator.
	// We can use either the cos or sin to return one normal distribution random number.
	// Here we return x[0] which uses cos.
	@Override
	public double nextRandom()
	{
		return gaussianRandom()[0];
	}

}
