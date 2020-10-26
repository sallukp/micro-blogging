package me.salmon.microblog.home.ui.authors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.salmon.microblog.home.R

class AuthorsFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorsFragment()
    }

    private lateinit var viewModel: AuthorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.authors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(AuthorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}