package jp.co.practice.shopinglist.ui.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.practice.shopinglist.R
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem
import jp.co.practice.shopinglist.ui.shoppinglist.viewModels.ShoppingViewModel

class ShoppiingAdapter(
    var itemList: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppiingAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell_shop_items, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.findViewById<TextView>(R.id.tv_item_name).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tv_amount).text = currentItem.amount.toString()
        holder.itemView.findViewById<ImageView>(R.id.btn_delete).setOnClickListener {
            viewModel.delete(currentItem)
        }
        holder.itemView.findViewById<ImageView>(R.id.btn_plus).setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }
        holder.itemView.findViewById<ImageView>(R.id.btn_minus).setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}