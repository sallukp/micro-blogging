package me.salmon.microblog.blog.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import me.salmon.microblog.blog.databinding.SingleCommentRowBinding
import me.salmon.microblog.models.Comment
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.utils.extensions.DateUtils.formatDate
import me.salmon.microblog.utils.extensions.DateUtils.getFirstLetters

class CommentsAdapter(val navigator: Navigator, val glideRequest: RequestManager):
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var commentsList = listOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = SingleCommentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun getItemCount() = commentsList.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.onBind(commentsList[position], position)
    }


    inner class CommentsViewHolder(val binding: SingleCommentRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(comment: Comment, position: Int) {
            binding.userNameText.text = "@${comment.userName}"
            binding.bodyText.text = comment.body
            binding.timeText.text = comment.date.formatDate()
            binding.avatarView.setAvatar(comment.id, comment.userName.getFirstLetters(),
                comment.avatarUrl, glideRequest)
        }
    }
}