package me.salmon.microblog.profile.ui.post

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Constants
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.profile.R
import me.salmon.microblog.profile.databinding.ProfileFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var glideRequests: RequestManager

    lateinit var binding: ProfileFragmentBinding

    companion object {
        fun newInstance(author: Author): ProfileFragment {
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constants.authorExtra, author)
            fragment.arguments = bundle
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        val author: Author? = arguments?.getParcelable(Constants.authorExtra)
        author?.let {
            viewModel.setStateEvent(ProfileViewModel.ProfileStateEvent.GetProfileState(it))
        }
    }


    fun subscribeObservers() {
        activity?.let {
            viewModel.author.observe(it, Observer { author ->
                binding.authorName.text = author.name
                binding.userName.text = "@${author.userName}"
                binding.avatarView.setAvatar(author.id, author.getFirstLetters(), author.avatarUrl, glideRequests)
                binding.mapButton.setOnClickListener {
                    navigator.navigate(Navigator.Feature.MAP, author)
                }
                binding.emailButton.setOnClickListener {
                    navigator.navigate(Navigator.Feature.EMAIL, author.email)
                }
            })
        }
    }

}