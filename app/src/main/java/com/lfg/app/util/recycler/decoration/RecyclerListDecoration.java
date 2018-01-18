package com.lfg.app.util.recycler.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lfg.app.R;

/**
 * Created by sevyr on 17/01/2018.
 */

public class RecyclerListDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int offset = parent.getResources().getDimensionPixelSize(R.dimen.list_padding);

        outRect.left = offset;
        outRect.right = offset;
        outRect.top = offset;
    }
}