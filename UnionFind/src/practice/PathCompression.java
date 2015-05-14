package practice;

public class PathCompression {
	private int[] id;
	private int[] sz;
	
	public PathCompression(int N)
	{
		id = new int[N];
		for(int i = 0; i < N; i++) id[i] = i;
		sz = new int[N];
		for(int i = 0; i < N; i++) id[i] = 1;
	}
	
	private int root(int i)
	{
		while(i != id[i]) 
			id[i] = id[id[i]]; //every node on the way up to the root gets below the root
			i = id[i];
		return i;
	}
	
	public boolean find(int p, int q)
	{
		return root(p) == root(q);
	}
	
	public void union(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		if(sz[i] == sz[j]) { id[i] = j; sz[j] += sz[i]; }
		else			   { id[j] = i; sz[i] += sz[j]; }
	}

}