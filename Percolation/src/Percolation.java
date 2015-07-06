/**
 * Programming Assignment 1: Percolation
 * Algorithms, Part 1 by Robert Sedgewick and Kevin Wayne
 * 
 * @author Hyeyoung Shin
 *
 */

public class Percolation {
	private int n; // n in 'n-by-n grid'
	private int[] id;
	private int[] size;
	private int[][] grid;
	private int comp;
	private int numOpen; // <== Do I need this?
	private final int OPEN = 1; // 1 when open, 0 when blocked

	public Percolation(int N)
	{
		if (N < 1) throw new IllegalArgumentException();

		// create N-by-N grid, with all sites blocked
		n = N;
		id = new int[n * n - 1];
		for (int i = 0; i < n * n - 1; i++)
		{
			id[i] = i;
		}
		grid = new int[n][n];
		size = new int[n];
		comp = n;
		numOpen = 0;
	}

	/**
	 * Open site (row i, column j) if it is not open already
	 * @param i
	 * @param j
	 */
	public void open(int i, int j)
	{
		if (!valid(i, j)) throw new IndexOutOfBoundsException();

		if (!isOpen(i, j))
		{
			grid[i][j] = OPEN;
			numOpen = numOpen + 1; // <-- may not need

			if (valid(i-1, j-1) && valid(i+1, j+1)) {
				int id = getId(i,j);
				if (isOpen(i-1, j)) union(id, getId(i-1, j));
				if (isOpen(i, j-1)) union(id, getId(i, j-1));
				if (isOpen(i+1, j)) union(id, getId(i+1, j));
				if (isOpen(i, j+1)) union(id, getId(i, j+1));
			}
		}
	}

	/** Check if inputs are valid 
	 * @param i 
	 * @param j
	 * @return true if inputs are valid 
	 * 		   false otherwise
	 */
	private boolean valid(int i, int j)
	{
		// id range is from 0 to n-1
		if (i < 0 || i >= n || j < 0 || j >= n)
		{
			return false;
		}
		return true;
	}

	/**
	 * Get id of a site
	 * @param i row of grid
	 * @param j column of grid
	 * @return id of grid[i][j]
	 */
	private int getId(int i, int j) 
	{
		return j + 3 * i;
	}

	public int getOpen()
	{
		return numOpen;
	}

	private boolean connected(int p, int q)
	{
		return root(p) == root(q);
	}

	private int root(int i)
	{
		if (i < 1) throw new IllegalArgumentException();

		while (i != id[i]) 
		{
			i = id[i];
		}
		return i;
	}

	private void union(int p, int q)
	{
		int rootP = root(p);
		int rootQ = root(q);
		if (rootP == rootQ) return;

		// make smaller root point to larger one
		if (size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
		}
		else
		{
			id[rootQ] = rootP;
			size[rootP] += rootQ;
		}
		comp--; // number of components reduces by 1 per union
	}

	/**
	 * Is site (row i, column j) open?
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) 
	{
		return grid[i][j] == OPEN;
	}

	/**
	 * Is site (row i, column j) full?
	 * @param i row
	 * @param j column
	 * @return true if site is connected to an open site in the top row
	 *         false otherwise
	 */
	public boolean isFull(int i, int j) 
	{
		for (int k = 0; k < n; k++)
		{
			if (isOpen(0, k)) 
				if (connected(getId(0, k), getId(i, j))) return true;
		}
		return false;
	}

	/**
	 *  does the system percolate?
	 * @return true if percolates
	 *         false otherwise 
	 */
	public boolean percolates()
	{
		for (int k = 0; k < n; k++)
		{
			if (isOpen(n-1, k))
				if (isFull(n-1, k)) return true;
		}
		return false;
	}

	// Test client(optional)
	public static void main(String[]args) 
	{
		Percolation p = new Percolation(3);
		p.open(0, 0);
		System.out.println(p.isOpen(0, 0) == true);

	}
}
