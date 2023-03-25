package gre.lab1.groupe16;

import gre.lab1.graph.GridGraph2D;

import java.util.List;
import java.util.ArrayList;


// TODO: javadoc
public final class GridGraph implements GridGraph2D {
  /** Largeur de la grille. */
  private final int width ;

  /** Hauteur de la grille. */
  private final int height ;

  /** Matrice d'ajacence du graphe */
  private ArrayList<ArrayList<Integer>> adjacencyLists;


  /**
   * Construit une grille carrée.
   * @param side Côté de la grille.
   */
  public GridGraph(int side) {
    this(side, side);
  }

  /**
   * Construit une grille rectangulaire.
   * @param width Largeur de la grille.
   * @param height Hauteur de la grille.
   * @throws IllegalArgumentException si {@code width} ou {@code length} sont négatifs ou nuls.
   */
  public GridGraph(int width, int height) {
    if (width <= 0 || height <= 0)
      throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");

    this.width = width;
    this.height = height;
    adjacencyLists = new ArrayList<>();
    for (int i = 0; i < width  * height; i++ ){
      adjacencyLists.add( new ArrayList<Integer>());}

  }


  @Override
  public List<Integer> neighbors(int v) {
   return adjacencyLists.get( v );
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    if(!vertexExists(u)){
      throw new IllegalArgumentException("Vertex don't exsit");
    }
    return adjacencyLists.get(u).contains(v);
  }

  @Override
  public void addEdge(int u, int v) {
    if(!vertexExists(u) || !vertexExists(v)){
      throw new IllegalArgumentException("One or both vertex don't exist");
    }
    adjacencyLists.get(u).add(v);
    adjacencyLists.get(v).add(u);
  }

  @Override
  public void removeEdge(int u, int v) {
    if(!areAdjacent(u,v)){
      throw new IllegalArgumentException("Vertex must be adjacents");
    }
    adjacencyLists.get(u).remove(v);
    adjacencyLists.get(v).remove(u);
  }

  @Override
  public int nbVertices() {
    return width*height;
  }

  @Override
  public boolean vertexExists(int v) {
    return (v >= 0 && v < height*width );
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  /**
   * Lie chaque sommet du graphe donné à tous ses voisins dans la grille.
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {
    for (int i = 0, size = graph.height * graph.width; i < size; ++i) {
      if (i % graph.width != 0) {
        graph.addEdge(i, i - 1);;
      }
      if (i >= graph.width) {
        graph.addEdge(i, i - graph.width);
      }
    }
  }


  public void printGraph(){
    for(int i = 0; i < width*height; i++){
      System.out.print(i +" :");
      for(int j = 0; j < adjacencyLists.get(i).size();j++){
        System.out.print(" " + adjacencyLists.get(i).get(j) );
      }
      System.out.print("\n");
    }
  }

  public static void main(String[] args) {
    GridGraph g = new GridGraph(4,5);
    g.bindAll(g);
    g.printGraph();

    System.out.println(g.areAdjacent(6,5));
    System.out.println(g.areAdjacent(14,5));
  }
}


