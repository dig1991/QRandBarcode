package one.mstudio.qrbar.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import one.mstudio.qrbar.R;

/**
 * Created by Ashiq on 3/1/16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mList;

    private ClickListener clickListener;

    public HistoryAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view, viewType);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView result;
        private ImageButton copyButton;

        public ViewHolder(View v, int viewType) {
            super(v);
            result = (TextView) v.findViewById(R.id.result);
            copyButton = (ImageButton) v.findViewById(R.id.copyButton);
            copyButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.onItemClicked(getLayoutPosition());
            }

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.result.setText(Html.fromHtml(mList.get(position), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.result.setText(Html.fromHtml(mList.get(position)));
        }
        holder.result.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface ClickListener {
        public void onItemClicked(int position);
    }
}