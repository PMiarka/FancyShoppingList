@file:OptIn(ExperimentalCoroutinesApi::class)

package com.fansymasters.shoppinglist.list.details.usecase

import app.cash.turbine.test
import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.domain.FancyError
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FetchListDetailsUseCaseTest {

    private val details = ListDetailsLocalDto("", 1, "", listOf(), listOf())
    private val mockedDetailsRepository: ListDetailsRepository = mockk(relaxed = true) {
        every { localState } returns MutableStateFlow(details)
    }
    private val useCase get() = FetchListDetailsUseCase(mockedDetailsRepository)

    @Test
    fun `on init should return idle`() = runTest {
        // give
        val expected = FetchListDetailsState(ProcessingState.Idle, details)

        // when
        useCase.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `on fetch list details should return Success`() = runTest {
        // give
        coEvery { mockedDetailsRepository.fetchListItems(any()) } returns ApiResult.Success(Unit)
        val expected =
            listOf(
                FetchListDetailsState(ProcessingState.Idle, details),
                FetchListDetailsState(ProcessingState.Processing, details),
                FetchListDetailsState(ProcessingState.Success(Unit), details),
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
        coEvery { mockedDetailsRepository.fetchListItems(any()) } throws IllegalStateException("")
        val expected =
            listOf(
                FetchListDetailsState(ProcessingState.Idle, details),
                FetchListDetailsState(ProcessingState.Processing, details),
                FetchListDetailsState(ProcessingState.Error(FancyError.Unknown), details)
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