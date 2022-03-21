package it.mem.marvel.ui.events

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
import it.mem.marvel.databinding.FragmentEventsBinding

class EventsFragment:Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewModel by lazy {
        ViewModelProvider(this).get(EventsViewModel::class.java)
    }

    private val adapter: EventsAdapter by lazy {
        EventsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding= FragmentEventsBinding.inflate(layoutInflater, container, false)

        val llm = if(resources.configuration.orientation== Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(activity, 2)
        }else{
            GridLayoutManager(activity, 4)
        }
        binding.recyclerEvents.layoutManager = llm
        binding.recyclerEvents.adapter = adapter
        subscribeToList()

        return binding.root
    }

    @SuppressLint("CheckResult")
    private fun subscribeToList() {
        viewModel.eventList
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

