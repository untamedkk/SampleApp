package com.test.krishna.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.test.krishna.R
import com.test.krishna.models.Model
import kotlinx.android.synthetic.main.item_delivery_layout.view.*

class DeliveriesAdapter(private val context: Context, private var deliveries: List<Model.Delivery>) : RecyclerView.Adapter<DeliveriesAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setItems(deliveries: List<Model.Delivery>) {
        this.deliveries = deliveries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_delivery_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return deliveries.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        val delivery = deliveries[viewHolder.adapterPosition]
        viewHolder.itemView.item_delivery_description_tv.text = delivery.description
        viewHolder.itemView.item_delivery_location_tv.text = delivery.location.address
        Glide.with(context).load(delivery.imageUrl)
                .apply(glideRequestOption)
                .into(viewHolder.itemView.item_delivery_icon_iv)
    }

    private val glideRequestOption: RequestOptions by lazy {
        RequestOptions()
                .transform(CenterCrop())
                .override(150, 150)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemClickListener.onItemClick(adapterPosition, deliveries[adapterPosition]) }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, delivery: Model.Delivery)
    }
}