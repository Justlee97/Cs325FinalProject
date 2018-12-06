package carbon.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SoundEffectConstants;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.CompoundButton;

import carbon.Carbon;
import carbon.R;
import carbon.drawable.ButtonGravity;
import carbon.drawable.CheckableDrawable;
import carbon.drawable.ripple.RippleDrawable;

public class CheckBox extends TextView implements Checkable {
    private Drawable drawable;
    private float drawablePadding;
    private ButtonGravity buttonGravity;

    CheckableDrawable.CheckedState checkedState = CheckableDrawable.CheckedState.UNCHECKED;

    public CheckBox(Context context) {
        super(context, null, android.R.attr.checkboxStyle);
        initCheckBox(null, android.R.attr.checkboxStyle, R.style.carbon_CheckBox);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(Carbon.getThemedContext(context, attrs, R.styleable.CheckBox, android.R.attr.checkboxStyle, R.styleable.CheckBox_carbon_theme), attrs, android.R.attr.checkboxStyle);
        initCheckBox(attrs, android.R.attr.checkboxStyle, R.style.carbon_CheckBox);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(Carbon.getThemedContext(context, attrs, R.styleable.CheckBox, defStyleAttr, R.styleable.CheckBox_carbon_theme), attrs, defStyleAttr);
        initCheckBox(attrs, defStyleAttr, R.style.carbon_CheckBox);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(Carbon.getThemedContext(context, attrs, R.styleable.CheckBox, defStyleAttr, R.styleable.CheckBox_carbon_theme), attrs, defStyleAttr, defStyleRes);
        initCheckBox(attrs, defStyleAttr, defStyleRes);
    }

    public void initCheckBox(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBox, defStyleAttr, defStyleRes);

        int drawableId = a.getResourceId(R.styleable.CheckBox_android_button, R.drawable.carbon_defaultdrawable);
        Drawable d;
        if (drawableId == R.drawable.carbon_defaultdrawable) {
            if (!isInEditMode()) {
                d = new CheckableDrawable(getContext(), R.raw.carbon_checkbox_checked, R.raw.carbon_checkbox_unchecked, R.raw.carbon_checkbox_filled, new PointF(-0.09f, 0.11f));
            } else {
                d = getResources().getDrawable(android.R.drawable.checkbox_on_background);
            }
        } else {
            d = ContextCompat.getDrawable(getContext(), drawableId);
        }
        setButtonDrawable(d);

        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CheckBox_android_drawablePadding) {
                drawablePadding = a.getDimension(attr, 0);
            } else if (attr == R.styleable.CheckBox_android_checked) {
                setChecked(a.getBoolean(attr, false));
            } else if (attr == R.styleable.CheckBox_carbon_buttonGravity) {
                buttonGravity = ButtonGravity.values()[a.getInt(attr, 0)];
            }
        }

        Carbon.initHtmlText(this, a, R.styleable.CheckBox_carbon_htmlText);

        a.recycle();
    }

    private boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL;
    }

    private boolean isButtonOnTheLeft() {
        return buttonGravity == ButtonGravity.LEFT ||
                !isLayoutRtl() && buttonGravity == ButtonGravity.START ||
                isLayoutRtl() && buttonGravity == ButtonGravity.END;
    }

    private OnCheckedChangeListener onCheckedChangeListener;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };
    private static final int[] INDETERMINATE_STATE_SET = {
            R.attr.carbon_state_indeterminate
    };

    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public boolean performClick() {
        toggle();

        if (onCheckedChangeListener != null)
            onCheckedChangeListener.onCheckedChanged(this, isChecked());

        final boolean handled = super.performClick();
        if (!handled) {
            // View only makes a sound effect if the onClickListener was
            // called, so we'll need to make one here instead.
            playSoundEffect(SoundEffectConstants.CLICK);
        }

        return handled;
    }

    @ViewDebug.ExportedProperty
    public boolean isChecked() {
        return checkedState == CheckableDrawable.CheckedState.CHECKED;
    }

    public boolean isIndeterminate() {
        return checkedState == CheckableDrawable.CheckedState.INDETERMINATE;
    }

    /**
     * <p>Changes the checked state of this button.</p>
     *
     * @param checked true to check the button, false to uncheck it
     */
    public void setChecked(boolean checked) {
        setChecked(checked ? CheckableDrawable.CheckedState.CHECKED : CheckableDrawable.CheckedState.UNCHECKED);
    }

    /**
     * <p>Changes the checked state of this button.</p>
     *
     * @param state
     */
    public void setChecked(CheckableDrawable.CheckedState state) {
        if (this.checkedState != state) {
            checkedState = state;
            refreshDrawableState();
        }
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onCheckedChangeListener = listener;
    }

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    public interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(CheckBox buttonView, boolean isChecked);
    }

    /**
     * Set the button graphic to a given Drawable
     *
     * @param d The Drawable to use as the button graphic
     */
    public void setButtonDrawable(Drawable d) {
        if (drawable != d) {
            if (drawable != null) {
                drawable.setCallback(null);
                unscheduleDrawable(drawable);
            }

            drawable = d;

            if (d != null) {
                d.setCallback(this);
                //d.setLayoutDirection(getLayoutDirection());
                if (d.isStateful()) {
                    d.setState(getDrawableState());
                }
                d.setVisible(getVisibility() == VISIBLE, false);
                setMinHeight(d.getIntrinsicHeight());
                updateButtonTint();
            }
        }
    }

    public ButtonGravity getButtonGravity() {
        return buttonGravity;
    }

    public void setButtonGravity(ButtonGravity buttonGravity) {
        this.buttonGravity = buttonGravity;
    }

    @Override
    public void setTintList(ColorStateList list) {
        super.setTintList(list);
        updateButtonTint();
    }

    @Deprecated
    public void setTint(@Nullable ColorStateList list) {
        super.setTintList(list);
        updateButtonTint();
    }

    @Override
    public void setTint(int color) {
        setTintList(ColorStateList.valueOf(color));
    }

    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        super.setTintMode(mode);
        updateButtonTint();
    }

    private void updateButtonTint() {
        if (drawable != null) {
            drawable = drawable.mutate();

            if (tint != null && tintMode != null) {
                Carbon.setTintList(drawable, tint);
                Carbon.setTintMode(drawable, tintMode);
            } else {
                Carbon.setTintList(drawable, null);
            }

            // The drawable (or one of its children) may not have been
            // stateful before applying the tint, so let's try again.
            if (drawable.isStateful())
                drawable.setState(getDrawableState());
        }
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(CheckBox.class.getName());
        event.setChecked(isChecked());
    }

    /*@Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CheckBox.class.getName());
        info.setCheckable(true);
        info.setChecked(mChecked);
    }*/

    @Override
    public int getCompoundPaddingLeft() {
        int padding = super.getCompoundPaddingLeft();
        if (isButtonOnTheLeft()) {
            final Drawable buttonDrawable = drawable;
            if (buttonDrawable != null) {
                padding += buttonDrawable.getIntrinsicWidth() + drawablePadding;
            }
        }
        return padding;
    }

    @Override
    public int getCompoundPaddingRight() {
        int padding = super.getCompoundPaddingRight();
        if (!isButtonOnTheLeft()) {
            final Drawable buttonDrawable = drawable;
            if (buttonDrawable != null) {
                padding += buttonDrawable.getIntrinsicWidth() + drawablePadding;
            }
        }
        return padding;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final Drawable buttonDrawable = drawable;
        if (buttonDrawable != null) {
            final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
            final int drawableHeight = buttonDrawable.getIntrinsicHeight();
            final int drawableWidth = buttonDrawable.getIntrinsicWidth();

            final int top;
            switch (verticalGravity) {
                case Gravity.BOTTOM:
                    top = getHeight() - drawableHeight;
                    break;
                case Gravity.CENTER_VERTICAL:
                    top = (getHeight() - drawableHeight) / 2;
                    break;
                default:
                    top = 0;
            }
            final int bottom = top + drawableHeight;
            final int left = isButtonOnTheLeft() ? getPaddingLeft() : getWidth() - drawableWidth - getPaddingRight();
            final int right = isButtonOnTheLeft() ? drawableWidth + getPaddingLeft() : getWidth() - getPaddingRight();

            buttonDrawable.setBounds(left, top, right, bottom);

            final Drawable background = getBackground();
            if (background != null && background instanceof RippleDrawable) {
                //TODO: hotspotBounds
                // ((RippleDrawable)background).setHotspotBounds(left, top, right, bottom);
            }
        }

        super.onDraw(canvas);

        if (buttonDrawable != null) {
            final int scrollX = getScrollX();
            final int scrollY = getScrollY();
            if (scrollX == 0 && scrollY == 0) {
                buttonDrawable.draw(canvas);
            } else {
                canvas.translate(scrollX, scrollY);
                buttonDrawable.draw(canvas);
                canvas.translate(-scrollX, -scrollY);
            }
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        if (isIndeterminate()) {
            mergeDrawableStates(drawableState, INDETERMINATE_STATE_SET);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (drawable != null) {
            int[] myDrawableState = getDrawableState();

            // Set the state of the Drawable
            drawable.setState(myDrawableState);
        }
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return super.verifyDrawable(who) || who == drawable;
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (drawable != null) drawable.jumpToCurrentState();
    }

    static class SavedState extends BaseSavedState {
        CheckableDrawable.CheckedState checked;

        /**
         * Constructor called from {@link CompoundButton#onSaveInstanceState()}
         */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            checked = CheckableDrawable.CheckedState.values()[in.readInt()];
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(checked.ordinal());
        }

        @Override
        public String toString() {
            return "CheckBox.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " checked=" + checked + "}";
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);

        ss.checked = checkedState;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
        requestLayout();
    }
}
