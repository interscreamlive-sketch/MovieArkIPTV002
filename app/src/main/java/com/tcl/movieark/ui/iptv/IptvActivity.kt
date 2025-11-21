package com.tcl.movieark.ui.iptv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tcl.movieark.R
import com.tcl.movieark.data.m3u.M3uParser
import com.tcl.movieark.model.IptvChannel
import com.tcl.movieark.player.VideoPlayActivity

class IptvActivity : AppCompatActivity() {

    private val parser = M3uParser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iptv)

        val edtUrl = findViewById<EditText>(R.id.edtM3uUrl)
        val btnLoad = findViewById<Button>(R.id.btnCarregar)
        val rv = findViewById<RecyclerView>(R.id.rvCanais)

        val adapter = IptvAdapter { channel -> openChannel(channel) }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        btnLoad.setOnClickListener {
            val url = edtUrl.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Informe um link M3U", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Thread {
                try {
                    val list = parser.loadFromUrl(url)
                    runOnUiThread {
                        adapter.submitList(list)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this, "Erro: ${'$'}{e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }.start()
        }
    }

    private fun openChannel(channel: IptvChannel) {
        val it = Intent(this, VideoPlayActivity::class.java).apply {
            putExtra(VideoPlayActivity.EXTRA_STREAM_URL, channel.url)
            putExtra(VideoPlayActivity.EXTRA_STREAM_TITLE, channel.name)
        }
        startActivity(it)
    }
}
