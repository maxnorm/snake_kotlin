/**
 * Classe qui représente le serpent
 * @property coordTete coordonnées de la tête du serpent
 * @property listeCoordCorps liste des coordonnées du corps du serpent
 * @property ancienCoord coordonnées de la dernière case du serpent
 */
object Snake {
    lateinit var coordTete: Coordonnee
    var listeCoordCorps: MutableList<Coordonnee> = mutableListOf()
    lateinit var ancienCoord: Coordonnee

    /**
     * Initialise le serpent
     * @param coord coordonnées de la tête du serpent
     * @see Coordonnee
     */
    fun init(coord : Coordonnee) {
        coordTete = coord
    }

    /**
     * Déplace le serpent dans la direction donnée
     * @param direction direction dans laquelle le serpent doit se déplacer
     * @see Direction
     */
    fun move(direction: Direction) {
        ancienCoord = Coordonnee(coordTete.x, coordTete.y)

        deplacerTete(direction)

        for (coord: Coordonnee in listeCoordCorps) {
            val ancienCoordCorps = Coordonnee(coord.x, coord.y)
            coord.x = ancienCoord.x
            coord.y = ancienCoord.y
            ancienCoord = ancienCoordCorps
        }
    }

    /**
     * Déplace la tête du serpent dans la direction donnée
     * @param direction direction dans laquelle la tête du serpent doit se déplacer
     * @see Direction
     */
    private fun deplacerTete(direction: Direction) {
        when (direction) {
            Direction.NORD -> coordTete.x--
            Direction.EST -> coordTete.y++
            Direction.OUEST -> coordTete.y--
            Direction.SUD -> coordTete.x++
        }
    }

    /**
     * Ajoute une case au corps du serpent
     */
    fun ajouterCorps() {
        listeCoordCorps.add(Coordonnee(ancienCoord.x, ancienCoord.y))
    }
}