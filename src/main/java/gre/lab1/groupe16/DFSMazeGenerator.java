package gre.lab1.groupe16;

import gre.lab1.graph.Graph;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;

import java.util.ArrayList;

// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  public void dfs(ArrayList<Integer> u) {

  }

  public void dfs(Graph topology) {

  }

  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.
  }
}
