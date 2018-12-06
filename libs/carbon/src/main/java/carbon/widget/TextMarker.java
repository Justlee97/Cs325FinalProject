package carbon.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import carbon.R;

public class TextMarker extends View {
    TextPaint paint;
    Rect rect = new Rect();
    Rect rect2 = new Rect();
    CharSequence text = "I";
    private int id;
    private int baseline;
    private StaticLayout layout;

    public TextMarker(Context context) {
        super(context);
    }

    public TextMarker(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs, 0);
    }

    public TextMarker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextMarker, defStyleAttr, 0);

            for (int i = 0; i < a.getIndexCount(); i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.TextMarker_carbon_text) {
                    setText(a.getText(attr).toString());
                } else if (attr == R.styleable.TextMarker_carbon_textView) {
                    id = a.getResourceId(attr, 0);
                }
            }

            a.recycle();
        }
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(TextPaint paint) {
        this.paint = paint;
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (id != 0) {
            android.widget.TextView textView = ((ViewGroup) getParent()).findViewById(id);
            if (text == null)
                text = textView.getText();
            paint = textView.getPaint();

            if (layout == null)
                layout = new StaticLayout(text, paint, getMeasuredWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, true);

            String firstLine = text.subSequence(0, layout.getLineEnd(0)).toString();
            paint.getTextBounds(firstLine, 0, firstLine.length(), rect);
            baseline = Math.abs(rect.top);
            rect.top = -layout.getLineAscent(0) + rect.top;

            String lastLine = text.subSequence(layout.getLineStart(layout.getLineCount() - 1), layout.getLineEnd(layout.getLineCount() - 1)).toString();
            paint.getTextBounds(lastLine, 0, lastLine.length(), rect2);
            rect.bottom = layout.getHeight() - layout.getLineDescent(layout.getLineCount() - 1) + rect2.bottom;

            setMeasuredDimension(getMeasuredWidth(), rect.height() + getPaddingTop() + getPaddingBottom());
        }
    }

    @Override
    public int getBaseline() {
        return baseline + getPaddingTop();
    }

}