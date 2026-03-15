package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class GameResultControllerTests {

    private lateinit var mockedService: GameResultService
    private lateinit var controller: GameResultController

    @BeforeEach
    fun setup() {
        mockedService = mock(GameResultService::class.java)
        controller = GameResultController(mockedService)
    }

    @Test
    fun test_getGameResult_returnsCorrectResult() {

        val gameResult = GameResult(1, "player1", 20, 10.0)

        `when`(mockedService.getGameResult(1)).thenReturn(gameResult)

        val result = controller.getGameResult(1)

        verify(mockedService).getGameResult(1)
        assertEquals(gameResult, result)
    }

    @Test
    fun test_getAllGameResults_returnsList() {

        val list = listOf(
            GameResult(1, "player1", 20, 10.0),
            GameResult(2, "player2", 15, 12.0)
        )

        `when`(mockedService.getGameResults()).thenReturn(list)

        val result = controller.getAllGameResults()

        verify(mockedService).getGameResults()
        assertEquals(2, result.size)
    }

    @Test
    fun test_addGameResult_callsService() {

        val gameResult = GameResult(1, "player1", 20, 10.0)

        controller.addGameResult(gameResult)

        verify(mockedService).addGameResult(gameResult)
    }

    @Test
    fun test_deleteGameResult_callsService() {

        controller.deleteGameResult(1)

        verify(mockedService).deleteGameResult(1)
    }
}