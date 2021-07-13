package com.composezabanzad.data.datastore


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class AppDatastoreTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    lateinit var appDatastore: AppDatastore

    @Inject
    lateinit var coroutineScope: CoroutineScope

    private val testCoroutineScope get() = coroutineScope as TestCoroutineScope

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
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
     *when currentLevel is null should return 0
     * */
    @Test
    fun test1() = runBlockingTest {
        assertThat(appDatastore.currentLevel.first()).isEqualTo(0)
    }

    /**
     *when currentStep is null should return 0
     * */
    @Test
    fun test2() = runBlockingTest {
        assertThat(appDatastore.currentStep.first()).isEqualTo(0)
    }

    /**
     *check read and write currentLevel
     * */
    @Test
    fun test3() = runBlockingTest {
        appDatastore.setCurrentLevel(1)
        assertThat(appDatastore.currentLevel.first()).isEqualTo(1)
    }

    /**
     *check read and write currentLevel
     * */
    @Test
    fun test4() = runBlockingTest {
        appDatastore.setCurrentStep(1)
        assertThat(appDatastore.currentStep.first()).isEqualTo(1)
    }

    /**
     *when lastStepSolvedWords is empty should return empty list
     * */
    @Test
    fun test5() = runBlockingTest {
        assertThat(appDatastore.lastStepSolvedWords.first().isEmpty()).isTrue()
    }

    /**
     *when added some solved words should return current and all previous words in a list
     * */
    @Test
    fun test6() = runBlockingTest {
        val solvedWord = listOf("chicken", "meat", "egg")
        solvedWord.forEach {
            appDatastore.addSolvedWord(it)
        }
        assertThat(appDatastore.lastStepSolvedWords.first()).isEqualTo(solvedWord)
    }
    /**
     * when clearSolvedWords called should clear all solved list.
     * */
    @Test
    fun test7() = runBlockingTest {
        val solvedWord = listOf("chicken", "meat", "egg")
        solvedWord.forEach {
            appDatastore.addSolvedWord(it)
        }
        assertThat(appDatastore.lastStepSolvedWords.first()).isEqualTo(solvedWord)
        appDatastore.clearSolvedWords()
        assertThat(appDatastore.lastStepSolvedWords.first()).isEmpty()
    }
}