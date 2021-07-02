package com.composezabanzad.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.composezabanzad.data.model.Proverb
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import javax.inject.Inject
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule
import org.junit.Test

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

    @After
    fun tearDown() {
    }

    /**
     * test insert and read
     * */
    @Test
    fun test1() = runBlockingTest {
        val proverb = Proverb(1, 1, "test", "test", 1)
        appDatabase.proverbDao().insertProverb(proverb)
        assertThat(appDatabase.proverbDao().getProverb(proverb.id)).isEqualTo(proverb)
    }
}