package com.thao.gobear.article.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thao.gobear.R
import com.thao.gobear.article.ArticleClickListener
import com.thao.gobear.article.model.ArticleModel

class ArticleAdapter(
    private var articleList: ArrayList<ArticleModel>,
    private var listener: ArticleClickListener
) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
            vh = ViewHolder()

            vh.tvTitle = view.findViewById(R.id.txtTitle)
            vh.tvDescription = view.findViewById(R.id.txtDes)
            vh.tvPubDate = view.findViewById(R.id.txtPubDate)
            vh.imgThumb = view.findViewById(R.id.imageView)

            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = articleList[position].title
        vh.tvDescription.text = articleList[position].description
        vh.tvPubDate.text = articleList[position].pubDate
        Glide.with(parent.context)
            .load(articleList[position].thumb)
            .apply(RequestOptions.centerCropTransform())
            .into(vh.imgThumb)


        view?.setOnClickListener(View.OnClickListener { listener.onClick(articleList[position]) })
        return view
    }

    override fun getItem(position: Int): Any {
        return articleList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return articleList.size
    }

    fun clear() {
        articleList.clear()
        notifyDataSetChanged()
    }
}

private class ViewHolder() {
    lateinit var tvTitle: TextView
    lateinit var tvDescription: TextView
    lateinit var tvPubDate: TextView
    lateinit var imgThumb: ImageView
}