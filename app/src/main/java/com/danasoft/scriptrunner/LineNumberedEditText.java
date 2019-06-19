package com.danasoft.scriptrunner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import java.util.Locale;

/**
     * Author S Mahbub Uz Zaman on 5/9/15.
     * Lisence Under GPL2
     */

    public class LineNumberedEditText extends AppCompatEditText {
        float mOriginalLeftPadding = -1;
        private Rect rect;
        private Paint paint;

        public LineNumberedEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            rect = new Rect();
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            paint.setTextSize(20);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec,
                                 int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            calculatePrefix();
        }

        private void calculatePrefix() {
            if (mOriginalLeftPadding == -1) {
                String prefix = "XXX";
                float[] widths = new float[prefix.length()];
                getPaint().getTextWidths(prefix, widths);
                float textWidth = 0;
                for (float w : widths) {
                    textWidth += w;
                }
                mOriginalLeftPadding = getCompoundPaddingLeft();
                setPadding((int) (textWidth + mOriginalLeftPadding),
                        getPaddingRight(), getPaddingTop(),
                        getPaddingBottom());
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (int i = 0; i < getLineCount(); i++) {
                canvas.drawText(String.format(Locale.getDefault(), "%d. ", i+1), mOriginalLeftPadding,
                        getLineBounds(i, null), getPaint());
            }
            super.onDraw(canvas);
        }
    }
