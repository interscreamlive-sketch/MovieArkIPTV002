package com.tcl.movieark.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tcl.movieark.R
import com.tcl.movieark.ui.iptv.IptvActivity
import com.tcl.movieark.ui.mediaDetail.MediaDetailActivity
import com.tcl.movieark.ui.personal.MediaLibraryActivity
import com.tcl.movieark.ui.search.SearchActivity
import com.tcl.movieark.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnMovies).setOnClickListener {
            startActivity(Intent(this, MediaDetailActivity::class.java))
        }
        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        findViewById<Button>(R.id.btnPersonal).setOnClickListener {
            startActivity(Intent(this, MediaLibraryActivity::class.java))
        }
        findViewById<Button>(R.id.btnIptv).setOnClickListener {
            startActivity(Intent(this, IptvActivity::class.java))
        }
        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }
}
