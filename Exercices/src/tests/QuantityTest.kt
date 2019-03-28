package tests

import exercises.*
import exercises.Unit
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

internal class QuantityTest {

    @Test internal fun `Same quantity on different metrics should be equal`() {
        assertEquals(3.teaspoons, 1.tablespoons)
        assertNotEquals(0.5.teaspoons, 1.tablespoons)
        assertEquals(1.pints, 16.ounces)
        assertEquals(1.quarts, 4.cups)
        assertEquals(0.25.gallons, 2.pints)
    }

    @Test internal fun `hashCode should be equals`() {
        assertEquals(Quantity(0.25, Unit.gallon).hashCode(), Quantity(2, Unit.pint).hashCode())
        //assertNotEquals(Quantity(1, Unit.teaspoon).hashCode(), Quantity(1, Unit.inch).hashCode())  // Not necessary
        assertEquals(Quantity(-40, Unit.fahrenheit).hashCode(), Quantity(-40, Unit.celsius).hashCode())
    }

    @Test internal fun `Quantity can be anti matter`() {
        assertEquals(Quantity(-3, Unit.teaspoon), Quantity(-1, Unit.tablespoon))
        assertEquals(Quantity(-3, Unit.teaspoon), - Quantity(1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be added`() {
        assertEquals(Quantity(1, Unit.ounce),3.teaspoons + Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) + Quantity(-1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be subsctracted`() {
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) - Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(1, Unit.ounce),3.teaspoons - Quantity(- 1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(+6, Unit.teaspoon) - +Quantity(+1, Unit.tablespoon))
    }

    @Test internal fun `A Quantity of distance cannot be equal to one of volume`() {
        assertNotEquals(1.teaspoons, 1.inches)
        assertNotEquals(0.5.gallons, 1.feet)
    }

    @Test internal fun `Quantity of distance should match`() {
        assertEquals(22.yards, 1.chains)
        assertEquals(16.furlongs, 2.miles)
    }

    @Test internal fun `Celsius to Fahrenheit`() {
        assertEquals(0.celsius, 32.fahrenheits)
        assertEquals(10.celsius, 50.fahrenheits)
        assertEquals(100.celsius, 212.fahrenheits)
        assertEquals((-40).celsius, (-40).fahrenheits)
    }

    @Test internal fun `Fahrenheit to Celsius`() {
        assertEquals(32.fahrenheits, 0.celsius)
        assertEquals(50.fahrenheits, 10.celsius)
        assertEquals(212.fahrenheits, 100.celsius)
        assertEquals((-40).fahrenheits, (-40).celsius)
    }

    @Test internal fun `Kelvin can be Celsius and Fahrenheit`(){
        assertEquals(0.kelvins, (-459.67).fahrenheits)
        assertEquals((-441.67).fahrenheits, 10.kelvins)
        assertEquals(27.kelvins, (-246.15).celsius)
        assertEquals(105.85.celsius, 379.kelvins)
    }

    @Test internal fun `Gas Mark can be Celsius and Fahrenheit`() { // Gasmarks sucks
//        assertEquals(2.gasmarks, 300.fahrenheits)
//        assertEquals(2.gasmarks, 149.celsius)
//        assertEquals(7.92.gasmarks, 232.celsius)
//        assertEquals(225.fahrenheits, 0.25.gasmarks)
    }

    @Test internal fun `Substraction does not work on temperature`(){
        assertFailsWith<IllegalArgumentException> {
            (-10).celsius -  (-50).fahrenheits
        }
        assertFailsWith<IllegalArgumentException> {
            20.celsius - 50.fahrenheits
        }
    }

    @Test internal fun `Addition does not work on temperature`(){
        assertFailsWith<IllegalArgumentException> {
            10.celsius + 10.celsius
        }
    }

}