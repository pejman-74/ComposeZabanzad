package com.composezabanzad.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.composezabanzad.data.model.Proverb
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class ProverbDaoTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }


    /**
     * when call getProverb if that exist should return it
     * */
    @Test
    fun test1() = runBlockingTest {
        val proverb = Proverb(1, 1, "test", "test", 1)
        appDatabase.proverbDao().insertProverb(proverb)
        assertThat(appDatabase.proverbDao().getProverb(proverb.id)).isEqualTo(proverb)
    }

    /**
     * when call getProverb if that exist should return null
     * */
    @Test
    fun test2() = runBlockingTest {
        assertThat(appDatabase.proverbDao().getProverb(1)).isNull()
    }

    /**
     * when getProverbsCountByLevel called should return count of proverbs
     * */
    @Test
    fun test3() = runBlockingTest {
        val proverb = Proverb(1, 1, "test", "test", 1)
        appDatabase.proverbDao().insertProverb(proverb)
        assertThat(appDatabase.proverbDao().getProverbsCountByLevel(1)).isEqualTo(1)
    }

    /**
     * when getProverbsCountByLevel called with wrong level is should return 0
     * */
    @Test
    fun test4() = runBlockingTest {
        assertThat(appDatabase.proverbDao().getProverbsCountByLevel(1)).isEqualTo(0)
    }
}