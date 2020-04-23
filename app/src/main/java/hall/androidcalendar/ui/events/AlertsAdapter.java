package hall.androidcalendar.ui.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;

import hall.androidcalendar.Alert;
import static hall.androidcalendar.R.*;

public class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.ViewHolder> {
    Context context;
    ArrayList<Alert> alerts;

    public AlertsAdapter(Context context, ArrayList<Alert> alerts){
        this.context = context;
        this.alerts = alerts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.content_alerts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.alerts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView){
            super(itemView);

        }
    }
}