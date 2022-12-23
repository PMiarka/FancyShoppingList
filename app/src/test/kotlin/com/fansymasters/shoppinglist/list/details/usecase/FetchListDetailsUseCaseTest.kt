@file:OptIn(ExperimentalCoroutinesApi::class)

package com.fansymasters.shoppinglist.list.details.usecase

import app.cash.turbine.test
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import listDetails
import org.junit.Test

internal class FetchListDetailsUseCaseTest {

    private val mockedDetailsRepository: ListDetailsRepository = mockk(relaxed = true)
    private val localStorageReader: ListDetailsLocalStorageReader = mockk()
    { every { localState } returns MutableStateFlow(listDetails) }

    private val useCase get() = FetchListDetailsUseCase(mockedDetailsRepository, localStorageReader)

    @Test
    fun `on init should return idle`() = runTest {
        // give
        val expected = FetchListDetailsState(CommonProcessingState.Idle, listDetails)

        // when
        useCase.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `on fetch list details should return Success`() = runTest {
        // give
        val expected =
            listOf(
                FetchListDetailsState(CommonProcessingState.Idle, listDetails),
                FetchListDetailsState(CommonProcessingState.Processing, listDetails),
                FetchListDetailsState(CommonProcessingState.Idle, listDetails),
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
                FetchListDetailsState(CommonProcessingState.Idle, listDetails),
                FetchListDetailsState(CommonProcessingState.Processing, listDetails),
                FetchListDetailsState(CommonProcessingState.Error(Throwable()), listDetails)
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