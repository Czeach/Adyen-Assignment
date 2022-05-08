package com.adyen.android.assignment.ui.nearbyPlaces.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.models.Result

class NearbyPlacesAdapter(diffCallback: NearbyPlacesDiffCallback):
    ListAdapter<Result, NearbyPlacesAdapter.NearbyPlacesViewHolder>(diffCallback){

    class NearbyPlacesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val country: TextView = itemView.findViewById(R.id.country)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val timeZone: TextView = itemView.findViewById(R.id.timezone)

        @SuppressLint("SetTextI18n")
        fun bind(result: Result) {
            name.text = result.name
            country.text = result.location?.country
            address.text = result.location?.formattedAddress
            timeZone.text = result.timezone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyPlacesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nearby_plces_item, parent, false)

        return NearbyPlacesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NearbyPlacesViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}

object NearbyPlacesDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.fsqId == newItem.fsqId
    }
}