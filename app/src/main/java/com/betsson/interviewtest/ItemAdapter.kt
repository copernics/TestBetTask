package com.betsson.interviewtest

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.betsson.interviewtest.model.Bet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target

class ItemAdapter(private var bets: List<Bet>) :
    RecyclerView.Adapter<ItemAdapter.BetViewHolder>() {

    class BetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val betType: TextView = view.findViewById(R.id.tvBetType)
        val odds: TextView = view.findViewById(R.id.tvOdds)
        val imageView: ImageView = view.findViewById(R.id.ivBetImage)
        val sellIn: TextView = view.findViewById(R.id.tvSellIn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bet, parent, false)
        return BetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BetViewHolder, position: Int) {
        val bet = bets[position]
        holder.betType.text = bet.type.displayName
        holder.odds.text = holder.itemView.context.getString(R.string.bet_item_odds_text, "${bet.odds.value}")
        holder.sellIn.text = holder.itemView.context.getString(R.string.bet_item_seel_in_text, "${bet.sellIn.value}")

        Log.d("BetAdapter", "Loading image for bet: ${bet.image.value}")
        Glide.with(holder.itemView)
            .load(bet.image.value)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .listener(object : com.bumptech.glide.request.RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                   Log.d("BetAdapter", "Image loading failed: ${e?.message}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("BetAdapter", "Image loading succeed for bet: ${bet.image.value}")
                   return false
                }

            })
            .into(holder.imageView)
    }

    override fun getItemCount() = bets.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newBets: List<Bet>) {
        bets = newBets

        //Here we should use DiffUtil to update only changed items
        notifyDataSetChanged()
    }
}
