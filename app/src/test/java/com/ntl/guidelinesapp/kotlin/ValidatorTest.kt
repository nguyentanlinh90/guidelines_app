package com.ntl.guidelinesapp.kotlin

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {
    @Test
    fun whenInputIsValid() {
        val amount = 100
        val desc = "Some desc random"
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInValid() {
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(false)
    }
}