package mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import engine.Engine;
import path.DataPoint;
import payout.AsianCallOption;
import payout.EuroCallOption;
import random.NormalRandomNumberGenerator;
import random.RandomNumberGenerator;

public class EngineTest
{
	
	@Test
	// Normal Unit test way by creating real object and see the result
	public void testEngine()
	{
		DataPoint startPoint = new DataPoint();
		startPoint.date(LocalDate.of(2019, 1, 1));
		startPoint.price(152.35);

		// CallOption(double r, double q, double sigma, double S0, double T, double strike)
		EuroCallOption euroC = new EuroCallOption(0.0001, 0, 0.01, 152.35, 252, 165);
		AsianCallOption asianC = new AsianCallOption(0.0001, 0, 0.01, 152.35, 252, 164);

		// Enter startPoint, euroC and asianC
		Engine testEngine = new Engine(startPoint, euroC, asianC);
		
		//#1 Return the final number of simulation Euro
		int NEuro = testEngine.warmupEuroCall(10000);
		System.out.println("Number of simulation for Euro Call is: " + NEuro);
		// Final euro payoff
		double euroCpayoff = testEngine.finalAverageEuroCall(NEuro);
		System.out.println("The final average payoff for this Euro Call option is: " + euroCpayoff);
		System.out.println();
		
		//#2 Return the final number of simulation Asian
		int NAsian = testEngine.warmupAsianCall(10000);
		System.out.println("Number of simulation for Asian Call is: " + NAsian);
		// Final asian payoff
		double asianCpayoff = testEngine.finalAverageAsianCall(NAsian);
		System.out.println("The final average payoff for this Asian Call option is: " + asianCpayoff);
	}

}
