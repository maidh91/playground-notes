public class Digraph {
  private final int V;
  private final Bag<Integer>[] adjacent;

  @SuppressWarnings("unchecked")
  public Digraph(int V) {
    this.V = V;
    this.adjacent = new Bag[V];

    for (int v = 0; v < this.V; ++v) {
      adjacent[v] = new Bag<Integer>();
    }
  }

  public void addEdge(int v, int w) {
    adjacent[v].add(w);
  }

  public Iterable<Integer> adj(int v) {
    return adjacent[v];
  }

  public int V() {
    return V;
  }
}