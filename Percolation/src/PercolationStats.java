import java.util.ArrayList;
import java.util.Random;


public class PercolationStats {
	
	private int n; // N in N-by-N grid
	private int exp;
	private ArrayList<Integer> openSites; // number of open sites for each experiment

	public PercolationStats(int N, int T) // perform T independent experiments on an N-by-N grid  
	{
		if(N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		this.n = N;
		exp = T;
		
		Random rand = new Random();
		int row = rand.nextInt(n) + 1;
		int col = rand.nextInt(n) + 1;
		
		Percolation perc = new Percolation(n);
		while(exp != 0) {
			perc.open(row, col);
			exp--;
		}
	}
	public double mean() // sample mean of percolation threshold
	{
		return 0.0;
	}
	public double stddev() // sample standard deviation of percolation threshold
	{
		return 0.0;
	}
	public double confidenceLo() //low end point of 95% confidence interval
	{
		return 0.0;
	}
	public double confidenceHi() // high end point of 95% confidence interval
	{
		return 0.0;
	}
	public static void main(String[] args) // test client (described below)
	{
		
	}
	
		 

}
