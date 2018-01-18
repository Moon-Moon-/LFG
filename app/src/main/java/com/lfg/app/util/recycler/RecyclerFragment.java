package com.lfg.app.util.recycler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.lfg.app.util.SnackBarManager;

/**
 * Created by sevyr on 18/01/2018.
 */

public abstract class RecyclerFragment<VH extends RecyclerView.ViewHolder> extends Fragment {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    // RECYCLER VIEW
    // ===================================
    protected RecyclerView m_recyclerView;


    // TAGS
    // =================
    private final String
            BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout",
            TAG_LAST_POS = "lastPosition";


    // ANIMATION
    // ===============================
    protected int m_lastPosition = -1;
    private final Interpolator m_interpolator = new DecelerateInterpolator(3.f);



    // ============================================================================= \\
    // FRAGMENT
    // ============================================================================= \\

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup vars using arguments
        if (getArguments() != null)
            initVars(getArguments());

        // setup vars using savedInstanceState
        if (savedInstanceState != null)
            initVars(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(getLayout(), container, false);

        // setup views, recreate with savedInstanceState
        initViews(rootView, savedInstanceState);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (m_recyclerView != null && m_recyclerView.getLayoutManager() != null)
            outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, m_recyclerView.getLayoutManager().onSaveInstanceState());
        outState.putInt(TAG_LAST_POS, m_lastPosition);
    }



    // ============================================================================= \\
    // INIT
    // ============================================================================= \\

    // VAR SETUP
    // ===================================
    protected void initVars(Bundle args) {

        if (args.containsKey(TAG_LAST_POS))
            m_lastPosition = args.getInt(TAG_LAST_POS);
    }


    // VIEW SETUP
    // ==============================================================
    protected void initViews(ViewGroup v, Bundle savedInstanceState){

        // setup recycler view
        m_recyclerView = v.findViewById(getRecyclerId());

        // setup interfaces
        if (this instanceof RecyclerLayout) {
            RecyclerView.LayoutManager layoutManager = ((RecyclerLayout) this).getLayoutManager();
            m_recyclerView.setLayoutManager(layoutManager);
        }
        if (this instanceof RecyclerDecoration) {
            RecyclerView.ItemDecoration itemDecoration = ((RecyclerDecoration) this).getDecoration();
            m_recyclerView.addItemDecoration(itemDecoration);
        }

        m_recyclerView.setAdapter(setupAdapter());
        m_recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        SnackBarManager.dismiss();
                    }
                });

        // restore recycler layout
        if(savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_RECYCLER_LAYOUT)) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            m_recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }



    // ============================================================================= \\
    // ADAPTER
    // ============================================================================= \\

    private RecyclerView.Adapter setupAdapter() {
        return new RecyclerView.Adapter<VH>() {

            @Override
            public VH onCreateViewHolder(ViewGroup parent, int viewType) {
                return RecyclerFragment.this.onCreateViewHolder(parent, viewType, LayoutInflater.from(getContext()));
            }

            @Override
            public void onBindViewHolder(VH holder, int position) {
                RecyclerFragment.this.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemCount() {
                return RecyclerFragment.this.getItemCount();
            }

            public int getItemViewType(int position) {
                return RecyclerFragment.this.getItemViewType(position);
            }
        };
    }



    // ============================================================================= \\
    // INTERFACES
    // ============================================================================= \\

    interface RecyclerLayout {
        RecyclerView.LayoutManager getLayoutManager();
    }
    interface RecyclerDecoration {
        RecyclerView.ItemDecoration getDecoration();
    }

    public abstract @LayoutRes int getLayout();
    public abstract @IdRes int getRecyclerId();
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater layoutInflater);
    public abstract void onBindViewHolder(VH holder, int position);
    public abstract int getItemCount();
    public int getItemViewType(int position) {
        return 0;
    }



    // ============================================================================= \\
    // ANIMATION
    // ============================================================================= \\

    public boolean hasAdapter() {
        return m_recyclerView != null && m_recyclerView.getAdapter() != null;
    }

    public void notifyDataSetChanged(){

        if (hasAdapter()){
            m_lastPosition = -1;
            m_recyclerView.getAdapter().notifyItemRangeChanged(0, getItemCount());
            m_recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    protected void animate(final VH holder, final int position){

        final View v = holder.itemView;

        if (position > m_lastPosition) {

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            final int screenHeight = displaymetrics.heightPixels;

            ViewCompat.setTranslationY(v, screenHeight);
            v.animate()
                    .translationY(0)
                    .setInterpolator(m_interpolator)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            ViewCompat.setTranslationY(v, 0);
                        }
                    })
                    .start();

            m_lastPosition = position;
        }
//        else
//            ViewCompat.setTranslationY(v, 0);
    }
}