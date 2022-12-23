package com.fansymasters.shoppinglist.list.createitem.usecase

import app.cash.turbine.test
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.mapToCategory
import com.fansymasters.shoppinglist.domain.FancyError
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CreateItemUseCaseTest {

    private val mockedRepository: CreateListItemRepository = mockk(relaxed = true)
    private val useCase get() = CreateItemUseCase(mockedRepository)
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
    private val listId = 0

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

        coEvery {
            mockedRepository.createListItem(
                listId = listId,
                name = item.name,
                unit = item.unit,
                quantity = item.qty,
                category = item.category.mapToCategory()
            )
        } returns Unit
        val expected =
            listOf(
                CommonProcessingState.Idle,
                CommonProcessingState.Processing,
                CommonProcessingState.Idle
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.createItem(
                listId = listId,
                name = item.name,
                unit = item.unit,
                quantity = item.qty,
                category = item.category.mapToCategory()
            )

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }

    @Test
    fun `on create item should return Error`() = runTest {
        // give
        coEvery {
            mockedRepository.createListItem(
                listId = listId,
                name = item.name,
                unit = item.unit,
                quantity = item.qty,
                category = item.category.mapToCategory()
            )
        } throws IllegalStateException("")
        val expected =
            listOf(
                ProcessingState.Idle,
                ProcessingState.Processing,
                ProcessingState.Error(FancyError.Unknown)
            )

        val testedUseCase = useCase

        // when-then
        testedUseCase.state.test {
            testedUseCase.createItem(
                listId = listId,
                name = item.name,
                unit = item.unit,
                quantity = item.qty,
                category = item.category.mapToCategory()
            )

            expected.forEach {
                assertEquals(it, awaitItem())
            }
        }
    }
}