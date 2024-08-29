package unitAndVoidMismatch

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

fun customGreetingVoid(name: String, greeting: (String) -> Void) = greeting(name)

fun customGreetingUnit(name: String, greeting: (String) -> Unit) = greeting(name)

class UnitAndVoidTypeMismatchUnitTest {
    val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun `when calling customGreetingVoid method, then code does not compile`() {
        /* the below code doesn't compile
         ---------------------------------
        customGreetingVoid("Tom Hanks"){
            log.info("Hi $it, how do you do?")
        }

        customGreetingVoid("Kai"){
            log.info("Hello $it")
            null
          }
        */
    }

    @Test
    fun `when calling customGreetingUnit method, then works as expected`() {
        customGreetingUnit("Tom Cruise") {
            log.info("Hi $it, how are you doing?")
        }
    }
}