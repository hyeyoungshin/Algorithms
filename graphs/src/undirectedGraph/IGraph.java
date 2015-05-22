package undirectedGraph;

public interface IGraph {
	void addEdge(int v, int w);
	Iterable<Integer> adj(int v);
	int V();
	int E();
	String toString();
}
