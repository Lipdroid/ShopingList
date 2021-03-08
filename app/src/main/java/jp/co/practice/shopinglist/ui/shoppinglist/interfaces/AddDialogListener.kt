package jp.co.practice.shopinglist.ui.shoppinglist.interfaces

import jp.co.practice.shopinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}