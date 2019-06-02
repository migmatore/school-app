package com.eisais.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        //val intent = Intent()

        val postTitle = intent.getStringExtra("title")
        val postBody = intent.getStringExtra("body")

        titleDetail.text = postTitle
        bodyDetail.text = postBody
    }
}
