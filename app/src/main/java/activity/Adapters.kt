package activity

//adapter to bind activity cards to view holder in recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.todo.R

import kotlinx.android.synthetic.main.alistitem.view.*

class ActAdapter(val context: Context, val activities: ArrayList<Activity>)
    :RecyclerView.Adapter<ActAdapter.ViewHolder>() {

    //text of view holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtAct = itemView.txtAct
    }

    //to initialise view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.alistitem, parent, false)
        return ViewHolder(v)
    }

    //to count number of item in list
    override fun getItemCount(): Int {
        return activities.size
    }

    //to bind activity cards to view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity: Activity = activities[position]
        holder.txtAct.text = activity.aName

    }
}