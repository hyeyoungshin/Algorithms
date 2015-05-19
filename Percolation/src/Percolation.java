/**
 * 
 * @author Hyeyoung Shin
 *
 */
public class Percolation {
	private int[] id;
	private int[][] grid;
	private int numOpen;
	private final int OPEN = 1;
	private final int BLOCKED = 0;
	
	public Percolation(int N)
	{
		if(N < 1) throw new IllegalArgumentException();
		
		// create N-by-N grid, with all sites blocked
		id = new int[N];
		for(int i=0; i<N; i++)
		{
			id[i] = i;
		}
		grid = new int[N][N];
		numOpen = 0;
	}
	
	/**
	 * Open site (row i, column j) if it is not open already
	 * @param i
	 * @param j
	 */
	public void open(int i, int j)
	{
		if(!isValidIndex(i, j)) throw new IndexOutOfBoundsException();
		
		int N = id.length;
		int p = id[(i-1)*N + (j-1)];
		
		if(!isOpen(i, j))
		{
			grid[i][j] = OPEN;
			numOpen++;
			
			int[] neighbors = getNeighbors(i, j);
			if(isOpen(i-1, j) && !connected(p, neighbors[0]))
			{
				union(p, neighbors[0]);
			}
		}
	}
	
	private boolean isValidIndex(int i, int j)
	{
		int N = id.length;
		
		if(i < 1 || i > N || j < 1 || j > N)
		{
			return true;
		}
		return false;
	}
	
	private int[] getNeighbors(int i, int j)
	{
		if(!isValidIndex(i, j)) throw new IndexOutOfBoundsException();
		
		int N = id.length;
		
		int p = id[(i-1)*N + (j-1)];
		int[] neighbors = new int[4];
		
		if(isValidIndex(i-1, j)) //top
		{
			neighbors[0] = p - N;
		}
		if(isValidIndex(i+1, j)) //bottom
		{
			neighbors[1] = p + N;
		}
		if(isValidIndex(i, j-1)) //left
		{
			neighbors[2] = p - 1;
		}
		if(isValidIndex(i, j+1)) //right
		{
			neighbors[3] = p + 1;
		}
		
		return neighbors;
	}
	
	private boolean connected(int p, int q)
	{
		return root(p) == root(q);
	}
	
	private int root(int i)
	{
		while(i != id[i]) 
		{
			id[i] = id[id[i]]; //make every other node in path point to its grandparent --> halving path length
			i = id[i];
		}
			
		return i;
	}
	
	private void union(int p, int q)
	{
		int pRoot = root(p);
		int qRoot = root(q);
		if(pRoot == qRoot) return;
		else
		{
			id[p] = qRoot;
		}
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
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) 
	{
		
	}
	
	// does the system percolate?
	public boolean percolates()
	{
		int N = id.length;
		for(int i = N-1*N;  i < N*N-1; i++)
		{
			if(isFull())
		}
	}
	  
	//testclient(optional)
	public static void main(String[]args) 
	{
		
	}
}
