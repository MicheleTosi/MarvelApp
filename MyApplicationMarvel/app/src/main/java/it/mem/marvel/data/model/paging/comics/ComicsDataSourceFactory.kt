package it.mem.marvel.data.model.paging.comics

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import it.mem.marvel.data.model.api.MarvelAPI
import it.mem.marvel.data.model.entity.Comics

class ComicsDataSourceFactory (private val compositeDisposable: CompositeDisposable, private val marvelAPI: MarvelAPI):
    DataSource.Factory<Int, Comics>() {

    override fun create(): DataSource<Int, Comics> {
        return ComicsDataSource(marvelAPI, compositeDisposable)
    }

}