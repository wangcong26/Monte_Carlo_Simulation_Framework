package mock;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import random.NormalRandomNumberGenerator;
import random.RandomNumberGenerator;
import random.UniformRandomGenerator;
import stats.StatsCollector;

public class StatsCollectorTest
{
	@Mock
	StatsCollector collector = new StatsCollector();

	@Before
	public void create()
	{
		collector = mock(StatsCollector.class);
		collector.addPayoff(1.5);
		collector.getList().size();
	}
	
	@Test
	public void testRandom() throws IOException
	{
		// Now the size of the list is 0
		then(collector.getList().size()).isEqualTo(0);
		
		// Make sure addPayoff works
		verify(collector).addPayoff(eq(1.5));
		
		// Add only once
		verify(collector, times(1)).addPayoff(1.5);
		
		// Test the two method
		when(collector.computeMeanPayoff()).thenReturn((double) 3);
		
		when(collector.computeStdPayoff()).thenReturn((double) 100);
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
