/**
 * Classe Jeu
 *
 * @property width largeur de la grille
 * @property height hauteur de la grille
 */
class Jeu (private val width: Int,private val height: Int) {
    private val grid = Grid(width, height)
    private val snake = Snake
    private var coordApple: Coordonnee

    /**
     * Initialise le jeu
     */
    init {
        val coordTete: Coordonnee = genererCoord()
        snake.init(coordTete)

        coordApple = genererCoord()
        while (coordTete == coordApple) {
            coordApple = genererCoord()
        }

        grid.refresh(coordApple)
        play()
    }

    /**
     * Lance le jeu
     */
    private fun play() {
        while (true) {
            Affichage.display(grid)

            print("> Entrez votre direction: ")

            val choix: String = readln()
            val direction  = when (choix) {
                "w" -> Direction.NORD
                "a" -> Direction.OUEST
                "s" -> Direction.SUD
                "d" -> Direction.EST
                else -> {
                    Affichage.defaite(grid, "Direction invalide.")
                    break
                }
            }

            snake.move(direction)

            val resCode: CodeVerifCoords = grid.verifCoord(snake.coordTete)
            // Verifie si out of bound ou emplacement non vide
            if (resCode != CodeVerifCoords.OK) {
                Affichage.defaite(grid, (when (resCode) {
                    CodeVerifCoords.SNAKE_THERE -> "Le serpent est deja sur cette case !"
                    CodeVerifCoords.OUT_OF_BOUND -> "$direction est hors grille."
                    else -> ""
                }))
                break
            }

            if (snake.coordTete == coordApple) {
                mangerPomme()
                grid.refresh(coordApple)
                // Verifier Victoire
                if (grid.score == 100.00) {
                    Affichage.victore(grid)
                    break
                }
            } else {
                grid.refresh(coordApple)
            }

        }
    }

    /**
     * Gère le cas où le serpent mange une pomme
     */
    private fun mangerPomme() {
        grid.calculerPourcentage()
        snake.ajouterCorps()
        coordApple = genererCoord()
        while (!grid.estCoordVide(coordApple) && !grid.estComplete()) {
            coordApple = genererCoord()
        }
    }

    /**
     * Génère des coordonnées aléatoires
     * @return Coordonnées aléatoires
     * @see Coordonnee
     */
    private fun genererCoord(): Coordonnee {
        var coords = Coordonnee((0..<width).random(), (0..<height).random())
        while (grid.verifCoord(coords) != CodeVerifCoords.OK) {
            coords = Coordonnee((0..<width).random(), (0..<height).random())
        }
        return coords
    }
}