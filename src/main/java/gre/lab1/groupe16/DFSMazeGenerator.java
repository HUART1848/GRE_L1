package gre.lab1.groupe16;

import gre.lab1.graph.Graph;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;
import java.util.List;

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
    start[u] = ++date;

    // Début du traitement
    builder.progressions().setLabel(u, Progression.PROCESSING);

    List<Integer> neighbors = builder.topology().neighbors(u);
    Collections.shuffle(neighbors);

    for (int v : neighbors) {
      if (start[v] == 0) {
        // L'arête entre u et v fait partie de l'arborescence,
        // on enlève donc le mur correspondant.
        builder.removeWall(u, v);
        dfs(builder, v);
      }
    }
    ++date;

    // Fin du traitement
    builder.progressions().setLabel(u, Progression.PROCESSED);
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
    dfs(builder);
  }
}
