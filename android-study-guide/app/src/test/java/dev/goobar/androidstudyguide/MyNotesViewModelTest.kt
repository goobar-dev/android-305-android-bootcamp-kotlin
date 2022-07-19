package dev.goobar.androidstudyguide

import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import app.cash.turbine.test
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.db.NoteDao
import dev.goobar.androidstudyguide.mynotes.MyNotesViewModel
import dev.goobar.androidstudyguide.mynotes.MyNotesViewModel.UiState
import dev.goobar.androidstudyguide.utils.CoroutinesTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class MyNotesViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val coroutinesDispatcherRule = CoroutinesTestRule()

  private val mainThreadSurrogate = newSingleThreadContext("UI thread")

  @Before
  fun setUp() {
    Dispatchers.setMain(mainThreadSurrogate)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    mainThreadSurrogate.close()
  }

  @Test
  fun `verify initial ViewState`() = runBlocking {
    val noteDao = Mockito.mock(NoteDao::class.java)
    Mockito.`when`(noteDao.getAll()).thenReturn(flowOf(emptyList<Note>()))
    val viewModel = MyNotesViewModel(noteDao)

    assertEquals(viewModel.state.value, MyNotesViewModel.UiState())
  }

  @Test
  fun `verify ViewState on loaded notes`() = runBlocking {
    val noteDao = Mockito.mock(NoteDao::class.java)
    Mockito.`when`(noteDao.getAll()).thenReturn(flowOf(listOf(Note("title", "category", "content"))))
    val viewModel = MyNotesViewModel(noteDao)

    viewModel.state.test {
      assertEquals(awaitItem(), UiState())
      assertEquals(awaitItem(), UiState(listOf(Note("title", "category", "content"))))
    }
  }

}