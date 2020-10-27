package me.salmon.microblog.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.salmon.microblog.profile.ui.post.PostFragment

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PostFragment.newInstance())
                .commitNow()
        }
    }
}