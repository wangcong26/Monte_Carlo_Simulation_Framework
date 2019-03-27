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

public class ZValueFormulaTest
{
	@Test
	// Here is to test the formula Professor gave
	public void testCalculateZ()
	{
		double myZ = computeZ(0.96);
		System.out.println("The z is: " + myZ);
		
		// Test myZ and the result 2.054189 I computed from Excel.
		assertEquals(myZ, 2.054189, 0.00001);
	}

	public double computeZ(double confidenceLevel)
	{
		double t = 0;
		double z = 0;

		// Compute t. Here is I used (1-p)/2 because it's a two tail.
		t = Math.sqrt(Math.log(1.0 / (Math.pow((1 - confidenceLevel) / 2, 2))));
		System.out.println("t is: " + t);

		// Compute z
		z = t - (2.515517 + 0.802853 * t + 0.010328 * t * t)
				/ (1 + 1.432788 * t + 0.189269 * t * t + 0.001308 * t * t * t);

		return z;
	}

}
