package me.salmon.microblog.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.salmon.microblog.home.ui.authors.AuthorsFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthorsFragment.newInstance())
                .commitNow()
        }
    }
}