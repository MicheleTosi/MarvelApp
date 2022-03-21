package it.mem.marvel.data.model.paging.characters.characterdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.mem.marvel.databinding.ItemComicsBinding
import it.mem.marvel.data.model.entity.Comics
import it.mem.marvel.extensions.load

class ComicsByCharactersAdapter : PagedListAdapter<Comics, ComicsByCharactersAdapter.VH>(characterDiff) {

    private lateinit var binding: ItemComicsBinding
    var onItemClick: ((Comics) -> Unit)? = null
    val comics= mutableListOf<Comics>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater=LayoutInflater.from(parent.context)
        binding= ItemComicsBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val comic: Comics? =getItem(position)
        if (comic != null) {
            comics.add(position, comic)
        }

        holder.txtName.text = comic?.title
        holder.imgThumbnail.load("${comic?.thumbnail?.path}.${comic?.thumbnail?.extension}")

    }


    inner class VH(binding: ItemComicsBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgThumbnail = binding.imgThumbnail
        val txtName = binding.txtName

        init {
            binding.root.setOnClickListener {

                onItemClick?.invoke(comics[adapterPosition])
            }
        }

    }

    companion object {
        val characterDiff = object: DiffUtil.ItemCallback<Comics>() {
            override fun areItemsTheSame(old: Comics, new: Comics): Boolean {
                return old.id == new.id

            }

            override fun areContentsTheSame(old: Comics, new: Comics): Boolean {
                return old == new
            }

        }
    }
}