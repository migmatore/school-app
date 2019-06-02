package com.eisais.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eisais.app.adapters.PostAdapter
import com.eisais.app.api.Connect
import com.eisais.app.models.PostModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_category_detail.*

class CategoryDetailActivity : AppCompatActivity() {
    val api by lazy { Connect.callApi() }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        swipe_refresh.setOnRefreshListener {
            getPosts()
        }

        getPosts()

        swipe_refresh.setColorSchemeResources(R.color.light_blue, R.color.middle_blue, R.color.deep_blue)
    }

    private fun getPosts() {
        swipe_refresh.isRefreshing = true

        disposable = api.getSubjectPosts(id = intent.getStringExtra("id").toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                swipe_refresh.isRefreshing = false

                showPosts(result.data.response)
            }, { error ->
                swipe_refresh.isRefreshing = false

                error.printStackTrace()
            })
    }

    private fun showPosts(posts: List<PostModel.Response>) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = PostAdapter(posts, this)
    }
}
