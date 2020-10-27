package me.salmon.microblog.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.blog.ui.comment.CommentFragment
import me.salmon.microblog.blog.ui.post.PostFragment
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Constants

@AndroidEntryPoint
class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blog_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val post: Post? = intent.extras?.getParcelable(Constants.postExtra)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.comments_container, CommentFragment.newInstance(post))
                .commitNow()
            supportFragmentManager.beginTransaction()
                .replace(R.id.post_container, PostFragment.newInstance(post))
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed();
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}