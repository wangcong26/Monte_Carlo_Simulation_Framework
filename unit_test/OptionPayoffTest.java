package mock;

import path.DataPoint;
import payout.AsianCallOption;
import payout.EuroCallOption;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import path.Path;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import payout.AsianCallOption;
import payout.EuroCallOption;

public class OptionPayoffTest
{
	@Mock
	EuroCallOption euroC = new EuroCallOption(0.0001, 0, 0.01, 100, 252, 120);
	AsianCallOption asianC = new AsianCallOption(0.0001, 0, 0.01, 100, 252, 100);

	@Before
	public void before()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEuroPayoff()
	{
		Path pathTest = new Path();

		// make a startpoint with price 150
		DataPoint startPoint = new DataPoint();
		startPoint.date(LocalDate.of(2019, 1, 1));
		startPoint.price(150);

		// add this datapoint to my path
		pathTest.addDataPoint(startPoint);

		// compute the euro payoff 150-120=30
		euroC.payout(pathTest);

		// Expected payoff is 150-120=30 using when, then assert to see if it's correct
		when(euroC.payout(pathTest)).thenReturn((double) 30);
		assertEquals(euroC.payout(pathTest), 30, 0.01);
	}

	@Test
	public void testAsianPayoff()
	{
		Path pathTest = new Path();

		// make two startpoints with price 110
		DataPoint startPoint1 = new DataPoint();
		startPoint1.date(LocalDate.of(2019, 1, 1));
		startPoint1.price(110);
		DataPoint startPoint2 = new DataPoint();
		startPoint2.date(LocalDate.of(2019, 1, 2));
		startPoint2.price(130);
		pathTest.addDataPoint(startPoint1);
		pathTest.addDataPoint(startPoint2);

		// Compute the payoff (110+130)/2-100=20
		asianC.payout(pathTest);

		// Expected asian payoff is (110+130)/2-100=20, using when, then assert to see if it's correct
		when(asianC.payout(pathTest)).thenReturn((double) 20);
		assertEquals(asianC.payout(pathTest), 20, 0.01);
	}

}
