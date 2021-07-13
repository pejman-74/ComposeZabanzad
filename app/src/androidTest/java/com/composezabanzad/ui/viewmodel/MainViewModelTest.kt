package com.composezabanzad.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.composezabanzad.data.database.AppDatabase
import com.composezabanzad.data.datastore.AppDatastore
import com.composezabanzad.data.model.Proverb
import com.composezabanzad.data.repository.MainRepository
import com.composezabanzad.util.BackgroundMusicPlayer
import com.composezabanzad.util.FakeBackgroundMusicPlayerImpl
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule

    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("main")
    lateinit var mainDispatcher: CoroutineDispatcher

    @Inject
    @Named("io")
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var backgroundMusicPlayer: BackgroundMusicPlayer

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var coroutineScope: CoroutineScope

    @Inject
    lateinit var appDatastore: AppDatastore

    private val testCoroutineScope get() = coroutineScope as TestCoroutineScope

    lateinit var mainViewModel: MainViewModel

    private val proverb = Proverb(1, 1, "test", "test", 1)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        mainViewModel =
            MainViewModel(mainDispatcher, ioDispatcher, mainRepository, backgroundMusicPlayer)
    }

    @After
    fun tearDown() {
        runBlockingTest {
            appDatastore.clearDataStore()
        }
        testCoroutineScope.cancel()
        testCoroutineScope.cleanupTestCoroutines()
    }

    /**
     * when playBackgroundMusic called should background music playing.
     * */
    @Test
    fun test1() = runBlockingTest {
        mainViewModel.playBackgroundMusic(1)
        val isPlaying = (backgroundMusicPlayer as FakeBackgroundMusicPlayerImpl).isPlaying
        assertThat(isPlaying).isTrue()
    }

    /**
     * when stopBackgroundMusic called should background music stop.
     * */
    @Test
    fun test2() = runBlockingTest {
        mainViewModel.playBackgroundMusic(1)
        mainViewModel.stopBackgroundMusic()
        val isPlaying = (backgroundMusicPlayer as FakeBackgroundMusicPlayerImpl).isPlaying
        assertThat(isPlaying).isFalse()
    }

    /**
     * when getProverb called should return saved proverb.
     * */
    @Test
    fun test3() = runBlockingTest {
        appDatabase.proverbDao().insertProverb(proverb)
        mainViewModel.getProverb(1)
        assertThat(mainViewModel.proverb.value).isEqualTo(proverb)
    }

    /**
     * when getProverb with wrong id called should return null.
     * */
    @Test
    fun test4() = runBlockingTest {
        mainViewModel.getProverb(2)
        assertThat(mainViewModel.proverb.value).isNull()
    }

    /**
     * when getAllProverbsCount called should return saved proverb count.
     * */
    @Test
    fun test5() = runBlockingTest {
        appDatabase.proverbDao().insertProverb(proverb)
        mainViewModel.getProverbsCountByLevel(1)
        assertThat(mainViewModel.proverbsCountByLevel.value).isEqualTo(1)
    }

    /**
     * when getAllProverbsCount with wrong level called should return 0.
     * */
    @Test
    fun test6() = runBlockingTest {
        mainViewModel.getProverbsCountByLevel(10)
        assertThat(mainViewModel.proverbsCountByLevel.value).isEqualTo(0)
    }


    /**
     * when currentLevel for first time called should return 0.
     * */
    @Test
    fun test7() = runBlockingTest {
        assertThat(mainViewModel.currentLevel.first()).isEqualTo(0)
    }

    /**
     * when currentLevel called should return current level.
     * */
    @Test
    fun test8() = runBlockingTest {
        mainViewModel.setCurrentLevel(1)
        assertThat(mainViewModel.currentLevel.first()).isEqualTo(1)
    }

    /**
     * when currentStep for first time called should return 0.
     * */
    @Test
    fun test9() = runBlockingTest {
        assertThat(mainViewModel.currentStep.first()).isEqualTo(0)
    }

    /**
     * when currentStep called should return current step.
     * */
    @Test
    fun test10() = runBlockingTest {
        mainViewModel.setCurrentStep(1)
        assertThat(mainViewModel.currentStep.first()).isEqualTo(1)
    }

    /**
     * when lastStepSolvedWords for first time called should return empty list.
     * */
    @Test
    fun test11() = runBlockingTest {
        assertThat(mainViewModel.lastStepSolvedWords.first()).isEmpty()
    }

    /**
     * when lastStepSolvedWords called should return solved list.
     * */
    @Test
    fun test12() = runBlockingTest {
        mainViewModel.addSolvedWord("meet")
        assertThat(mainViewModel.lastStepSolvedWords.first()).contains("meet")
    }

    /**
     * when clearSolvedWords called should clear all solved list.
     * */
    @Test
    fun test13() = runBlockingTest {
        mainViewModel.addSolvedWord("meet")
        assertThat(mainViewModel.lastStepSolvedWords.first()).isEqualTo(listOf("meet"))
        mainViewModel.clearSolvedWords()
        assertThat(mainViewModel.lastStepSolvedWords.first()).isEmpty()
    }
}