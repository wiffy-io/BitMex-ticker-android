package io.wiffy.bitmexticker.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration (verticalSpaceHeight : Int): RecyclerView.ItemDecoration() {

    private var verticalSpaceHeight = verticalSpaceHeight;

    override fun getItemOffsets(outRect : Rect, view : View, parent: RecyclerView, state : RecyclerView.State ) {
        // 마지막 아이템이 아닌 경우, 공백 추가
        if(parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount -1 ){
            outRect.bottom = verticalSpaceHeight;
        }
    }
}

