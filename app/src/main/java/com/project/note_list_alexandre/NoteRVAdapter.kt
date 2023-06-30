package com.project.note_list_alexandre

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface,
    val checkClicked: NoteClickCheckInterface,
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV: TextView = itemView.findViewById(R.id.idTVNote)
        val dateTV: TextView = itemView.findViewById(R.id.idTVDate)
        val deleteIV: ImageView = itemView.findViewById(R.id.idIVDelete)
        val tickImageView: ImageView = itemView.findViewById(R.id.tickImageView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes[position].noteTitle
        holder.dateTV.text = "Last Updated : " + allNotes[position].timeStamp


        val note = allNotes[position]
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(note)
        }

        if (note.isChecked)
            holder.tickImageView.setImageResource(R.drawable.baseline_check_box_24)
        else
            holder.tickImageView.setImageResource(R.drawable.baseline_check_box_outline_blank_24)


        holder.tickImageView.setOnClickListener {
            note.isChecked = !note.isChecked
            Log.d(TAG, "onBindViewHolder: "+note.isChecked)

            checkClicked.onCheckIconClick(note)
        }

        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickCheckInterface {
    fun onCheckIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}
