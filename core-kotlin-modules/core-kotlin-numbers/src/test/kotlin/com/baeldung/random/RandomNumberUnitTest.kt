
import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.test.assertTrue

class RandomNumberUnitTest {

    @Test
    fun whenRandomNumberWithJavaUtilMath_thenResultIsBetween0And1() {
        val randomNumber = Math.random()
        assertTrue { randomNumber >=0 }
        assertTrue { randomNumber <= 1 }
    }

    @Test
    fun whenRandomNumberWithJavaThreadLocalRandom_thenResultsInDefaultRanges() {
        val randomDouble = ThreadLocalRandom.current().nextDouble()
        val randomInteger = ThreadLocalRandom.current().nextInt()
        val randomLong = ThreadLocalRandom.current().nextLong()
        assertTrue { randomDouble >= 0 }
        assertTrue { randomDouble <= 1 }
        assertTrue { randomInteger >= Integer.MIN_VALUE }
        assertTrue { randomInteger <= Integer.MAX_VALUE }
        assertTrue { randomLong >= Long.MIN_VALUE }
        assertTrue { randomLong <= Long.MAX_VALUE }
    }

    @Test
    fun whenRandomNumberWithKotlinJSMath_thenResultIsBetween0And1() {
        val randomDouble = Math.random()
        assertTrue { randomDouble >=0 }
        assertTrue { randomDouble <= 1 }
    }

    @Test
    fun whenRandomNumberWithKotlinNumberRange_thenResultInGivenRange() {
        val randomInteger = (1..12).shuffled().first()
        assertTrue { randomInteger >= 1 }
        assertTrue { randomInteger <= 12 }
    }

    @Test
    fun whenRandomNumberWithJavaThreadLocalRandom_thenResultsInGivenRanges() {
        val randomDouble = ThreadLocalRandom.current().nextDouble(1.0, 10.0)
        val randomInteger = ThreadLocalRandom.current().nextInt(1, 10)
        val randomLong = ThreadLocalRandom.current().nextLong(1, 10)
        assertTrue { randomDouble >= 1 }
        assertTrue { randomDouble <= 10 }
        assertTrue { randomInteger >= 1 }
        assertTrue { randomInteger <= 10 }
        assertTrue { randomLong >= 1 }
        assertTrue { randomLong <= 10 }
    }

    @Test
    fun whenRandomNonRepeatingNumbersWithShuffle_thenResultsAreUnique() {
        val randomNumbers = (1..10).shuffled().take(5)
        assertTrue { randomNumbers.distinct().size == randomNumbers.size }
    }

    @Test
    fun whenRandomNonRepeatingNumbersWithSequence_thenResultsAreUnique() {
        val randomNumbers = generateSequence { Random.nextInt(1, 11) }.distinct().take(5).toList()
        assertTrue { randomNumbers.distinct().size == randomNumbers.size }
    }

}