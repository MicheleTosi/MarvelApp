package it.mem.marvel.ui.comics

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import it.mem.marvel.databinding.FragmentComicsBinding

class ComicsFragment:Fragment() {
    private lateinit var binding: FragmentComicsBinding
    private val viewModel: ComicsViewModel by lazy {
        ViewModelProvider(this).get(ComicsViewModel::class.java)
    }

    private val adapter: ComicsAdapter by lazy {
        ComicsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding= FragmentComicsBinding.inflate(layoutInflater, container, false)

        val llm = if(resources.configuration.orientation==Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity, 2)
        }else{
            GridLayoutManager(activity, 4)
        }
        binding.recyclerComics.layoutManager = llm
        binding.recyclerComics.adapter = adapter
        subscribeToList()

        return binding.root
    }

    @SuppressLint("CheckResult")
    private fun subscribeToList() {
        viewModel.comicList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    adapter.submitList(list)
                },
                { e ->
                    Log.e("Marvel", "Error", e)
                }
            )
    }
}

