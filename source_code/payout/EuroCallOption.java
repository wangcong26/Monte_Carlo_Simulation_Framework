package payout;

import path.DataPoint;
import path.Path;

public class EuroCallOption implements Payout
{
	private Path oneStockPath = new Path();
	private double r;
	private double q;
	private double sigma;
	private double S0;
	private double T;
	private double strike;
	private double payoffEuroCall;

	// Constructor
	public EuroCallOption()
	{
		
	}
	
	
	// Constructor
	public EuroCallOption(double r, double q, double sigma, double S0, double T, double strike)
	{
		this.r = r;
		this.q = q;
		this.sigma = sigma;
		this.S0 = S0;
		this.T = T;
		this.strike = strike;
	}

	// #1 Compute the payoff of an European option
	@Override
	public double payout(Path path)
	{
		oneStockPath = path;
		double payoff;
		if (path.getData().last().price() - strike > 0)
		{
			payoff = path.getData().last().price() - strike;
			payoffEuroCall = payoff;
			return payoffEuroCall;
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

	// Setter to set Strike
	public void setStrike(double strike)
	{
		this.strike = strike;
	}
	
	@Override
	public String toString()
	{
		return "This path generates an EuroCallOption payoff: " + payoffEuroCall + ", with a strike: " + strike;
	}

}
