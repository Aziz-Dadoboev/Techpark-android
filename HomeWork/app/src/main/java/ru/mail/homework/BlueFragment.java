package ru.mail.homework;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlueFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blue, container, false);
        List<DataSource.NewsModel> news = DataSource.getInstance().getData();

        RecyclerView recyclerView = v.findViewById(R.id.recycler);
        int cols = 3;
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            cols = 4;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), cols));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(news);
        recyclerView.setAdapter(adapter);

        Button button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource.getInstance().addData(new DataSource.NewsModel(777, Color.RED));
                adapter.DataChange(DataSource.getInstance().getData());
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<DataSource.NewsModel> mData;

        public MyAdapter(List<DataSource.NewsModel> mData) {
            this.mData = mData;
        }

        public void DataChange(List<DataSource.NewsModel> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(
                    parent.getContext())
                    .inflate(R.layout.news_layout,
                            parent,
                            false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataSource.NewsModel model = mData.get(position);
            holder.mNum.setText(String.valueOf(model.getNum()));
            holder.mNum.setTextColor(model.getColor());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNum;
        public MyViewHolder(@NonNull View item) {
            super(item);
            mNum = item.findViewById(R.id.text_num);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SecondFragment secFragment = new SecondFragment();
                    secFragment.Params(
                            ((TextView) v.findViewById(R.id.text_num)).getText().toString(),
                            ((TextView) v.findViewById(R.id.text_num)).getCurrentTextColor()
                    );
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, secFragment)
                            .addToBackStack(SecondFragment.class.getSimpleName())
                            .commit();
                }
            });
        }
    }
}
