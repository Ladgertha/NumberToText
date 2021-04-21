package ru.ladgertha

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SingleNumberConverterTest {
    private val numberConverter = NumberConverter()

    private val answers = listOf("ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")

    @Test
    fun `convertNumberToString with int value returns correct value`() {
        for (index in answers.indices) {
            assertEquals(answers[index], numberConverter.convertNumberToString(index))
        }
    }

    @Test
    fun `convertNumberToString with long value returns correct value`() {
        for (index in answers.indices) {
            assertEquals(answers[index], numberConverter.convertNumberToString(index.toLong()))
        }
    }
}