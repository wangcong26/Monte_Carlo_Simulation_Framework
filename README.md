# Monte_Carlo_Simulation_Framework
Developed a Monte Carlo Simulation framework for European and Asian Option Pricing

The objective of this project is to price two call option: 1) European option 2) Asian option

Here is the structure of my code:

1) Package Engine
Class Engine

Fields:
In this Class, it mainly contains an European option and an Asain option, and a statscollector used to store and compute the payoff.

Some key Methods:
(a) warmupEuroCall
This is the warmup step to generate 10000 paths and compute the mean and std. The std computed will be used to compute the final number of simulations using the formuala in the method "computeZ"
This method returns an integer N.

(b) finalAverageEuroCall
This is the second step of MonteCarloSimulation. We take the N returned from (a) and run simulation N times to return a final average payoff of the European Call option.

(c) warmupAsianCall
This is similar to (a) but for Asian call option.

(d) finalAverageAsianCall
This is similar to (b) but for Asian call option.


2) Package main
Class Main
This is the main function that takes input like DataPoint, European Option, Asian Option, Path, etc and put them into an Engine Class object to execute the monte carlo simulation. I printed out the final result in the console.

3) Package path
Class DataPoint
This Class takes one date and one price to store in one "DataPoint"

Class Path
This Class can store a list of DataPoints to form a path. For example, if we have 20 days. The one Path object will contain these 20 days' datapoint.

Class StockPathGenerator
This is basically an interface to add in parameters: DataPoint startPoint, double volatility, double dailyReturn, double periodDays

Class StockPathGeneratorImp
This Class basically is designed to generate a path.

Key Methods:
i) calculateNextDataPoint
This method is used to calculate the next DataPoint by assuming that the stock price is following a Geometric Brownian Motion. And return a DataPoint.

ii) nextPath
This method is just to add DataPoint to the path


4) Package payout
Class AsainCallOption
This class is used to compute the payoff of a Asian call option. And it implements the interface "Payout"

Class EuroCallOption 
This class is used to compute the payoff of a European call option. And it implements the interface "Payout"

Payout is served as an interface in this package.

5) Package random
RandomNumberGenerator is an interface

Class NormalRandomNumberGenerator
This Class takes two uniform random numbers and convert them into two normal distributed numbers using Box-Muller method.

Class UniformRandomGenerator
This Class will generate a uniform random number. I set it as between 0-1


6) Package stats
Class StatsCollector
This Class is used to store price and compute the mean and std. It adds price point into a list and then compute the mean, std, max, min

Key Methods:
addPayoff
This is used to add a double type price to the list

computeMeanPayoff()
This is used to compute the mean

computeStdPayoff()
This is used to compute the std.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
In the test folder, under Package mock, I tested all the relevant Class I wrote.
When possible, I used the Mockito to test the Class. For example, normalRandomGeneratorTest, I forced the output of a uniform number as a fixed one. This way I can use box muller formula to mannually convert to a normal random number. Then I can test if this one is the same as what Class generates using assertEquals.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



