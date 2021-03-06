package com.hendra.movieapp.ui.profile

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hendra.movieapp.R
import com.hendra.movieapp.di.Injectable
import com.hendra.movieapp.utils.ResourceState

class ProfileFragment : Fragment(), Injectable {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var emailText: TextView
    private lateinit var profile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        emailText = root.findViewById(R.id.emailTextView)
        profile = root.findViewById(R.id.imageView)
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.emailLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == ResourceState.SUCCESS) {
                it.data?.let { email -> emailText.text = email }
                this.context?.let { ctx ->
                    try {
                        val res = ctx.resources
                        val imageStream = res.assets.open("harry_potter_poster.jpg")
                        val drawable: Drawable = BitmapDrawable(res, imageStream)
                        Glide.with(this)
                            .load(drawable)
                            .circleCrop()
                            .placeholder(ColorDrawable(Color.GRAY))
                            .into(profile)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
        return root
    }
}