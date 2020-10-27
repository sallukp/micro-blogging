package me.salmon.microblog.utils.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.salmon.microblog.utils.R
import me.salmon.microblog.utils.databinding.RecyclerViewFragmentBinding

abstract class RecyclerViewFragment<VH: RecyclerView.ViewHolder>: Fragment() {
    lateinit var binding: RecyclerViewFragmentBinding

    protected lateinit var adapter: RecyclerView.Adapter<VH>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecyclerViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        subscribeObservers()
        setStateEvent()
    }
    
    abstract fun setStateEvent()

    abstract fun subscribeObservers()

    private fun initRecyclerView() {
        adapter = createAdapter()
        val layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
    
    abstract fun createAdapter(): RecyclerView.Adapter<VH>

    fun showLoading(loading: Boolean) {
        binding.progress.visibility = if (loading) VISIBLE  else GONE
    }

    fun showError() {
        Toast.makeText(activity, R.string.could_not_connect, Toast.LENGTH_SHORT).show()
    }
}