package com.lfg.app.util.recycler;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lfg.app.util.P;
import com.lfg.app.util.item.Item;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by sevyr on 18/01/2018.
 */

public abstract class ArrayListRecyclerFragment<VP extends Item, VH extends RecyclerView.ViewHolder> extends RecyclerFragment<VH> {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    // ARRAY LIST
    // ===============================================
    private final String TAG_LIST = "ViteeParcelList";
    protected final ArrayList<VP> m_list = new ArrayList<>();
    public void clearList(){
        setList(new ArrayList<VP>());
    }
    public void setList(ArrayList<VP> newList){
        m_list.clear();
        m_list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (m_list.size() > 0)
            outState.putParcelable(TAG_LIST, Parcels.wrap(m_list));
    }

    @Override
    protected void initVars(Bundle args){
        super.initVars(args);

        if (args.containsKey(TAG_LIST)) {
            ArrayList<VP> newList = Parcels.unwrap(args.getParcelable(TAG_LIST));
            P.ln("list parcel: " + newList.size());

            m_list.clear();
            m_list.addAll(newList);
            P.ln("list recreated: " + m_list.size());
            notifyDataSetChanged();
        }
    }



    // ============================================================================= \\
    // ADAPTER
    // ============================================================================= \\

    @Override
    public int getItemCount() {

        P.ln("recycler list size: " + m_list.size());
        return m_list.size();
    }
}