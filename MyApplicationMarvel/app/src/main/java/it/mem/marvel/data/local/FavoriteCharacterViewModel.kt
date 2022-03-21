package it.mem.marvel.data.local

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteCharacterViewModel(application: Application): AndroidViewModel(application) {
    val readAllData:LiveData<List<FavoriteCharacters>>
    private val repository: FavoriteCharactersRepository

    init {
        val favoriteCharactersDao = FavoriteCharactersDatabase.getDatabase(application).FavoriteCharactersDao()
        repository= FavoriteCharactersRepository(favoriteCharactersDao)
        readAllData = repository.getAllCharacters
    }
}