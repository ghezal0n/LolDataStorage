package tn.esprit.loldatastorage.championList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.loldatastorage.data.Champion
import tn.esprit.loldatastorage.DetailActivity
import tn.esprit.loldatastorage.R
import tn.esprit.loldatastorage.data.NAME
import tn.esprit.loldatastorage.data.PICTURE
import tn.esprit.loldatastorage.data.ROLE
import tn.esprit.loldatastorage.utils.AppDataBase

class ChampionAdapter(var championsList: MutableList<Champion>) : RecyclerView.Adapter<ChampionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.champion_single_item, parent, false)

        return ChampionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {

        val name = championsList[position].champName
        val role = championsList[position].champRole

        holder.championPic.setImageResource(championsList[position].champPic)
        holder.championName.text = name
        holder.championRole.text = role

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra(PICTURE, championsList[position].champPic)
                putExtra(NAME, name)
                putExtra(ROLE, role)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            //TODO 13 "Delete a champion from the database and refresh the recyclerView"
            AppDataBase.getDatabase(holder.itemView.context).champDao().delete(championsList[position])
            championsList.removeAt(position)
            notifyDataSetChanged()


        }

    }

    override fun getItemCount() = championsList.size

}