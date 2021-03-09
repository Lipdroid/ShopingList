package jp.co.practice.shopinglist.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import jp.co.practice.shopinglist.data.db.AppDatabase
import jp.co.practice.shopinglist.data.db.dao.ShoppingDao
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem
import jp.co.practice.shopinglist.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {
    //iinitialize
    private lateinit var db: AppDatabase
    private lateinit var dao: ShoppingDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.getShoppingDao()
    }

    @Test
    fun insertItemTest() = runBlockingTest {
        val item = ShoppingItem("Banana", 20, 1)
        this@ShoppingDaoTest.dao.upsert(item)
        val allItems = this@ShoppingDaoTest.dao.getAllItem().getOrAwaitValue()
        assertThat(allItems).contains(item)
    }

    @Test
    fun deleteItemTest() = runBlockingTest {
        val item = ShoppingItem("Holo", 20, 1)
        this@ShoppingDaoTest.dao.upsert(item)
        this@ShoppingDaoTest.dao.delete(item)
        val allItems = dao.getAllItem().getOrAwaitValue()
        assertThat(allItems).doesNotContain(item)
    }

    @Test
    fun getAllItemsInsertTest() = runBlockingTest {
        val item1 = ShoppingItem("Banana", 20, 1)
        val item2 = ShoppingItem("Holo", 20, 2)
        this@ShoppingDaoTest.dao.upsert(item1)
        this@ShoppingDaoTest.dao.upsert(item2)
        val allItems = dao.getAllItem().getOrAwaitValue()
        assertThat(allItems).hasSize(2)
    }
    @After
    fun tearDown() {
        db.close()
    }
}