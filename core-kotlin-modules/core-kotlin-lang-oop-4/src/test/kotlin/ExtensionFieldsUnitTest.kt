import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExtensionFieldsUnitTest {

    // using Extension Properties
    val String.firstChar: Char
        get() = this[0]

    @Test
    fun `test using extension properties`() {
        val word = "Baeldung"
        assertEquals("B", word.firstChar.toString())
    }

    @Target(AnnotationTarget.PROPERTY)
    annotation class CustomField

    // using anotation
    class Foo(val id: Int) {
        @CustomField
        var defaultUserName: String = "Hangga"

        var extraField: String = "Default"
    }

    @Test
    fun `test using anotation`() {
        val foo = Foo(1)
        assertEquals("Hangga", foo.defaultUserName)
    }

    fun Foo.someFunction() : String {
         return "Foo"
    }

    @Test
    fun `test using extension function`(){
        val foo = Foo(0)
        assertEquals("Foo", foo.someFunction())
    }

    // var Foo.someProperty: Int = 20 // this not allowed

    val somePropertyStorage = mutableMapOf<Int, Int>()

    var Foo.someProperty: Int
        get() = somePropertyStorage[this.id] ?: 0
        set(value) {
            somePropertyStorage[this.id] = value
        }

    @Test
    fun `test using custom backing field`() {
        val foo = Foo(0)
        foo.someProperty = 20
        assertEquals(20, foo.someProperty)
    }

    // Using Data Class Copy Method
//    data class Bar(val id: Int, var extraField: String = "Default")

    @Test
    fun `using data class copy method`() {
        val foo = Foo(0)
        assertEquals("Default", foo.extraField)

        foo.extraField = "Not default"
        assertEquals("Not default", foo.extraField)
    }
}