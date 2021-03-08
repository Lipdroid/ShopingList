package jp.co.practice.shopinglist.ui.shoppinglist.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jp.co.practice.shopinglist.data.db.repositories.ShoppingRepository
import jp.co.practice.shopinglist.ui.shoppinglist.viewModels.ShoppingViewModel

@Suppress("UNCHECKED_CAST")
class ShoppingFactory(private val repository: ShoppingRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}