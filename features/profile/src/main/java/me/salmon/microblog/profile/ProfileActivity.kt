package me.salmon.microblog.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.blog.BlogActivity
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.components.BlogNavigation
import me.salmon.microblog.profile.databinding.ProfileActivityBinding
import me.salmon.microblog.profile.ui.post.PostFragment
import me.salmon.microblog.profile.ui.post.ProfileFragment

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity(), BlogNavigation {

    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val author: Author = intent.getParcelableExtra(Constants.authorExtra)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.post_container, PostFragment.newInstance(author))
                .commitNow()
            supportFragmentManager.beginTransaction()
                .replace(R.id.author_container, ProfileFragment.newInstance(author))
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun blogActivityClass() = BlogActivity::class.java.name

}