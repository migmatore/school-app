package com.eisais.app.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eisais.app.CategoryDetailActivity
import com.eisais.app.R
import com.eisais.app.models.CategoryModel

class CategoryAdapter(val subjectList: List<CategoryModel.Response>, val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_category_item_layout, parent, false)

        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = subjectList[position].title

//        val createAnimationView = CreateAnimationView()
//
//        createAnimationView.createAnimation(holder.itemView)
//
        holder.itemView.setOnClickListener {
            Log.w("RESULT", subjectList[position].id.toString())
            Log.w("RESULT", subjectList[position].title)

            val intent = Intent(context, CategoryDetailActivity::class.java)

            intent.putExtra("id", subjectList[position].id.toString())
            intent.putExtra("title", subjectList[position].title)

//            val detailTitle = Pair.create<View, String>(textView, "layoutTransition")
//
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, detailTitle)

            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
    }
}

