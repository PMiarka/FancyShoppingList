package com.fansymasters.shoppinglist.list.details.usecase

import app.cash.turbine.test
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.domain.FancyError
import com.fansymasters.shoppinglist.domain.ProcessingState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FetchListDetailsUseCaseTest {

    private val mockedListApi: ListsApi = mockk(relaxed = true)
    private val useCase get() = FetchListDetailsUseCase(mockedListApi)

    @Test
    fun `on init should return idle`() = runTest {
        // give
        val expected = ProcessingState.Idle

        // when
        useCase.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `on fetch list details should return Success`() = runTest {
        // give
        val details = ListDetailsDto("", 1, "", listOf(), listOf())
        coEvery { mockedListApi.fetchListDetails(any()) } returns details
        val expected =
            listOf(
                ProcessingState.Idle,
                ProcessingState.Processing,
                ProcessingState.Success(details)
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.fetchListDetails(0)

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }

    @Test
    fun `on fetch list details should return Error`() = runTest {
        // give
        coEvery { mockedListApi.fetchListDetails(any()) } throws java.lang.IllegalStateException("")
        val expected =
            listOf(
                ProcessingState.Idle,
                ProcessingState.Processing,
                ProcessingState.Error(FancyError.Unknown)
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.fetchListDetails(0)

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }
}