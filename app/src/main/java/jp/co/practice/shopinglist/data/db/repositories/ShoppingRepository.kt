package jp.co.practice.shopinglist.data.db.repositories

import jp.co.practice.shopinglist.data.db.AppDatabase
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem

class ShoppingRepository(private val db: AppDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShopingItem() = db.getShoppingDao().getAllItem()
}