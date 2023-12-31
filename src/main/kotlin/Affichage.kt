/**
 * Objet permettant d'afficher la grille
 */
object Affichage {
    /**
     * Affiche la grille et le score
     * @param grid grille à afficher
     * @see Grid
     */
    fun display(grid: Grid) {
        for (i in 0..<grid.width) {
            print("\t")
            for (j in 0..<grid.height) {
                print(grid.getCase(i, j))
                print("\t")
            }
            println()
        }
        println(String.format("SCORE : %.2f%%", grid.score))
        println()
    }

    /**
     * Affiche la grille et un message de victoire
     * @param grid grille à afficher
     * @see Grid
     */
    fun victore(grid: Grid){
        println("BRAVO !!")
        display(grid)
    }

    /**
     * Affiche la grille et un message de défaite
     * @param grid grille à afficher
     * @param msg message à afficher
     * @see Grid
     */
    fun defaite(grid: Grid, msg: String){
        println(msg)
        display(grid)
        println("PARTIE TERMINEE")
    }


}