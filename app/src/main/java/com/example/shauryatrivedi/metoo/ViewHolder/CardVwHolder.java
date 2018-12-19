package com.example.shauryatrivedi.metoo.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.shauryatrivedi.metoo.Fragments.Laws;

public class CardVwHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    Laws.ItemLongClickListener itemLongClickListener;

    public CardVwHolder(View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(this);
    }

    public void setItemLongClickListener(Laws.ItemLongClickListener ic)
    {
        this.itemLongClickListener=ic;
    }

    @Override
    public boolean onLongClick(View v) {
        this.itemLongClickListener.onItemLongClick(v,getLayoutPosition());
        return false;
    }
}

