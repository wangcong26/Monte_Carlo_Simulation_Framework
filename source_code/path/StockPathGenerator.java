package path;

// Made an interface for stock path generator
public interface StockPathGenerator
{
	Path nextPath(DataPoint startPoint, double volatility, double dailyReturn, double periodDays);
}
