package mock;

import java.time.LocalDate;
import org.junit.Test;
import path.DataPoint;
import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
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

public class DataPointTest
{
	DataPoint dataPoint;

	@Before
	public void createDataPoint()
	{
		// Create DataPoint mock Object
		dataPoint = mock(DataPoint.class);
		when(dataPoint.price(100)).thenReturn(dataPoint);
		when(dataPoint.date(LocalDate.of(2019, 1, 1))).thenReturn(dataPoint);
	}

	@Test
	public void test()
	{
		// Test initialization of a dataPoint
		dataPoint.price(100);
		dataPoint.date(LocalDate.of(2019, 1, 1));
		
		// Make sure they are equal
		assertEquals(dataPoint, dataPoint.price(100));
		assertEquals(dataPoint, dataPoint.date(LocalDate.of(2019, 1, 1)));
		
		//Make sure this works
		when(dataPoint.price()).thenReturn((double) 100);

		// Junit test way to test and printout result
		DataPoint startPoint = new DataPoint();
		startPoint.date(LocalDate.of(2019, 1, 1));
		startPoint.price(153.35);
		System.out.println(startPoint);
	}

}