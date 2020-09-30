package com.hendra.movieapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.hendra.movieapp.R
import com.hendra.movieapp.di.Injectable
import com.hendra.movieapp.utils.ResourceState
import com.hendra.movieapp.utils.ViewModelFactory
import com.smarteist.autoimageslider.SliderView
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), Injectable {
    companion object {
        const val TYPE_BATMAN = 1
        const val TYPE_FAST = 2
        const val TYPE_HARRY = 3
        const val TYPE_SPIDER = 4
        const val TYPE_STAR = 5
        private const val EXTRA_ID = "extra-id"

        fun startActivity(context: Context, movieType: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, movieType)
            return intent
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ContainerViewModel by viewModels { viewModelFactory }
    private lateinit var adapterPoster: SliderAdapter
    private lateinit var slider: SliderView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel.doSearch(intent.getIntExtra(EXTRA_ID, 0))
        viewModel.listPosterLiveData.observe(this, Observer {
            if (it.status == ResourceState.SUCCESS) {
                slider.setSliderAdapter(adapterPoster)
            }
        })
    }
}