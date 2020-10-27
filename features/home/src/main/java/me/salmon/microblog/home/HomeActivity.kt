package me.salmon.microblog.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.home.databinding.HomeActivityBinding
import me.salmon.microblog.home.ui.authors.AuthorsFragment
import me.salmon.microblog.navigation.components.ProfileNavigation
import me.salmon.microblog.profile.ProfileActivity

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ProfileNavigation {

    lateinit var binding: HomeActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthorsFragment.newInstance())
                .commitNow()
        }
    }

    override fun profileActivityClass(): String = ProfileActivity::class.java.name
}