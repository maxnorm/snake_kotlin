/**
 * Snake X and Y are for the head
 */
object Snake {
    lateinit var coordTete: Coordonee
    var listeCoordCorps: MutableList<Coordonee> = mutableListOf()
    lateinit var ancienCoord: Coordonee

    fun init(coord : Coordonee) {
        coordTete = coord
    }


    fun move(direction: Direction) {
        ancienCoord = Coordonee(coordTete.x, coordTete.y)

        deplacerTete(direction)

        for (coord: Coordonee in listeCoordCorps) {
            val ancienCoordCorps = Coordonee(coord.x, coord.y)
            coord.x = ancienCoord.x
            coord.y = ancienCoord.y
            ancienCoord = ancienCoordCorps
        }
    }

    fun deplacerTete(direction: Direction) {
        when (direction) {
            Direction.NORD -> coordTete.x--
            Direction.EST -> coordTete.y++
            Direction.OUEST -> coordTete.y--
            Direction.SUD -> coordTete.x++
        }
    }

    fun ajouterCorps() {
        listeCoordCorps.add(Coordonee(ancienCoord.x, ancienCoord.y))
    }
}