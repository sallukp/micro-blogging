package me.salmon.microblog.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.salmon.microblog.blog.ui.comment.CommentFragment

class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blog_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CommentFragment.newInstance())
                .commitNow()
        }
    }
}