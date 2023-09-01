/**
 * Classe qui représente la grille de jeu
 * @property width largeur de la grille
 * @property height hauteur de la grille
 */
class Grid(val width: Int, val height: Int) {
    private lateinit var grid: Array<Array<String>>
    private val empty = "⬜️"
    private val snakeHead = "🟩"
    private val snakeBody = "🟦"
    private val apple = "🍎"

    var score = 0.0

    /**
    * Initialise la grille
    */
    init {
        newGrid()
    }

    /**
     * Retourne la case aux coordonnées données
     * @param x coordonnée x de la case
     * @param y coordonnée y de la case
     * @return la case aux coordonnées données
     */
    fun getCase(x: Int, y: Int) : String {
        return grid[x][y]
    }

    /**
     * Met la valeur donnée dans la case aux coordonnées données
     * @param coord coordonnées de la case
     * @param value valeur à mettre dans la case
     * @return la case aux coordonnées données
     * @see Coordonnee
     */
    private fun setCase(coord: Coordonnee, value: String) {
        grid[coord.x][coord.y] = value
    }

    /**
     * Si la case aux coordonnées données est vide
     * @param coord coordonnées de la case
     * @return Booléen indiquant si la case est vide
     * @see Coordonnee
     */
    fun estCoordVide(coord: Coordonnee): Boolean {
        return grid[coord.x][coord.y] == empty
    }

    /**
     * Si la grille est complète
     * @return Booléen indiquant si la grille est complète
     */
    fun estComplete(): Boolean {
        return grid.all { range -> range.all { case -> case != empty } }
    }

    /**
     * Vérifie si les coordonnées données sont valides
     * @param coord coordonnées à vérifier
     * @return CodeCoords indiquant si les coordonnées sont valides
     * @see Coordonnee
     * @see CodeVerifCoords
     */
    fun verifCoord(coord: Coordonnee): CodeVerifCoords {
        if (coord.x !in 0..< width || coord.y !in 0..<height) {
            return CodeVerifCoords.OUT_OF_BOUND
        }

        if (grid[coord.x][coord.y] == snakeHead || grid[coord.x][coord.y] == snakeBody) {
            return CodeVerifCoords.SNAKE_THERE
        }

        return CodeVerifCoords.OK
    }

    /**
     * Rafraichit la grille
     * @param coordApple coordonnées de la pomme
     * @see Coordonnee
     */
    fun refresh(coordApple : Coordonnee) {
        newGrid()
        setCase(coordApple, apple)
        setCase(Snake.coordTete, snakeHead)

        for (coord: Coordonnee in Snake.listeCoordCorps) {
            setCase(coord, snakeBody)
        }

        calculerPourcentage()
    }

    /**
     * Calcule le pourcentage de la grille occupée par le serpent
     */
    fun calculerPourcentage() {
        var compte = 0.0
        val validation: (String) -> Boolean = { it == snakeHead || it == snakeBody }

        for (rangee in grid) {
            compte += rangee.count(validation)
        }

        score = compte / (width * height) * 100
    }

    /**
     * Crée une nouvelle grille vide
     */
    private fun newGrid() {
        grid = Array(width) { Array(height) { empty } }
    }
}