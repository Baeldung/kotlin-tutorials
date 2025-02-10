import org.apache.logging.log4j.kotlin.logger

class Game {
    companion object {
        private val logger = logger()
    }

    fun startGame() {
        logger.info { "Game is starting..." }
    }

    fun loadLevel(levelName: String) {
        logger.info { "Loading level: $levelName" }
        try {
            if (levelName.isBlank()) {
                throw IllegalArgumentException("Level name cannot be empty")
            }
            logger.debug { "Level '$levelName' loaded successfully." }
        } catch (ex: Exception) {
            logger.error(ex) { "Error loading level: $levelName" }
        }
    }
}
