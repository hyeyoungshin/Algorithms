package undirectedGraph;

import java.util.Stack;

public class DepthFirstPaths {
	private boolean[] marked; // true if v connected to s
	private int[] edgeTo; // previous vertex on path from s to v
	private int s;
	
	public DepthFirstPaths(Graph G, int s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G, s); // find vertices connected to s
	}
	
	private void dfs(Graph G, int v) // recursive DFS does the work
	{
		marked[v] = true;
		for (int w: G.adj(v))
			if (!marked[w])
			{
				dfs(G, w);
				edgeTo[w] = v; // v is the parent
			}
	}
	
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) // move up to the source by following the parents
			path.push(x);
		path.push(s);
		return path;
		
	}
}
