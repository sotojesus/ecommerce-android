/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartsecurity.android.piudonnacouture.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.smartsecurity.android.piudonnacouture.R;

/**
 * Extension of FrameLayout that assumes a measured (non-zero) width and sets the
 * height according to the provided aspect ratio.
 */
public class AspectRatioFrameLayout extends FrameLayout {
    private float mAspectRatio = 0f;

    public AspectRatioFrameLayout(Context context) {
        this(context, null, 0);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AspectRatioView, defStyle, 0);

        mAspectRatio = a.getFloat(R.styleable.AspectRatioView_aspectRatio, 0);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width, height;
        if (mAspectRatio != 0) {
            width = widthSize;
            height = (int) (width / mAspectRatio);
            int exactWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int exactHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(exactWidthSpec, exactHeightSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
