package com.vanxnf.photovalley.widget.SlideTablayout;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.view.animation.LinearInterpolator;


/**
 * Created by Andy671
 */

public class LineMoveIndicator implements AnimatedIndicatorInterface, ValueAnimator.AnimatorUpdateListener {

    private Paint paint;
    private RectF rectF;
    private Rect rect;

    private int height;
    private int edgeRadius;
    private int leftX, rightX;

    private ValueAnimator valueAnimatorLeft, valueAnimatorRight;

    private LinearInterpolator linearInterpolator;

    private SlideTabLayout slideTabLayout;

    public LineMoveIndicator(SlideTabLayout slideTabLayout){
        this.slideTabLayout = slideTabLayout;

        linearInterpolator = new LinearInterpolator();

        valueAnimatorLeft = new ValueAnimator();
        valueAnimatorLeft.setDuration(DEFAULT_DURATION);
        valueAnimatorLeft.addUpdateListener(this);
        valueAnimatorLeft.setInterpolator(linearInterpolator);

        valueAnimatorRight = new ValueAnimator();
        valueAnimatorRight.setDuration(DEFAULT_DURATION);
        valueAnimatorRight.addUpdateListener(this);
        valueAnimatorRight.setInterpolator(linearInterpolator);

        rectF = new RectF();
        rect = new Rect();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        leftX = (int) slideTabLayout.getChildXLeft(slideTabLayout.getCurrentPosition());
        rightX = (int) slideTabLayout.getChildXRight(slideTabLayout.getCurrentPosition());

        edgeRadius = -1;
    }

    public void setEdgeRadius(int edgeRadius){
        this.edgeRadius = edgeRadius;

        slideTabLayout.invalidate();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        leftX = (int) valueAnimatorLeft.getAnimatedValue();
        rightX = (int) valueAnimatorRight.getAnimatedValue();

        rect.top = slideTabLayout.getHeight() - height;
        rect.left =  leftX + height/2;
        rect.right = rightX - height/2;
        rect.bottom = slideTabLayout.getHeight();

        slideTabLayout.invalidate(rect);
    }

    @Override
    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        paint.setColor(color);
    }

    @Override
    public void setSelectedTabIndicatorHeight(int height) {
        this.height = height;

        if(edgeRadius == -1)
            edgeRadius = height;
    }

    @Override
    public void setIntValues(int startXLeft, int endXLeft,
                             int startXCenter, int endXCenter,
                             int startXRight, int endXRight){
        valueAnimatorLeft.setIntValues(startXLeft, endXLeft);
        valueAnimatorRight.setIntValues(startXRight, endXRight);
    }

    @Override
    public void setCurrentPlayTime(long currentPlayTime) {
        valueAnimatorLeft.setCurrentPlayTime(currentPlayTime);
        valueAnimatorRight.setCurrentPlayTime(currentPlayTime);
    }

    @Override
    public void draw(Canvas canvas) {
        rectF.top = slideTabLayout.getHeight() - height;
        rectF.left =  leftX + height/2;
        rectF.right = rightX - height/2;
        rectF.bottom = slideTabLayout.getHeight();

        canvas.drawRoundRect(rectF, edgeRadius, edgeRadius, paint);
    }

    @Override
    public long getDuration() {
        return valueAnimatorLeft.getDuration();
    }
}
