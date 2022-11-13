package com.fansymasters.shoppinglist.list.createitem.usecase

import app.cash.turbine.test
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.domain.FancyError
import com.fansymasters.shoppinglist.domain.ProcessingState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class CreateItemUseCaseTest {

    private val mockedListApi: ListsApi = mockk(relaxed = true)
    private val useCase get() = CreateItemUseCase(mockedListApi)
    private val item = CreateListItemDto(
        name = "name",
        qty = 1,
        sortNo = 200,
        unit = "szt",
        category = "VegAndFruits",
        finished = false
    )

    private val responseItem = ListItemDto(
        id = 1,
        name = "name",
        qty = 1,
        sortNo = 200,
        unit = "szt",
        category = "VegAndFruits",
        finished = false
    )

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
    fun `on create item should return Success`() = runTest {
        // give
        coEvery { mockedListApi.createItem(0, item) } returns responseItem
        val expected =
            listOf(
                ProcessingState.Idle,
                ProcessingState.Processing,
                ProcessingState.Success(Unit)
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.createItem("name", 0)

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }

    @Test
    fun `on create item should return Error`() = runTest {
        // give
        coEvery { mockedListApi.createItem(any(), any()) } throws IllegalStateException("")
        val expected =
            listOf(
                ProcessingState.Idle,
                ProcessingState.Processing,
                ProcessingState.Error(FancyError.Unknown)
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.createItem("name", 0)

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }
}