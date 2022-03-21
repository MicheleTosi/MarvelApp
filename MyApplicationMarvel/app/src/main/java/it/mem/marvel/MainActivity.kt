package it.mem.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import it.mem.marvel.databinding.ActivityMainBinding
import it.mem.marvel.activities.SecondActivity
import it.mem.marvel.data.local.FavoriteCharactersDao
import it.mem.marvel.data.local.FavoriteCharactersDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var characterDb: FavoriteCharactersDatabase?=null
    private var characterDao: FavoriteCharactersDao?=null

    companion object {
        private var SPLASHSCREEN: Long = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        characterDb= FavoriteCharactersDatabase.getDatabase(this)
        characterDao=characterDb?.FavoriteCharactersDao()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASHSCREEN)

    }
}