package ir.greencode.androidmvprxroom.ui.Main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.androidmvprxroom.R;
import ir.greencode.androidmvprxroom.mvp.model.GroupResponseResult;
import ir.greencode.androidmvprxroom.mvp.view.HomeView;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.Holder> {
    LayoutInflater mLayoutInflater;
    List<GroupResponseResult> list;
    HomeView mainView ;
    public GroupAdapter(LayoutInflater mLayoutInflater, HomeView mainView) {
        this.mLayoutInflater = mLayoutInflater;
        list = new ArrayList<>();
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_main_category, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {

        holder.bind(list.get(i));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.onClickOnItem(list.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<GroupResponseResult> cakes) {
        list.addAll(cakes);
    }

    public void cleareData() {
        list.clear();
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo)
        ImageView groupIcon;
        @BindView(R.id.group_title)
        TextView group_title;


        private Context mContext;
        public Holder(@NonNull View view) {
            super(view);
            mContext = view.getContext();
            ButterKnife.bind(this,view);
        }

        public void bind(GroupResponseResult result) {

            group_title.setText(result.getCatName());
            if(!result.getCatImg().equals(""))
            {
                Glide.with(mContext).load(result.getCatImg())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(groupIcon);
            }else {
                Glide.with(mContext).load(R.drawable.ic_launcher_background)
                        .into(groupIcon);
            }

        }
    }
}
