package com.eisais.app.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.util.Log
import com.eisais.app.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eisais.app.PostDetailActivity
import com.eisais.app.models.PostModel

class PostAdapter(val postList: List<PostModel.Response>, val context: Context) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_item_layout, parent, false)

        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = postList[position].title
        holder.miniBody?.text = postList[position].mini_body

//        val createAnimationView = CreateAnimationView()
//
//        createAnimationView.createAnimation(holder.itemView)

        holder.itemView.setOnClickListener {
            Log.w("RESULT", postList[position].title)

            val intent = Intent(context, PostDetailActivity::class.java)

            intent.putExtra("title", postList[position].title)
            intent.putExtra("body", postList[position].body)

//            val detailTitle = Pair.create<View, String>(textView, "layoutTransition")
//
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, detailTitle)

            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val miniBody = itemView.findViewById<TextView>(R.id.mini_body)
    }
}

class CreateAnimationView {
    private val contador: Int = 0

    fun createAnimation(view: View): AnimatorSet {
        val fadeOut = ObjectAnimator.ofFloat(
            view, "alpha",
            0f
        )
        fadeOut.duration = 300

        val mover = ObjectAnimator.ofFloat(
            view,
            "translationX", -500f, 0f
        )
        mover.duration = 400

        val fadeIn = ObjectAnimator.ofFloat(
            view, "alpha",
            0f, 1f
        )
        fadeIn.duration = 300

        val animatorSet = AnimatorSet()

        animatorSet.play(mover)
        animatorSet.start()

        return animatorSet
    }
}