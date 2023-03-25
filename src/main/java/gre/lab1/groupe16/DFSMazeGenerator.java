// TODO: refactor le nom du package groupeX avec le bon numéro de groupe (SHIFT + F6)
package gre.lab1.groupe16;

import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;
import java.util.Collections;
import java.util.List;
// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.

    // Marque la case comme en cours de traitement
    builder.progressions().setLabel(from, Progression.PROCESSING);

    // Récupère tous les voisins
    List<Integer> neighbors = builder.topology().neighbors(from);

    // Les met dans un ordre aléatoire
    Collections.shuffle(neighbors);

    for (int vertex : neighbors) {
      // Ignore les cases déjà explorées
      if(builder.progressions().getLabel(vertex) != Progression.PENDING)
        continue;

      // Quand on a trouvé un voisin acceptable, enlève le mur
      builder.removeWall(from, vertex);
      // traite de manière récursive ses voisins
      generate(builder, vertex);
    }
    // Marque la case comme traitée
    builder.progressions().setLabel(from, Progression.PROCESSED);
  }
}
