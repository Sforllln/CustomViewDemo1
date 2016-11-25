package com.abe.llln.customviewdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Jeong Woo on 2016/11/21.
 */
public class CustomTitleView extends View {

    //text
    private String mTitleText;
    //text color
    private int mTitleColor;
    //text sizi
    private int mTitleTextSize;
    //绘制时文本绘制的范围
    private Rect mBound;
    private Paint mPain;


    public CustomTitleView(Context context) {
        this(context,null);

    }

    private String randomText() {
        Random r = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4){
            int randomInt = r.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i: set) {
            sb.append(""+i);
        }
        return sb.toString();
    }


    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomTitleView,
                defStyleAttr,0);
        int n =t.getIndexCount();
        for (int i = 0; i < n ; i++) {
            int attr = t.getIndex(i);
            switch(attr){
                case R.styleable.CustomTitleView_mTitleText:
                    mTitleText = t.getString(attr);
                    break;
                case R.styleable.CustomTitleView_mTitleTextColor:
                    mTitleColor = t.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_mTitleTextSize:
                    mTitleTextSize = t.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()
                    ));
                    break;
            }
        }
        t.recycle();
        //获得绘制文本的宽和高
        mPain = new Paint();
        mPain.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPain.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                postInvalidate();
            }

        });

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = 0;
        int height = 0;

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + getPaddingRight() + mBound.width();
                break;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;
            case MeasureSpec.AT_MOST:
                height = getPaddingTop() + getPaddingBottom() +mBound.height();
                break;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mPain.setColor(Color.GRAY);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPain);

        mPain.setColor(mTitleColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPain);

    }

}
