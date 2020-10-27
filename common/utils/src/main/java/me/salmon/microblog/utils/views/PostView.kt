package me.salmon.microblog.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import me.salmon.microblog.utils.R
import me.salmon.microblog.utils.databinding.PostViewBinding
import me.salmon.microblog.utils.extensions.DateUtils.formatDate

class PostView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {


    var binding: PostViewBinding =
        PostViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun onBind(title: String?, body: String?, imageUrl: String?, date: String?, glideRequests: RequestManager) {
        imageUrl?.let {
            glideRequests.load(it)
                .apply(RequestOptions().placeholder(R.drawable.ic_place_holder))
                .into(binding.postImageView)
        }
        binding.bodyText.text = body
        binding.titleText.text = title
        binding.timeText.text = date.formatDate()
    }
}