package me.salmon.microblog.home.ui.authors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.home.HomeActivity
import me.salmon.microblog.home.databinding.AuthorsFragmentBinding
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.utils.DataState
import javax.inject.Inject

@AndroidEntryPoint
class AuthorsFragment : Fragment() {

    private val viewModel: AuthorsViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager

    @Inject
    lateinit var navigator: Navigator

    lateinit var binding: AuthorsFragmentBinding

    private lateinit var adapter: AuthorsAdapter

    companion object {
        fun newInstance() = AuthorsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(AuthorsViewModel.AuthorStateEvent.GetAuthorEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(activity as AppCompatActivity, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Author>> -> {
//                    loading(false)
                    adapter.authorList = dataState.data
                    adapter.notifyDataSetChanged()
                }
                is DataState.Loading -> {
//                    loading(true)
                }
                is DataState.Error -> {
//                    loading(false)
//                    Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = AuthorsAdapter(navigator, glideRequests)
        val layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL, false)
        binding.authorsRecyclerView.layoutManager = layoutManager
        binding.authorsRecyclerView.adapter = adapter
    }

}