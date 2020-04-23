package hall.androidcalendar.ui.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import static hall.androidcalendar.R.*;
public class InviteeAdapter extends RecyclerView.Adapter<InviteeAdapter.ViewHolder> {

    Context context;
    ArrayList<String> invitees;


    public InviteeAdapter(Context context, ArrayList<String> invitees){
        this.context = context;
        this.invitees = invitees;
    }

    @NonNull
    @Override
    public InviteeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.content_invitees, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InviteeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
