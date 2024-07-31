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

    class FieldProperty<R, T>(
        private val initializer: (R) -> T
    ) {
        private val map = WeakHashMap<R, T>()

        operator fun getValue(thisRef: R, property: KProperty<*>): T {
            return map[thisRef] ?: setValue(thisRef, property, initializer(thisRef))
        }

        operator fun setValue(thisRef: R, property: KProperty<*>, value: T): T {
            map[thisRef] = value
            return value
        }
    }

    var Int.customField: String by FieldProperty { "$it" }
    var String.customField: Int by FieldProperty { it.length }
    var Double.customField: Boolean by FieldProperty { it > 0.0 }

    @Test
    fun `test using field property`() {
        val x = 0
        println(x.customField)  // Output: "0"
        assertEquals("0", x.customField)

        x.customField = "Baeldung"
        assertEquals("Baeldung", x.customField)

        val str = "Hello"
        assertEquals(5, str.customField)

        val d = 3.14
        assertEquals(true, d.customField)
    }
}
