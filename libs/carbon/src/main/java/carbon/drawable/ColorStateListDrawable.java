package carbon.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import carbon.animation.AnimatedColorStateList;

public class ColorStateListDrawable extends Drawable implements AlphaDrawable {
    private AnimatedColorStateList list;

    public ColorStateListDrawable(AnimatedColorStateList list) {
        this.list = list;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(list.getColorForState(getState(), list.getDefaultColor()));
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    public boolean setState(@NonNull int[] stateSet) {
        list.setState(stateSet);
        return super.setState(stateSet);
    }

    @Override
    public int getAlpha() {
        return (list.getColorForState(getState(), list.getDefaultColor()) >> 24) & 0xff;
    }
}
