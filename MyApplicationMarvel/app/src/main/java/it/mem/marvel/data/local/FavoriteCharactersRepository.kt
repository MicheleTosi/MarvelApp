package it.mem.marvel.data.local

import androidx.lifecycle.LiveData


class FavoriteCharactersRepository (favoriteCharactersDao: FavoriteCharactersDao){

    val getAllCharacters:LiveData<List<FavoriteCharacters>> = favoriteCharactersDao.getAllCharacters()

}