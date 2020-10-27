package me.salmon.microblog.utils.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.salmon.microblog.utils.R
import me.salmon.microblog.utils.databinding.AvatarViewBinding


class AvatarView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    var binding: AvatarViewBinding =
        AvatarViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        var a = context.obtainStyledAttributes(attrs, R.styleable.AvatarView, 0, 0)
        val radius = a.getDimension(R.styleable.AvatarView_avatarRadius,
            context.resources.getDimension(R.dimen.avatar_radius))
        binding.cardView.radius = radius
    }

    fun setAvatar(id: Int, text: String, url: String?, requestManager: RequestManager) {
        binding.firstLetterText.setBackgroundResource(getColorResource(id))
        binding.firstLetterText.text = text
        binding.firstLetterText.visibility = View.VISIBLE
        binding.imageView.visibility = View.INVISIBLE
        url?.let {
            requestManager.load(url)
                .override(context.resources.getDimensionPixelSize(R.dimen.avatar_size))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean = false

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.firstLetterText.visibility = View.INVISIBLE
                        binding.imageView.visibility = View.VISIBLE
                        return false
                    }

                })
                .into(binding.imageView)
        }

    }

    private fun getColorResource(id: Int): Int {
        return when((id % 10) + 1) {
            1 -> R.color.avatar_color_1
            2 -> R.color.avatar_color_2
            3 -> R.color.avatar_color_3
            4 -> R.color.avatar_color_4
            5 -> R.color.avatar_color_5
            6 -> R.color.avatar_color_6
            7 -> R.color.avatar_color_7
            8 -> R.color.avatar_color_8
            9 -> R.color.avatar_color_9
            else -> R.color.avatar_color_10
        }
    }
}