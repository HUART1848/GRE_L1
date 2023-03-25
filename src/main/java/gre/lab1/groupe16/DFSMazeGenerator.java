package gre.lab1.groupe16;

import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;
import java.util.List;

public final class DFSMazeGenerator implements MazeGenerator {

  /**
   * <p>Génère un nouveau labyrinthe à l'aide de l'algorithme DFS.</p>
   * @param builder Un builder à qui déléguer les modifications de la structure de données.
   * @param from Sommet de départ, si l'algorithme utilisé en nécessite un.
   */
  @Override
  public void generate(MazeBuilder builder, int from) {
    builder.progressions().setLabel(from, Progression.PROCESSING);

    // Pour parcourir les sommets adjacents dans un ordre aléatoire,
    // on mélange la liste des voisins.
    List<Integer> neighbors = builder.topology().neighbors(from);
    Collections.shuffle(neighbors);

    for (int v : neighbors) {
      if (builder.progressions().getLabel(v) == Progression.PENDING) {
        // L'arête {u, v} fait partie de l'arborescence d'exploration du graphe.
        // On enlève le mur correspondant.
        builder.removeWall(from, v);
        generate(builder, v);
      }
    }

    builder.progressions().setLabel(from, Progression.PROCESSED);
  }
}
