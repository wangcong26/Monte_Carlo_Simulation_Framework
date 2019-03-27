package payout;

import path.DataPoint;
import path.Path;

public class AsianCallOption implements Payout
{
	private Path oneStockPath = new Path();
	private double r;
	private double q;
	private double sigma;
	private double S0;
	private double T;
	private double strike;
	private double payoffAsianCall;

	// Constructor
	public AsianCallOption()
	{
		
	}
	
	
	// Constructor to read in all the parameters
	public AsianCallOption(double r, double q, double sigma, double S0, double T, double strike)
	{
		this.r = r;
		this.q = q;
		this.sigma = sigma;
		this.S0 = S0;
		this.T = T;
		this.strike = strike;
	}

	// #1 Compute the payoff of an Asian option
	@Override
	public double payout(Path path)
	{
		double sum = 0;
		double average = 0;
		
		// Compute the average price
		for (DataPoint datapoint : path.getData())
		{
			sum = sum + datapoint.price();
		}
		average = sum / path.getData().size();

		// If average greater than strike, it has a value
		if (average - strike > 0)
		{
			payoffAsianCall = average - strike;
			return payoffAsianCall;
		} else
		{
			return 0;
		}
	}

	// Getter to get r
	public double getR()
	{
		return r;
	}

	// Setter to set r
	public void setR(double r)
	{
		this.r = r;
	}

	// Getter to get q
	public double getQ()
	{
		return q;
	}

	// Setter to set q
	public void setQ(double q)
	{
		this.q = q;
	}

	// Getter to get sigma
	public double getSigma()
	{
		return sigma;
	}

	// Setter to set sigma
	public void setSigma(double sigma)
	{
		this.sigma = sigma;
	}

	// Getter to get price
	public double getS0()
	{
		return S0;
	}

	// Setter to set price
	public void setS0(double s0)
	{
		S0 = s0;
	}

	// Getter to get T
	public double getT()
	{
		return T;
	}

	// Setter to set T
	public void setT(double t)
	{
		T = t;
	}

	// Getter to get Strike
	public double getStrike()
	{
		return strike;
	}

	// Setter to set strike
	public void setStrike(double strike)
	{
		this.strike = strike;
	}
}
