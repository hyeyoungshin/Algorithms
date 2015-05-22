package undirectedGraph;

public class UndirectedGraph {

	public static int degree(Graph G, int v)
	{
		int degree = 0;
		for(int w : G.adj(v)) degree++;
		return degree;
	}
	
	public static int maxDegree(Graph G)
	{
		int max = 0;
		for(int v=0; v < G.V(); v++)
			for(int w : G.adj(v))
			{
				if(degree(G, w) > max)
					max = degree(G, w);
			}
		return max;
	}
	
	public static double averageDegree(Graph G)
	{
		return 2.0 * G.E() / G.V(); // every edge can be seen as degree 2
	}
	
	public static int numberOfSelfLoops(Graph G)
	{
		int count = 0;
		for(int v=0; v<G.V(); v++)
			for(int w : G.adj(v))
				if(v==w) count++;
				
		return count/2; //each edge counted twice
	}
	
}
