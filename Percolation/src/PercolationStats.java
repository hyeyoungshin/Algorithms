import java.util.ArrayList;
import java.util.Random;


public class PercolationStats {
	
	private int n; // N in N-by-N grid
	private int trials;
	private ArrayList<Integer> openSites; // number of open sites for each experiment

	public PercolationStats(int N, int T) // perform T independent experiments on an N-by-N grid  
	{
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		this.n = N;
		trials = T;
		
		Random rand = new Random();
		int row = rand.nextInt(n) + 1;
		int col = rand.nextInt(n) + 1;
		
		int count = 0;
		while(count != trials)
		{
			Percolation perc = new Percolation(n);
			while (!perc.percolates()) {
				perc.open(row, col);
			}
			openSites.add(perc.getOpen());
			count++;
		}
	}
	public double mean() // sample mean of percolation threshold
	{
		double sum = 0;
		for (int i = 0; i < trials; i++)
		{
			sum += openSites.get(i) / (n * n);
		}
		
		return sum / (double) trials;
	}
	public double stddev() // sample standard deviation of percolation threshold
	{
		
		int sumSquars = 0;
		for (int i = 0; i < trials; i++){
			sumSquars += (openSites.get(i) / (n * n)) ^ 2;
		}
		
		double m = mean();
		
		return Math.sqrt((sumSquars - (trials * (m * m)))/(trials-1));
	}
	public double confidenceLo() //low end point of 95% confidence interval
	{
		return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
	}
	public double confidenceHi() // high end point of 95% confidence interval
	{
		return mean() + ((1.96 * stddev())/ Math.sqrt(trials));
	}
	
	public static void main(String[] args) // test client (described below)
	{
		
	}
	
		 

}
