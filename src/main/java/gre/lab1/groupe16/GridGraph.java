package gre.lab1.groupe16;

import gre.lab1.graph.GridGraph2D;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


// TODO: javadoc
public final class GridGraph implements GridGraph2D {
    public final int DEFAULT_EDGE_ALLOCATION = 4;

    /**
     * Largeur de la grille.
     */
    private final int width;

    /**
     * Hauteur de la grille.
     */
    private final int height;

    /**
     * Liste d'adjacence du graphe
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
        return this.adjacencyLists.get(v);
    }

    @Override
    public boolean areAdjacent(int u, int v) {
        return adjacencyLists.get(u).contains(v);
    }

    @Override
    public void addEdge(int u, int v) {
        adjacencyLists.get(u).add(v);
    }

    @Override
    public void removeEdge(int u, int v) {
        adjacencyLists.get(u).removeIf(i -> i == v);
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
        for (int i = 0; i < graph.nbVertices(); ++i) {
            // Liaison au sommet nord
            if (i % graph.height > 0)
                graph.addEdge(i, i - 1);

            // Liaison au sommet est
            if (i + graph.height < graph.nbVertices())
                graph.addEdge(i, i + graph.height);

            // Liaison au sommet sud
            if (i % graph.height < graph.height - 1)
                graph.addEdge(i, i + 1);

            // Liaison au sommet ouest
            if (i - graph.height >= 0)
                graph.addEdge(i, i - graph.height);
        }
    }

    public void printGraph() {
        for (int i = 0; i < width * height; i++) {
            System.out.print(i + " :");
            for (int j = 0; j < adjacencyLists.get(i).size(); j++) {
                System.out.print(" " + adjacencyLists.get(i).get(j));
            }
            System.out.println();
        }
    }
}
