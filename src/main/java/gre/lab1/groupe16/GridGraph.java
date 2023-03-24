package gre.lab1.groupe16;

import gre.lab1.graph.GridGraph2D;

import java.util.List;
import java.util.ArrayList;


public final class GridGraph implements GridGraph2D {

  /**
   * Allocation de la capacité d'une liste d'adjacence par défaut.
   * Un sommet possède 4 voisins dans la majorité des cas.
   */
  private static final int DEFAULT_EDGE_ALLOCATION = 4;

  /**
   * Largeur de la grille.
   */
  private final int width;

  /**
   * Hauteur de la grille.
   */
  private final int height;

  /**
   * Listes d'adjacence du graphe
   */
  private final ArrayList<ArrayList<Integer>> adjacencyLists;

  /**
   * Construit une grille carrée.
   *
   * @param side Côté de la grille.
   */
  public GridGraph(int side) {
    this(side, side);
  }

  /**
   * Construit une grille rectangulaire.
   *
   * @param width  Largeur de la grille.
   * @param height Hauteur de la grille.
   * @throws IllegalArgumentException si {@code width} ou {@code length} sont négatifs ou nuls.
   */
  public GridGraph(int width, int height) {
    if (width <= 0 || height <= 0)
      throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");

    this.width = width;
    this.height = height;

    adjacencyLists = new ArrayList<>(nbVertices());

    for (int i = 0; i < nbVertices(); ++i) {
      adjacencyLists.add(new ArrayList<>(DEFAULT_EDGE_ALLOCATION));
    }
  }

  @Override
  public List<Integer> neighbors(int v) {
    return new ArrayList<>(adjacencyLists.get(v));
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    return adjacencyLists.get(u).contains(v);
  }

  @Override
  public void addEdge(int u, int v) {
    if (!areAdjacent(u, v)) {
      adjacencyLists.get(u).add(v);
    }

    if (!areAdjacent(v, u)) {
      adjacencyLists.get(v).add(u);
    }
  }

  @Override
  public void removeEdge(int u, int v) {
    adjacencyLists.get(u).removeIf(i -> i == v);
    adjacencyLists.get(v).removeIf(i -> i == u);
  }

  @Override
  public int nbVertices() {
    return width * height;
  }

  @Override
  public boolean vertexExists(int v) {
    return v >= 0 && v < nbVertices();
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
   *
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {
    for (int u = 0; u < graph.nbVertices(); ++u) {
      // Liaison au sommet nord
      if (graph.vertexExists(u - graph.width())) {
        graph.addEdge(u, u - graph.width());
      }

      // Au sommet sud
      if (graph.vertexExists(u + graph.width())) {
        graph.addEdge(u, u + graph.width());
      }

      // Au sommet est
      if (u % graph.width() + 1 < graph.width()) {
        graph.addEdge(u, u + 1);
      }

      // Au sommet ouest
      if (u % graph.width - 1 >= 0) {
        graph.addEdge(u, u - 1);
      }
    }
  }
}
