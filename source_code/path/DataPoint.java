package path;

import java.time.LocalDate;

public class DataPoint
{
	private LocalDate date;
	private double price;

	public LocalDate date()
	{
		return date;
	}

	public DataPoint date(LocalDate date)
	{
		this.date = date;
		return this;
	}

	public double price()
	{
		return price;
	}

	public DataPoint price(double price)
	{
		this.price = price;
		return this;
	}

	@Override
	public String toString()
	{
		return "Date: " + date + "; " + "Price: " + price;
	}
}
