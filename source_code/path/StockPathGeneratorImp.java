package path;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import random.RandomNumberGenerator;
import random.NormalRandomNumberGenerator;

// This is the implementation of the path generator
public class StockPathGeneratorImp implements StockPathGenerator
{
	private RandomNumberGenerator randomGenerator = new NormalRandomNumberGenerator();

	private DataPoint calculateNextDataPoint(DataPoint currentPoint, double volatility, double dailyReturn)
	{
		DataPoint nextPoint = new DataPoint();
		double nextPrice = 0;
		double t = 1.0; // Note: delta T here is to use 1 rather than 1/252.

		// 1. Compute the stock price in the next day
		nextPrice = currentPoint.price() * Math.exp((dailyReturn - 0.5 * Math.pow(volatility, 2)) * t
				+ volatility * Math.sqrt(t) * randomGenerator.nextRandom());
		// System.out.println(randomGenerator.nextRandom());

		// 2. Compute the next date
		LocalDate nextDate = currentPoint.date().plusDays(1);

		// 3. Set nextPoint by using the current Price and Date+1
		nextPoint.date(nextDate);
		nextPoint.price(nextPrice);

		return nextPoint;
	}

	@Override
	public Path nextPath(DataPoint startPoint, double volatility, double dailyReturn, double periodDays)
	{
		Path path = new Path();
		
		// Add the startPoint to the path first
		path.addDataPoint(startPoint);
		
		// Add all the datapoints to the path
		DataPoint prevPoint = startPoint;
		for (int i = 0; i < periodDays; i++)
		{
			// Here is to compute the next day DataPoint and add to path
			DataPoint currPoint = calculateNextDataPoint(prevPoint, volatility, dailyReturn);
			path.addDataPoint(currPoint);
			prevPoint = currPoint;
		}
		return path;
	}
}
