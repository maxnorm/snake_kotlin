/**
 * Classe Jeu
 */
class Jeu (val width: Int, val height: Int) {
    private val grid = Grid(width, height)
    private val snake = Snake
    private lateinit var coordApple: Coordonee

    fun start() {
        val coordTete: Coordonee = genererCoord()
        snake.init(coordTete)

        coordApple = genererCoord()
        while (coordTete == coordApple) {
            coordApple = genererCoord()
        }

        grid.refresh(coordApple)
        play()
    }

    private fun play() {
        while (true) {
            display()

            print("> Entrez votre direction: ")

            val choix: String = readln()
            val direction  = when {
                choix == "w" -> Direction.NORD
                choix == "a" -> Direction.OUEST
                choix == "s" -> Direction.SUD
                choix == "d" -> Direction.EST
                else -> {
                    println("Direction invalide.")
                    gererDefaite()
                    break
                }
            }

            snake.move(direction)


            val resCode: CodeCoords = grid.verifCoord(snake.coordTete)
            // Verifie si out of bound ou emplacement non vide
            if (resCode != CodeCoords.OK && resCode != CodeCoords.APPLE_THERE) {
                println(
                    (when (resCode) {
                        CodeCoords.SNAKE_THERE -> "Le serpent est deja sur cette case !"
                        CodeCoords.OUT_OF_BOUND -> "$direction est hors grille."
                        else -> ""
                    })
                )
                gererDefaite()
                break
            }

            if (snake.coordTete == coordApple) {
                mangerPomme()
                grid.refresh(coordApple)
                // Verifier Victoire
                if (grid.score == 100.00) {
                    println("BRAVO !!")
                    display()
                    break
                }
            } else {
                grid.refresh(coordApple)
            }

        }
    }

    private fun mangerPomme() {
        grid.calculerPourcentage()
        snake.ajouterCorps()
        coordApple = genererCoord()
        while (!grid.estCoordVide(coordApple) && !grid.estComplete()) {
            coordApple = genererCoord()
        }
    }


    private fun display() {
        for (i in 0..<grid.width) {
            for (j in 0..<grid.height) {
                print(grid.getCase(i, j))
                print(" ")
            }
            println()
        }
        println(String.format("SCORE : %.2f%%", grid.score))
        println()
    }

    private fun gererDefaite() {
        display()
        println("PARTIE TERMINEE")
    }

    private fun genererCoord(): Coordonee {
        var coords = Coordonee((0..<width).random(), (0..<height).random())
        while (grid.verifCoord(coords) != CodeCoords.OK) {
            coords = Coordonee((0..<width).random(), (0..<height).random())
        }
        return coords
    }
}