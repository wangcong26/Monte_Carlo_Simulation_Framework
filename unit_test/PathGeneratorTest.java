package mock;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;
import path.DataPoint;
import path.Path;
import path.StockPathGenerator;
import path.StockPathGeneratorImp;
import payout.AsianCallOption;
import payout.EuroCallOption;
import payout.Payout;
import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;
import stats.StatsCollector;

public class PathGeneratorTest
{
	@Test
	// To test path generator
	public void testGenerateStockPath()
	{
		// Initialize path generator
		StockPathGenerator generator = new StockPathGeneratorImp();
		
		// This is my starting point. DataPoint is already tested in a separate test.
		DataPoint startPoint = new DataPoint();
		startPoint.date(LocalDate.of(2019, 1, 1));
		startPoint.price(152.35);
		
		// Initialize one path
		Path path = generator.nextPath(startPoint, 0.01, 0.0001, 252);

		for (DataPoint point : path.getData())
		{
			System.out.println(point);
		}
		
		// Test the size of datapoint in the path. It should have 253 because I include the starting date.
		assertEquals(253, path.getData().size());
		
		// Just to print out the data points.
		System.out.println("There are "+path.getData().size()+" data points");

		// Enter CallOption parameter (double r, double q, double sigma, double S0, double T, double strike)
		Payout euroCall = new EuroCallOption(0.0001, 0, 0.01, 152.35, 252, 165);
		euroCall.payout(path);
		System.out.println("This path generate Euro call payoff: " + euroCall.payout(path));
		
		// Enter AsianOption parameter (double r, double q, double sigma, double S0, double T, double strike)
		Payout asianCall = new AsianCallOption(0.0001, 0, 0.01, 152.35, 252, 164);
		asianCall.payout(path);
		System.out.println("This path generate Asian call payoff: " + asianCall.payout(path));
	}
}

