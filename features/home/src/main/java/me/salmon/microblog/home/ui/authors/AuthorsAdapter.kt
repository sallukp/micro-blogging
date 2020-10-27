package me.salmon.microblog.home.ui.authors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import me.salmon.microblog.home.databinding.AuthorRowBinding
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Navigator

class AuthorsAdapter(val navigator: Navigator, val glideRequests: RequestManager):
    RecyclerView.Adapter<AuthorsAdapter.AuthorsViewHolders>() {

    var authorList: List<Author> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsViewHolders {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AuthorRowBinding.inflate(inflater, parent, false)
        return AuthorsViewHolders(binding)
    }

    override fun getItemCount(): Int = authorList.size

    override fun onBindViewHolder(holder: AuthorsViewHolders, position: Int)
            = holder.onBind(position, authorList[position])



    inner class AuthorsViewHolders(private val binding: AuthorRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int, author: Author) {
            binding.authorName.text = author.name
            binding.avatarView.setAvatar(author.id, author.getFirstLetters(),  author.avatarUrl, glideRequests )
            binding.userName.text = "@${author.userName}"
            binding.mapButton.setOnClickListener {
                navigator.navigateTo(Navigator.Feature.MAP, author)
            }
            binding.root.setOnClickListener {
                navigator.navigate(Navigator.Feature.PROFILE, author)
            }
        }
    }
}