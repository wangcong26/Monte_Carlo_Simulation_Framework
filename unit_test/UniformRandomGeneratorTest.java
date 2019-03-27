package mock;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;
import random.UniformRandomGenerator;
import stats.StatsCollector;

public class UniformRandomGeneratorTest
{
	
	@Mock
	// Mock a uniform generator
	UniformRandomGenerator uniGenerator = new UniformRandomGenerator(System.currentTimeMillis(), Integer.MAX_VALUE);

	@Before
	// Mock an object
	public void create()
	{
		uniGenerator = mock(UniformRandomGenerator.class);
	}
	
	@Test
	public void testRandom()
	{
		// The uniform generator generates a number between 0 and 1. Possible to be 0 but less than 1
		then(uniGenerator.nextRandom()).isLessThan(1);
		then(uniGenerator.nextRandom()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	// Normal Unit test way by creating real object and see the result
	public void testUniformGenerator()
	{
		StatsCollector collector = new StatsCollector();
		UniformRandomGenerator generator = new UniformRandomGenerator(System.currentTimeMillis(), Integer.MAX_VALUE);
		for (int i = 0; i < 10000; i++)
		{
			System.out.println(generator.nextRandom());
			collector.addPayoff(generator.nextRandom());
		}
		
		// compute mean and std.
		collector.computeMeanPayoff();
		collector.computeStdPayoff();
		
		// Print out result of stats.
		System.out.println("Max is: " + collector.getMax());
		System.out.println("Mean is: " + collector.getAveragePayoff());
		System.out.println("Std is: " + collector.getStdPayoff());
		System.out.println("Min is: " + collector.getMin());
	}
}


