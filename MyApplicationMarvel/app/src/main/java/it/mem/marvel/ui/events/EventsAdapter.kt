package it.mem.marvel.ui.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.mem.marvel.data.model.entity.Events
import it.mem.marvel.databinding.ItemComicsBinding
import it.mem.marvel.extensions.load

class EventsAdapter: PagedListAdapter<Events, EventsAdapter.VH>(eventDiff) {

    private lateinit var binding: ItemComicsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater=LayoutInflater.from(parent.context)
        binding= ItemComicsBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val event = getItem(position)
        holder.txtName.text=event?.title
        holder.imgThumbnail.load("${event?.thumbnail?.path}.${event?.thumbnail?.extension}")

    }

    class VH(binding: ItemComicsBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgThumbnail = binding.imgThumbnail
        val txtName = binding.txtName

    }

    companion object {
        val eventDiff = object: DiffUtil.ItemCallback<Events>() {
            override fun areItemsTheSame(old: Events, new: Events): Boolean {
                return old.id == new.id

            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(old: Events, new: Events): Boolean {
                return old == new
            }

        }
    }
}