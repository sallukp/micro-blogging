package me.salmon.microblog.profile.ui.post

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.profile.R
import me.salmon.microblog.profile.databinding.SinglePostRowBinding
import me.salmon.microblog.utils.extensions.DateUtils.formatDate

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
            post.imageUrl?.let {
                glideRequests.load(post.imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_place_holder))
                    .into(binding.postImageView)
            }
            binding.bodyText.text = post.body
            binding.bodyText.movementMethod = ScrollingMovementMethod()
            binding.titleText.text = post.title
            binding.timeText.text = post.date.formatDate()
            binding.root.setOnClickListener {
                navigator.navigate(Navigator.Feature.BLOG, post)
            }
        }
    }
}