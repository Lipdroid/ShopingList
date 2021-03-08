package jp.co.practice.shopinglist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jp.co.practice.shopinglist.R
import jp.co.practice.shopinglist.data.db.AppDatabase
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem
import jp.co.practice.shopinglist.data.db.repositories.ShoppingRepository
import jp.co.practice.shopinglist.ui.shoppinglist.adapters.ShoppiingAdapter
import jp.co.practice.shopinglist.ui.shoppinglist.custom.AddShoppingItemDialog
import jp.co.practice.shopinglist.ui.shoppinglist.factory.ShoppingFactory
import jp.co.practice.shopinglist.ui.shoppinglist.interfaces.AddDialogListener
import jp.co.practice.shopinglist.ui.shoppinglist.viewModels.ShoppingViewModel
import kotlinx.coroutines.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlin.system.measureTimeMillis

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ShoppingFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppiingAdapter(listOf(), viewModel)
        var rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllItems().observe(this, Observer {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })

        findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }

    private fun checkingCoroutines(){
        /**
         * -- GlobalScope - it runs until the app is running,app close, it finished
         * -- lifecycleScope - it runs until the activity is running,activity close, it finished
         * -- viewModelScope - it runs until the viewModel is running,viewModel close, it finished
         * -- Scope used a dispatcher which determines the thread and returs a job
         *      -- Dispatchers.Main is for running in main thread for ui update
         *      -- Dispatchers.IO is for network calling
         *      -- Dispatchers.Default is for long running task like sort a list with 100000 elemments
         *      -- Dispatchers.Unconfined is not confined in a specific thread,bt wii stay in thee same thread where it started
         * -- Only suspend function can run inside a coroutine
         * -- coroutines can be suspended,delayed,stoped
         * -- can change thread withContext(Dispatchers.Main/IO/Default/unconfined)
         * -- runblocking is also a coroutine which actually a coroutine bt act as main thread to run suspend function
         * -- job.join() will wait until the co routienes ended
         * -- withTimeout(5000){} will end the coroutine withiin 5 sec
         * -- async {  }.await same is job,join(), which is not a job,its returnd differed
         * -- measureTimeMillis {  } to check run time
         * */
    }

    private fun checkigSomefunction(){

    }


}