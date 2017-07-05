package com.mj.cloudsfrom0061.learnrecyclerview.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mj.cloudsfrom0061.learnrecyclerview.R;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class ListItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable drawable;
    private final static int DEFAULT_ORENTATION = LinearLayout.VERTICAL;
    private int mOrientation;

    public ListItemDecoration(Context context, int orientation) {
        if (orientation != LinearLayout.HORIZONTAL && orientation !=
                LinearLayout.VERTICAL) this.mOrientation = DEFAULT_ORENTATION;
        else this.mOrientation = orientation;
        drawable = context.getResources().getDrawable(R.drawable.divider_three_color);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) drawHorizontal(c,parent);
        else drawVertical(c,parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int chlicCount = parent.getChildCount();
        for (int i = 0; i < chlicCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }

    private void drawVertical(Canvas c,RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
