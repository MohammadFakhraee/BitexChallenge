package ir.mohammadhf.bitexchallenge.feature.futures

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.bitexchallenge.data.model.TradeUi
import ir.mohammadhf.bitexchallenge.databinding.ItemBuyerBinding
import ir.mohammadhf.bitexchallenge.databinding.ItemSellerBinding
import javax.inject.Inject

class TradeListAdapter constructor(private val type: Int) :
    ListAdapter<TradeUi, RecyclerView.ViewHolder>(TradeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (type == 1)
            BuyListViewHolder(
                ItemBuyerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        else
            SellListViewHolder(
                ItemSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BuyListViewHolder) holder.onBind(getItem(position))
        else if (holder is SellListViewHolder) holder.onBind(getItem(position))
    }

    inner class BuyListViewHolder(
        private val itemBuyerBinding: ItemBuyerBinding
    ) : RecyclerView.ViewHolder(itemBuyerBinding.root) {

        fun onBind(tradeUi: TradeUi) {
            itemBuyerBinding.apply {
                priceText.text = tradeUi.price
                amountText.text = tradeUi.amount.toString()
            }
        }
    }

    inner class SellListViewHolder(
        private val itemSellerBinding: ItemSellerBinding
    ) : RecyclerView.ViewHolder(itemSellerBinding.root) {

        fun onBind(tradeUi: TradeUi) {
            itemSellerBinding.apply {
                priceText.text = tradeUi.price
                amountText.text = tradeUi.amount.toString()
            }
        }
    }
}

class TradeDiffUtil() : DiffUtil.ItemCallback<TradeUi>() {
    override fun areItemsTheSame(oldItem: TradeUi, newItem: TradeUi): Boolean =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: TradeUi, newItem: TradeUi): Boolean =
        oldItem == newItem
}