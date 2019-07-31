package com.akb.gaplokbattle.adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akb.gaplokbattle.Data.AppDatabase;
import com.akb.gaplokbattle.Model.History;
import com.akb.gaplokbattle.R;

import java.util.ArrayList;

public class RecyclerHistoryAdapter extends RecyclerView.Adapter<RecyclerHistoryAdapter.ViewHolder> {

    private ArrayList<History> daftarHistory;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerHistoryAdapter(ArrayList<History> daftarHistory, Context context) {

        //Inisialisasi data yang akan digunakan
        this.daftarHistory = daftarHistory;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbGaplokBattle").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //Deklarasi View yang akan digunakan
        private TextView Id,Status, Waktu;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            Status = itemView.findViewById(R.id.status);
            Waktu = itemView.findViewById(R.id.waktu);
            //Id = itemView.findViewById(R.id.id);
            item = itemView.findViewById(R.id.cvMain);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Deklarasi Variable untuk mendapatkan data dari Database melalui Array
        //int getId = daftarHistory.get(position).getId();
        String getStatus = daftarHistory.get(position).getStatus();
        String getWaktu = daftarHistory.get(position).getWaktu();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        //holder.Id.setText(getId);
        holder.Status.setText(getStatus);
        holder.Waktu.setText(getWaktu);
    }

//    private void onDeleteData(int position){
//        appDatabase.historyDao().delete(daftarHistory.get(position));
//        daftarHistory.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, daftarHistory.size());
//        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return daftarHistory.size();
    }
}

