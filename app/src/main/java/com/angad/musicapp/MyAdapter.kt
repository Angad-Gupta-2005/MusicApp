package com.angad.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//    Below 3 function are the Member of MyAdapter class when extends the RecyclerView

//    called when RecyclerView needs a new ViewHolder of the given type to represent an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//   create the view in case the layout manager fails to create view for the data
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent,false)
        return MyViewHolder(itemView)
    }

//    Returns the total number of items(music) in the data set held by the adapter
    override fun getItemCount(): Int {
        return dataList.size
    }

//    Called by RecyclerView to display the data at the specified position in the Application
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//      populate the data into the view
        val currentData = dataList[position]

//    fetch the title of the music from the API
        holder.title.text = currentData.title

//    fetch the image from the API
    Picasso.get().load(currentData.album.cover).into(holder.image);

//    fetch the music from the API
    val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())

//    play the music
    holder.play.setOnClickListener{
        mediaPlayer.start()
    }

//    pause the music
    holder.pause.setOnClickListener {
        mediaPlayer.stop()
    }


}

//    This class hold the view of each_item.xml file like ImageView etc
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        //        initialised the above variable
        init {
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTitle)
            play = itemView.findViewById(R.id.btnPlay)
            pause = itemView.findViewById(R.id.btnPause)
        }
    }

}