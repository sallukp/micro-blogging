package me.salmon.microblog.home.ui.authors

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.home.R
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.utils.DataState
import me.salmon.microblog.utils.views.RecyclerViewFragment
import javax.inject.Inject

@AndroidEntryPoint
class AuthorsFragment : RecyclerViewFragment<AuthorsAdapter.AuthorsViewHolders>() {

    private val viewModel: AuthorsViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager

    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun newInstance() = AuthorsFragment()
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(activity as AppCompatActivity, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Author>> -> {
                    showLoading(false)
                    (adapter as AuthorsAdapter).authorList = dataState.data
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

    override fun setStateEvent() {
        viewModel.setStateEvent(AuthorsViewModel.AuthorStateEvent.GetAuthorEvent)
    }

    override fun createAdapter(): AuthorsAdapter = AuthorsAdapter(navigator, glideRequests)

}