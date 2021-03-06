package it.mem.marvel.data.model.paging.events

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import it.mem.marvel.data.model.api.MarvelAPI
import it.mem.marvel.data.model.entity.Events

class EventsDataSourceFactory (private val compositeDisposable: CompositeDisposable, private val marvelAPI: MarvelAPI):
    DataSource.Factory<Int, Events>() {

    override fun create(): DataSource<Int, Events> {
        return EventsDataSource(marvelAPI, compositeDisposable)
    }

}