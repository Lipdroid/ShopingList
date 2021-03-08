package jp.co.practice.shopinglist.appController

import android.app.Application
import jp.co.practice.shopinglist.data.db.AppDatabase
import jp.co.practice.shopinglist.data.db.repositories.ShoppingRepository
import jp.co.practice.shopinglist.ui.shoppinglist.factory.ShoppingFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppController: Application(),KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(app = this@AppController))
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
        bind() from provider { ShoppingFactory(instance()) }

    }
}