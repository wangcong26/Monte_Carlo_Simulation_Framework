package payout;

import path.Path;

// Interface for the option payoff at expiration date.
public interface Payout
{
	double payout(Path path);
}
