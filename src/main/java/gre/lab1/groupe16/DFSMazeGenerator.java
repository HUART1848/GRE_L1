package gre.lab1.groupe16;

import gre.lab1.graph.Graph;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;

import java.util.ArrayList;

// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  /**
   * Date courante.
   */
  private int date;

  /**
   * Tableau des dates de début de traitement.
   */
  private int[] start;

  private void dfs(MazeBuilder builder, int u) {

  }

  private void dfs(MazeBuilder builder) {
    start = new int[builder.topology().nbVertices()];
    date = 0;

    for (int u = 0; u < builder.topology().nbVertices(); ++u) {
      if (start[u] == 0) {
        dfs(builder, u);
      }
    }
  }

  @Override
  public void generate(MazeBuilder builder, int from) {
    ((GridGraph) builder.topology()).printGraph();
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.
  }
}
