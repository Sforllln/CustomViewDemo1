package com.abe.llln.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jeong Woo on 2016/11/21.
 */
public class RainbowBar extends View {

    //progress bar color
    int barColor = Color.parseColor("#1E88E5");
    //every bar segment width
    int hSpace = DisplayUtil.dp2px(getContext(),80);
    //every bar segment height
    int vSpace =DisplayUtil.dp2px(getContext(),4);
    //space among bars
    int space =DisplayUtil.dp2px(getContext(),10);
    float startX = 0;
    float delta = 10f;
    Paint mPaint;

    int index = 0;


    public RainbowBar(Context context) {
        super(context);
    }

    public RainbowBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RainbowBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //read custom attrs
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.rainbowbar,0,0);
        hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
        vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
        barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
        t.recycle();   // we should always recycle after used
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //get screen width
        float sw = this.getMeasuredWidth();
        if (startX > sw + (hSpace+space)-(sw%(hSpace+space))){
            startX= 0 ;
        }else {
            startX += delta;
        }
        float start = startX;
        //draw latter parse
        while(start < sw){
            canvas.drawLine(start,5,start+hSpace,5,mPaint);
            start +=(hSpace+space);
        }

        start = startX -space-hSpace;

        //draw front parse
        while (start>= -hSpace ){
            canvas.drawLine(start,5,start+hSpace,5,mPaint);
            start-=(hSpace+space);
        }
        if (index >= 1000*70){
            index = 0;
        }
        invalidate();
    }
}
