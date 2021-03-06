package it.mem.marvel.data.model.paging.characters.characterdetails


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io
import it.mem.marvel.data.model.api.MarvelAPI
import it.mem.marvel.data.model.entity.Comics
import it.mem.marvel.data.model.paging.characters.infobyid.SeriesByCharactersDataSourceFactory

class SeriesByCharactersViewModel :ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    fun getSeriesList(id:Int):Observable<PagedList<Comics>> {


        val pageSize = 20

        val seriesByCharactersFactory = SeriesByCharactersDataSourceFactory(compositeDisposable, MarvelAPI.getService(), id)

        Log.d("Marvel", "$id")

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()

        return RxPagedListBuilder(seriesByCharactersFactory, config)
                .setFetchScheduler(io())
                .buildObservable()


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}