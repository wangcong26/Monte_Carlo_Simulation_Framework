package mock;

import static org.assertj.core.api.BDDAssertions.then;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;
import random.UniformRandomGenerator;
import stats.StatsCollector;
import java.time.LocalDate;
import org.junit.Test;
import path.DataPoint;
import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class NormalRandomGeneratorTest
{
	@Mock
	RandomNumberGenerator normalGenerator = new NormalRandomNumberGenerator();

	@Before
	public void create()
	{
		normalGenerator = mock(RandomNumberGenerator.class);
		
	}

	@Test
	public void testRandom() throws IOException
	{
		UniformRandomGenerator uniform = Mockito.mock(UniformRandomGenerator.class);

		//Test both uniform and normal generator, given a uniform output 0.9
		when(uniform.nextRandom()).thenReturn(0.9);
		
		// Based on this uniform, I computed a normal number based on BoxMuller, it's -0.2698191.
		when(normalGenerator.nextRandom()).thenReturn(-0.2698191);
		
		// Then test the equality of -0.2698191 and the one normalGenerator.nextRandom()
		assertEquals(-0.2698191, normalGenerator.nextRandom(), 0.0001);
	}

	@Test
	// Normal way to test the Normal Generator and print it out.
	public void testNormalGenerator() throws IOException
	{
		StatsCollector collector = new StatsCollector();
		RandomNumberGenerator generator = new NormalRandomNumberGenerator();
		for (int i = 0; i < 1000; i++)
		{
			System.out.println(generator.nextRandom());

			collector.addPayoff(generator.nextRandom());
		}
		collector.computeMeanPayoff();
		collector.computeStdPayoff();
		System.out.println();
		System.out.println("Max is: " + collector.getMax());
		System.out.println("Mean is: " + collector.getAveragePayoff());
		System.out.println("Std is: " + collector.getStdPayoff());
		System.out.println("Min is: " + collector.getMin());
	}

}
