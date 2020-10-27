package me.salmon.microblog.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.blog.BlogActivity
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.components.BlogNavigation
import me.salmon.microblog.profile.databinding.ProfileActivityBinding
import me.salmon.microblog.profile.ui.post.PostFragment
import me.salmon.microblog.profile.ui.profile.ProfileFragment

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity(), BlogNavigation {

    private lateinit var author: Author
    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        author = intent.getParcelableExtra(Constants.authorExtra)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = ""
        supportActionBar?.title = ""
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.post_container, PostFragment.newInstance(author))
                .commitNow()
            supportFragmentManager.beginTransaction()
                .replace(R.id.author_container, ProfileFragment.newInstance(author))
                .commitNow()
        }
        handleToolbarScroll()
    }

    private fun handleToolbarScroll() {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout
            .OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                title = ""
                supportActionBar?.title = ""
                binding.toolbarLayout.title = ""
                (supportFragmentManager.findFragmentById(R.id.author_container) as? ProfileFragment)?.let {
                    it.binding.root.visibility = VISIBLE
                }
            } else {
                (supportFragmentManager.findFragmentById(R.id.author_container) as? ProfileFragment)?.let {
                    it.binding.root.visibility = INVISIBLE
                }
                title = author.name
                supportActionBar?.title = author.name
                binding.toolbarLayout.title = author.name
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun blogActivityClass() = BlogActivity::class.java.name

}