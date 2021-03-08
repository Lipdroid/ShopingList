package jp.co.practice.shopinglist.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    //if item already existed replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)
    @Delete
    suspend fun delete(item: ShoppingItem)
    @Query("SELECT * FROM shopping_items")
    fun getAllItem(): LiveData<List<ShoppingItem>>
}