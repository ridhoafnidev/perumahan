package com.ridhoafnidev.project.feature.calonpemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davemorrissey.labs.subscaleview.ImageSource
import com.ridhoafnidev.project.feature.calonpemilik.databinding.ActivityPreviewDpActivityBinding

class PreviewDPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewDpActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewDpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnClose.setOnClickListener {
                onBackPressed()
            }
            subDpPreview.setImage(ImageSource.resource(R.drawable.dummy_rumah))
        }
    }
}