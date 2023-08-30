/**
 * Class Grid represent the grid of the game
 * @property width Int With of the grid
 * @property height Int Height of the grid
 */
class Grid(val width: Int, val height: Int) {
    private lateinit var grid: Array<Array<String>>

    private val snakeHead = "🟩"
    private val snakeBody = "🟦"
    private val apple = "🍎"
    private val empty = "□"
    var score = 0.0

    fun init() =
        newGrid()


    fun getCase(x: Int, y: Int) : String {
        return grid[x][y]
    }

    private fun setCase(coord: Coordonee, value: String) {
        grid[coord.x][coord.y] = value
    }

    fun verifCoord(coord: Coordonee): CodeCoords {
        if (coord.x !in 0..< width || coord.y !in 0..<height) {
            return CodeCoords.OUT_OF_BOUND
        }

        if (grid[coord.x][coord.y] == snakeHead && grid[coord.x][coord.y] != snakeBody) {
            return CodeCoords.SNAKE_THERE
        }

        return CodeCoords.OK
    }

    fun refresh(coordApple : Coordonee) {
        newGrid()
        setCase(coordApple, apple)
        setCase(Snake.coordTete, snakeHead)

        for (coord: Coordonee in Snake.listeCoordCorps) {
            setCase(coord, snakeBody)
        }

        calculerPourcentage()
    }

    fun calculerPourcentage() {
        var compte = 0.0
        val validation: (String) -> Boolean = { it == snakeHead || it == snakeBody }

        for (rangee in grid) {
            compte += rangee.count(validation)
        }

        score = compte / (width * height) * 100
    }

    private fun newGrid() {
        grid = Array(width) { Array(height) { empty } }
    }
}