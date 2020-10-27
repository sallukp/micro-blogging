package me.salmon.microblog.profile.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.models.Author
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.utils.DataState
import me.salmon.microblog.utils.views.RecyclerViewFragment
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : RecyclerViewFragment<PostsAdapter.PostViewHolder>() {

    private val viewModel: PostViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager

    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun newInstance( author: Author): Fragment {
            val fragment = PostFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.authorExtra, author.id)
            fragment.arguments = bundle
            return fragment;
        }
    }

    override fun setStateEvent() {
        val bundle = arguments
        val authorId:Int? = bundle?.getInt(Constants.authorExtra)
        authorId?.let {
            val postEvent = PostViewModel.PostStateEvent.GetPostEvent(it)
            viewModel.setStateEvent(postEvent)
        }
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(activity as AppCompatActivity, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Post>> -> {
                    showLoading(false)
                    (adapter as PostsAdapter).posts = dataState.data
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

    override fun createAdapter() = PostsAdapter(navigator, glideRequests)

}