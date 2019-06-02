package com.eisais.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eisais.app.adapters.CategoryAdapter
import com.eisais.app.api.Connect
import com.eisais.app.models.CategoryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val api by lazy { Connect.callApi() }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe_refresh.setOnRefreshListener {
            getSubjects()
        }

        getSubjects()

        swipe_refresh.setColorSchemeResources(R.color.light_blue, R.color.middle_blue, R.color.deep_blue)
    }

    private fun getSubjects() {
        swipe_refresh.isRefreshing = true

        disposable = api.getSubjects()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                swipe_refresh.isRefreshing = false

                showSubjects(result.data.response)
            }, { error ->
                swipe_refresh.isRefreshing = false

                error.printStackTrace()
            })
    }

    private fun showSubjects(subjects: List<CategoryModel.Response>) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = CategoryAdapter(subjects, this)
    }
}
