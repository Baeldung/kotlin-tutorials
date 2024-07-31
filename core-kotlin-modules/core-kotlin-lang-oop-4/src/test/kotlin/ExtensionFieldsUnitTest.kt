import org.junit.jupiter.api.Test
import java.util.*
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

class ExtensionFieldsUnitTest {

    fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.capitalize() }

    // val String.firstChar: Char = this[0] // this not allowed

    val String.firstChar: Char
        get() = this[0]

    @Test
    fun `test using extension function and properties`() {
        val word = "baeldung kotlin"

        assertEquals(word[0], word.firstChar)

        assertEquals("Baeldung Kotlin", word.toTitleCase())
    }

    class Foo {
        var name: String = "Foo"
    }

    fun Foo.getName(): String = this.name

    fun Foo.getLength(): Int = this.name.length

    @Test
    fun `test using extension function`() {
        val foo = Foo()

        assertEquals("Foo", foo.getName())
        assertEquals("Foo".length, foo.getLength())
    }

    val Foo.someProperty: Int
        get() = 21

    @Test
    fun `test extension properties using getter`() {
        val foo = Foo()
        assertEquals(21, foo.someProperty)

        // foo.someProperty = 22 // not allowed
    }

    val propertyStorage = mutableMapOf<Foo, Any>()

    var Foo.someIntProperty: Any
        get() = propertyStorage[this] ?: 0
        set(value) { propertyStorage[this] = value }

    var Foo.someStringProperty: Any
        get() = propertyStorage[this] ?: ""
        set(value) { propertyStorage[this] = value }

    @Test
    fun `test using custom backing field`() {
        val foo = Foo()

        foo.someIntProperty = 20
        assertEquals(20, foo.someIntProperty)

        foo.someIntProperty = 11
        assertEquals(11, foo.someIntProperty)

        foo.someStringProperty = "Baeldung"
        assertEquals("Baeldung", foo.someStringProperty)

        foo.someStringProperty = "Kotlin"
        assertEquals("Kotlin", foo.someStringProperty)
    }
}
