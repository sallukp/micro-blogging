package me.salmon.microblog.profile.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.profile.databinding.SinglePostRowBinding

class PostsAdapter(val navigator: Navigator, val glideRequests: RequestManager)
    : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var posts = listOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = SinglePostRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(posts[position], position)
    }


    inner class PostViewHolder(private val binding: SinglePostRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Post, position: Int) {
            binding.postView.onBind(post.title, post.body, post.imageUrl, post.date, glideRequests)
            binding.root.setOnClickListener {
                navigator.navigate(Navigator.Feature.BLOG, post)
            }
        }
    }
}