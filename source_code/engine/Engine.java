package engine;

import java.time.LocalDate;

import path.DataPoint;
import path.Path;
import path.StockPathGenerator;
import path.StockPathGeneratorImp;
import payout.AsianCallOption;
import payout.EuroCallOption;
import payout.Payout;
import stats.StatsCollector;

public class Engine
{
	DataPoint startPoint = new DataPoint();
	Path stockPath = new Path();
	// Initialize two collectors
	StatsCollector statsCollectorEuro = new StatsCollector();
	StatsCollector statsCollectorAsian = new StatsCollector();
	// Initialize two call options
	EuroCallOption euroCall = new EuroCallOption();
	AsianCallOption asianCall = new AsianCallOption();

	// Constructor
	public Engine()
	{

	}

	// Constructor
	public Engine(DataPoint startPoint, EuroCallOption euroC, AsianCallOption asianC)
	{
		// Set initial values
		this.startPoint = startPoint;
		this.euroCall = euroC;
		this.asianCall = asianC;
	}

	// Warmup EuroCall to return the number of simulation
	public int warmupEuroCall(int pathAmount)
	{
		int N = 0;
		Payout euroCall = this.euroCall;

		// To run 10000 times to get an estimate of the std of payoff
		StockPathGenerator generator = new StockPathGeneratorImp();
		for (int i = 0; i < pathAmount; i++)
		{
			Path path = generator.nextPath(this.startPoint, this.euroCall.getSigma(), this.euroCall.getR(),
					this.euroCall.getT());
			double euroC = euroCall.payout(path);
			
			// Add payoff to statscollector
			this.statsCollectorEuro.addPayoff(euroC);
		}
		
		// Compute mean and std
		this.statsCollectorEuro.computeMeanPayoff();
		this.statsCollectorEuro.computeStdPayoff();
		System.out.println("mean is: " + statsCollectorEuro.getAveragePayoff());
		System.out.println("std is: " + statsCollectorEuro.getStdPayoff());

		// Compute the new N at 96% confidence level
		double z = computeZ(0.96);

		N = (int) Math.pow(z * this.statsCollectorEuro.getStdPayoff() / 0.1, 2);

		return N;
	}
	
	// Warmup AsianCall to return the number of simulation
	public int warmupAsianCall(int pathAmount)
	{
		int N = 0;
		Payout asianCall = this.asianCall;
		
		// To run 10000 times to get an estimate of the std of payoff
		StockPathGenerator generator = new StockPathGeneratorImp();
		for (int i = 0; i < pathAmount; i++)
		{
			Path path = generator.nextPath(this.startPoint, this.asianCall.getSigma(), this.asianCall.getR(),
					this.asianCall.getT());
			double asianC = asianCall.payout(path);
			
			// Add payoff to statscollector
			this.statsCollectorAsian.addPayoff(asianC);
		}
		// Compute mean and std
		this.statsCollectorAsian.computeMeanPayoff();
		this.statsCollectorAsian.computeStdPayoff();
		System.out.println("mean is: " + statsCollectorAsian.getAveragePayoff());
		System.out.println("std is: " + statsCollectorAsian.getStdPayoff());

		// Compute the new N at 96% confidence level

		double z = computeZ(0.96);

		N = (int) Math.pow(z * this.statsCollectorAsian.getStdPayoff() / 0.1, 2);

		return N;
	}

	// Return the final average EuroCall using the N computed from the warmup
	public double finalAverageEuroCall(int pathAmount)
	{
		double finalAverage;
		Payout euroCall = this.euroCall;

		StockPathGenerator generator = new StockPathGeneratorImp();
		
		// Compute the payoff from each path
		for (int i = 0; i < pathAmount; i++)
		{
			Path path = generator.nextPath(this.startPoint, this.euroCall.getSigma(), this.euroCall.getR(),
					this.euroCall.getT());
			double euroC = euroCall.payout(path);
			this.statsCollectorEuro.addPayoff(euroC);
		}
		
		// Compute the mean and std
		this.statsCollectorEuro.computeMeanPayoff();
		this.statsCollectorEuro.computeStdPayoff();
		finalAverage = this.statsCollectorEuro.getAveragePayoff();

		return finalAverage;
	}
	
	// Return the final average AsianCall using the N computed from the warmup
	public double finalAverageAsianCall(int pathAmount)
	{
		double finalAverage;
		Payout asianCall = this.asianCall;

		StockPathGenerator generator = new StockPathGeneratorImp();
		
		// Compute the payoff from each path
		for (int i = 0; i < pathAmount; i++)
		{
			Path path = generator.nextPath(this.startPoint, this.asianCall.getSigma(), this.asianCall.getR(),
					this.asianCall.getT());
			double asianC = asianCall.payout(path);
			this.statsCollectorAsian.addPayoff(asianC);
		}
		
		// Compute the mean and std
		this.statsCollectorAsian.computeMeanPayoff();
		this.statsCollectorAsian.computeStdPayoff();
		finalAverage = this.statsCollectorAsian.getAveragePayoff();

		return finalAverage;
	}

	// Compute the z
	public double computeZ(double confidenceLevel)
	{
		double t = 0;
		double z = 0;

		// Compute t
		t = Math.sqrt(Math.log(1.0 / (Math.pow((1 - confidenceLevel)/2, 2))));

		// Compute z
		z = t - (2.515517 + 0.802853 * t + 0.010328 * t * t)
				/ (1 + 1.432788 * t + 0.189269 * t * t + 0.001308 * t * t * t);

		return z;
	}
}
