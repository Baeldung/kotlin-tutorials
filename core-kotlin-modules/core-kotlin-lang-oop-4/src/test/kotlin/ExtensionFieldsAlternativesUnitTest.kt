import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface VehicleInterface {
    var speed: Int
}

class ExtensionFieldsAlternativesUnitTest {

    class Vehicle(override var speed: Int) : VehicleInterface

    class Car(val numberOfDoors: Int, vehicle: Vehicle) {
        var speed: Int = vehicle.speed
    }

    class Truck(val numberOfDoors: Int, val vehicle: Vehicle) : VehicleInterface {
        override var speed: Int
            get() = vehicle.speed
            set(value) {
                vehicle.speed = value
            }
    }

    @Test
    fun `test using inheritance`() {
        val vehicle = Vehicle(120)
        assertEquals(120, vehicle.speed)

        val car = Car(4, vehicle)

        assertEquals(4, car.numberOfDoors)
        assertEquals(120, car.speed)

        val truck = Truck(2, vehicle)
        assertEquals(2,  truck.numberOfDoors)
        assertEquals(120, truck.speed)
    }
}
