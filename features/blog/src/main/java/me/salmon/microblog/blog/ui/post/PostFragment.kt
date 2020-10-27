package me.salmon.microblog.blog.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.blog.databinding.PostFragmentBinding
import me.salmon.microblog.models.Post
import me.salmon.microblog.navigation.Constants
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager

    lateinit var binding: PostFragmentBinding

    companion object {
        fun newInstance(post: Post?): PostFragment {
            val fragment = PostFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constants.postExtra, post)
            fragment.arguments = bundle
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        val post: Post? = arguments?.getParcelable(Constants.postExtra)
        post?.let {
            viewModel.setStateEvent(PostViewModel.PostStateEvent.GetPostEvent(it))
        }
    }


    fun subscribeObservers() {
        activity?.let {
            viewModel.post.observe(it, Observer { post ->
                binding.postView.onBind(post.title, post.body, post.imageUrl, post.date, glideRequests)
            })
        }
    }
}