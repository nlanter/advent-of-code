package org.elwaxoro.advent.helpers

import org.elwaxoro.advent.permutations
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test
    fun testPermutations() {
        val abc = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L').permutations()
        println(abc)
        assertEquals(6, abc.size)

        val xyz = listOf(Foo("X"), Foo("Y"), Foo("Z")).permutations()
        println(xyz)
        assertEquals(6, xyz.size)


//        assertThrows<OutOfMemoryError> {
//            (0..10).toList().permutations()
//        }
    }

    private data class Foo(val a: String)
}
