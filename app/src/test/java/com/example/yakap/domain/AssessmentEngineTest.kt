package com.example.yakap.domain

import com.example.yakap.data.models.QuizType
import org.junit.Assert.assertEquals
import org.junit.Test

class AssessmentEngineTest {

    @Test
    fun testGAD7Interpretation_Minimal() {
        val result = AssessmentEngine.calculateResult(QuizType.GAD7, 3)
        assertEquals("Minimal anxiety", result)
    }

    @Test
    fun testGAD7Interpretation_Mild() {
        val result = AssessmentEngine.calculateResult(QuizType.GAD7, 7)
        assertEquals("Mild anxiety", result)
    }

    @Test
    fun testGAD7Interpretation_Moderate() {
        val result = AssessmentEngine.calculateResult(QuizType.GAD7, 12)
        assertEquals("Moderate anxiety", result)
    }

    @Test
    fun testGAD7Interpretation_Severe() {
        val result = AssessmentEngine.calculateResult(QuizType.GAD7, 18)
        assertEquals("Severe anxiety", result)
    }
}
