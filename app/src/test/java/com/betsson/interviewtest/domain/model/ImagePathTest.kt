package com.betsson.interviewtest.domain.model

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ImagePathTest {

    @Test
    fun `test valid ImagePath`() {
        val imagePath = ImagePath("path/to/image.jpg")
        assertEquals("path/to/image.jpg", imagePath.value)
    }

    @Test
    fun `test invalid ImagePath with blank value`() {
        assertThrows(IllegalArgumentException::class.java) {
            ImagePath(" ") // Should fail as value is blank
        }
    }
}