package stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import path.DataPoint;
import path.Path;
import path.StockPathGenerator;
import path.StockPathGeneratorImp;

public class StatsCollector
{
	List<Double> payOffList = new ArrayList<Double>();
	double sumPayoff = 0.0;
	double averagePayoff = 0.0;
	double stdPayoff = 0.0;

	// Add payoff to the list
	public void addPayoff(double payoff)
	{
		payOffList.add(payoff);
	}

	// Compute average payoff
	public double computeMeanPayoff()
	{
		double sum = 0.0;
		for (double d : payOffList)
		{
			sum = sum + d;
		}
		averagePayoff = sum / payOffList.size();
		return averagePayoff;
	}

	// Compute std of payoff
	public double computeStdPayoff()
	{
		double sum = 0.0;
		double average = this.averagePayoff;
		for (double d : payOffList)
		{
			sum = sum + Math.pow((d - average), 2);
		}
		stdPayoff = Math.sqrt(sum / payOffList.size());
		return stdPayoff;
	}

	// Get average Payoff
	public double getAveragePayoff()
	{
		return averagePayoff;
	}
	
	// Get std Payoff
	public double getStdPayoff()
	{
		return stdPayoff;
	}
	
	// Get max of Payoff
	public double getMax()
	{
		return Collections.max(payOffList);
	}
	
	// Get min of Payoff
	public double getMin()
	{
		return Collections.min(payOffList);
	}
	
	// Get average Payoff
	public List<Double> getList()
	{
		return payOffList;
	}

}
