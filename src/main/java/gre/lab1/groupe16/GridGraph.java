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



    //-------- remplisage de la matrice d'adjacence --------
    for (int i = 1; i < (width  * height) + 1; i++ ){
      adjacencyLists.add( new ArrayList<Integer>());
      if(i == 1){

       adjacencyLists.get(i - 1).add(i+1);
       adjacencyLists.get(i - 1).add(i+width);

      }else if(i == width){

        adjacencyLists.get(i - 1).add(i -1);
        adjacencyLists.get(i - 1).add(i - width);

      }else if(i == width*height){

        adjacencyLists.get(i - 1).add(i - 1);
        adjacencyLists.get(i - 1).add(i - width);

      }else if(i == (width*(height - 1) + 1)){

        adjacencyLists.get(i - 1).add(i+1);
        adjacencyLists.get(i - 1).add(i - width);

      }else if(i < width){

        adjacencyLists.get(i - 1).add(i-1);
        adjacencyLists.get(i - 1).add(i+1);
        adjacencyLists.get(i - 1).add(i+width);

      }else if(i % width == 0){

        adjacencyLists.get(i - 1).add(i-width);
        adjacencyLists.get(i - 1).add(i+width);
        adjacencyLists.get(i - 1).add(i -1);

      }else if(i % width == 1){

        adjacencyLists.get(i - 1).add(i-width);
        adjacencyLists.get(i - 1).add(i+width);
        adjacencyLists.get(i - 1).add(i + 1);

      }else  if(i > (width*(height - 1))){

        adjacencyLists.get(i - 1).add(i-1);
        adjacencyLists.get(i - 1).add(i+1);
        adjacencyLists.get(i - 1).add(i - width);

      }else {

        adjacencyLists.get(i - 1).add(i-1);
        adjacencyLists.get(i - 1).add(i+1);
        adjacencyLists.get(i - 1).add(i - width);
        adjacencyLists.get(i - 1).add(i+width);

      }
    }

  }


  @Override
  public List<Integer> neighbors(int v) {
   return adjacencyLists.get( v -1 );
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    return adjacencyLists.get(u-1).contains(v);
  }

  @Override
  public void addEdge(int u, int v) {
    // TODO: A implémenter
  }

  @Override
  public void removeEdge(int u, int v) {
    // TODO: A implémenter
  }

  @Override
  public int nbVertices() {
    // TODO: A implémenter
    return 0;
  }

  @Override
  public boolean vertexExists(int v) {
    // TODO: A implémenter
    return false;
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
    // TODO: A implémenter
  }

  public void printGraph(){
    for(int i = 0; i < width*height; i++){
      System.out.print(i+1+" :");
      for(int j = 0; j < adjacencyLists.get(i).size();j++){
        System.out.print(" " + adjacencyLists.get(i).get(j) );
      }
      System.out.print("\n");
    }
  }

  public static void main(String[] args) {
    GridGraph g = new GridGraph(4,5);
    g.printGraph();

    System.out.println(g.areAdjacent(6,5));
    System.out.println(g.areAdjacent(14,5));
  }
}


