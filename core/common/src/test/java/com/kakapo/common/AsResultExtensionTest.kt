package com.kakapo.common

import android.nfc.FormatException
import com.kakapo.common.result.Result
import com.kakapo.common.result.asResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class AsResultExtensionTest {

    @Test
    fun `test asResult return success`() = runTest {
        val successFlow = flowOf("data")
        val successResult = successFlow.asResult().toList()
        assertThat(successResult.size, `is`(2))
        assertThat(successResult[0], `is`(Result.Loading))
        assertTrue(successResult[1] is Result.Success)
    }

    @Test
    fun `test asResult Extensions return error`() = runTest {
        val errorFlow = flow<String>{
            throw FormatException()
        }
        val errorResult = errorFlow.asResult().toList()
        assertThat(errorResult.size, `is`(2))
        assertThat(errorResult[0], `is`(Result.Loading))
        assertTrue(errorResult[1] is Result.Error)
    }
}