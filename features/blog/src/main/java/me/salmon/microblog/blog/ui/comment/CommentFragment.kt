package me.salmon.microblog.blog.ui.comment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.models.Comment
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.utils.DataState
import me.salmon.microblog.utils.views.RecyclerViewFragment
import javax.inject.Inject

@AndroidEntryPoint
class CommentFragment : RecyclerViewFragment<CommentsAdapter.CommentsViewHolder>() {

    private val viewModel: CommentViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager

    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun newInstance(post: Post?): CommentFragment {
            val fragment = CommentFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putParcelable(Constants.postExtra, post)
            return fragment
        }
    }

    override fun setStateEvent() {
        var post: Post? = arguments?.getParcelable(Constants.postExtra)
        post?.let {
            viewModel.setStateEvent(CommentViewModel.CommentStateEvent.GetCommentsEvent(it.id))
        }
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(activity as AppCompatActivity, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Comment>> -> {
                    showLoading(false)
                    (adapter as CommentsAdapter).commentsList = dataState.data
                    adapter.notifyDataSetChanged()
                }
                is DataState.Loading -> {
                    showLoading(true)
                }
                is DataState.Error -> {
                    showLoading(false)
                    showError()
                }
            }
        })
    }

    override fun createAdapter() = CommentsAdapter(navigator, glideRequests)

}