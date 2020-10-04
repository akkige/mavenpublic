package com.example.softlib.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputConnection;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;


import com.example.softlib.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Number keyboard (to enter pin or custom amount).
 */
@SuppressWarnings("unused")
public class NumberKeyboard extends GridLayout {

    private static final int DEFAULT_KEY_WIDTH_DP = 60;
    private static final int DEFAULT_KEY_HEIGHT_DP = 60;
    private static final int DEFAULT_KEY_TEXT_SIZE_SP = 28;
    // Our communication link to the EditText
    InputConnection inputConnection;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Dimension
    private int keyWidth;
    @Dimension
    private int keyHeight;
    @DrawableRes
    private int numberKeyBackground;
    @Dimension
    private int numberKeyTextSize;
    @ColorRes
    private int numberKeyTextColor;
    @DrawableRes
    private int commaBtnIcon;
    @DrawableRes
    private int commaBtnBackground;
    @DrawableRes
    private int okBtnBackground;
    @ColorRes
    private int okBtnTextColor;
    @DrawableRes
    private int cancelBtnBackground;
    @ColorRes
    private int cancelBtnTextColor;
    @DrawableRes
    private int backBtnIcon;
    @DrawableRes
    private int backBtnBackground;

    private List<TextView> numericKeys;
    private ImageView commaBtn;
    private ImageView backBtn;

    private NumberKeyboardListener listener;

    public NumberKeyboard(@NonNull Context context) {
        super(context);
        inflateView();
    }

    public NumberKeyboard(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
        inflateView();
    }

    public NumberKeyboard(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(attrs);
        inflateView();
    }

    /**
     * Sets keyboard listener.
     */
    public void setListener(NumberKeyboardListener listener) {
        this.listener = listener;
    }


    /**
     * Sets key width in px.
     */
    public void setKeyWidth(int px) {
        for (TextView key : numericKeys) {
            key.getLayoutParams().width = px;
        }
        commaBtn.getLayoutParams().width = px;
        backBtn.getLayoutParams().width = px;
        requestLayout();
    }

    /**
     * Sets key height in px.
     */
    public void setKeyHeight(int px) {
        for (TextView key : numericKeys) {
            key.getLayoutParams().height = px;
        }
        commaBtn.getLayoutParams().width = px;
        backBtn.getLayoutParams().width = px;
        requestLayout();
    }

    /**
     * Sets number keys background.
     */
    public void setNumberKeyBackground(@DrawableRes int background) {
        for (TextView key : numericKeys) {
            key.setBackground(ContextCompat.getDrawable(getContext(), background));
        }
    }

    /**
     * Sets number keys text size.
     */
    public void setNumberKeyTextSize(@Dimension int size) {
        for (TextView key : numericKeys) {
            key.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    /**
     * Sets number keys text color.
     */
    public void setNumberKeyTextColor(@ColorRes int color) {
        for (TextView key : numericKeys) {
            key.setTextColor(ContextCompat.getColorStateList(getContext(), color));
        }
    }

    /**
     * Sets number keys text typeface.
     */
    public void setNumberKeyTypeface(Typeface typeface) {
        for (TextView key : numericKeys) {
            key.setTypeface(typeface);
        }
    }

    /**
     * Sets left auxiliary button background.
     */
    public void setBackButtonBackground(@DrawableRes int bg) {
        backBtn.setBackground(ContextCompat.getDrawable(getContext(), bg));
    }

    /**
     * Sets left auxiliary button icon.
     */
    public void setBackButtonButtonIcon(@DrawableRes int icon) {
        backBtn.setImageResource(icon);
    }

    /**
     * Sets left auxiliary button background.
     */
    public void setCommaButtonBackground(@DrawableRes int bg) {
        commaBtn.setBackground(ContextCompat.getDrawable(getContext(), bg));
    }

    /**
     * Sets left auxiliary button icon.
     */
    public void setCommaButtonIcon(@DrawableRes int icon) {
        commaBtn.setImageResource(icon);
    }

    /**
     * Initializes XML attributes.
     */
    private void initializeAttributes(AttributeSet attrs) {
        TypedArray array = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.NumberKeyboard, 0, 0);
        try {
            // Get keyboard type
            int type = array.getInt(R.styleable.NumberKeyboard_keyboardType, -1);
            if (type == -1) {
                throw new IllegalArgumentException("keyboardType attribute is required.");
            }
            // Get key sizes
            keyWidth = array.getLayoutDimension(R.styleable.NumberKeyboard_keyWidth,
                    dpToPx(DEFAULT_KEY_WIDTH_DP));
            keyHeight = array.getLayoutDimension(R.styleable.NumberKeyboard_keyHeight,
                    dpToPx(DEFAULT_KEY_HEIGHT_DP));
            // Get number key background
            numberKeyBackground = array.getResourceId(R.styleable.NumberKeyboard_numberKeyBackground,
                    R.drawable.key_bg);
            // Get number key text size
            numberKeyTextSize = array.getDimensionPixelSize(R.styleable.NumberKeyboard_numberKeyTextSize,
                    spToPx(DEFAULT_KEY_TEXT_SIZE_SP));
            // Get number key text color
            numberKeyTextColor = array.getResourceId(R.styleable.NumberKeyboard_numberKeyTextColor,
                    R.drawable.key_text_color);
            // Get auxiliary icons

            commaBtnIcon = R.drawable.ic_comma;

            commaBtnBackground = R.drawable.key_bg;

            okBtnBackground = R.drawable.key_ok;

            okBtnTextColor = R.drawable.key_text_color;

            cancelBtnBackground = R.drawable.key_cancel;

            cancelBtnTextColor = R.drawable.key_text_color;

            backBtnIcon = R.drawable.ic_backspace;

            backBtnBackground = R.drawable.key_bg_transparent;


        } finally {
            array.recycle();
        }
    }

    /**
     * Inflates layout.
     */
    private void inflateView() {
        View view = inflate(getContext(), R.layout.number_decimal_keyboard, this);
        // Get numeric keys
        numericKeys = new ArrayList<>(10);
        numericKeys.add((TextView) view.findViewById(R.id.key0));
        numericKeys.add((TextView) view.findViewById(R.id.key1));
        numericKeys.add((TextView) view.findViewById(R.id.key2));
        numericKeys.add((TextView) view.findViewById(R.id.key3));
        numericKeys.add((TextView) view.findViewById(R.id.key4));
        numericKeys.add((TextView) view.findViewById(R.id.key5));
        numericKeys.add((TextView) view.findViewById(R.id.key6));
        numericKeys.add((TextView) view.findViewById(R.id.key7));
        numericKeys.add((TextView) view.findViewById(R.id.key8));
        numericKeys.add((TextView) view.findViewById(R.id.key9));
        // Get auxiliary keys
        commaBtn = view.findViewById(R.id.keyComma);

        backBtn = view.findViewById(R.id.keyBack);

        // Set styles
        //setStyles();
        // Set listeners
        setupListeners();
    }

    /**
     * Set styles.
     */
    private void setStyles() {
        setKeyWidth(keyWidth);
        setKeyHeight(keyHeight);
        setNumberKeyBackground(numberKeyBackground);
        setNumberKeyTextSize(numberKeyTextSize);
        setNumberKeyTextColor(numberKeyTextColor);
        setCommaButtonIcon(commaBtnIcon);
        setCommaButtonBackground(commaBtnBackground);
        setBackButtonButtonIcon(backBtnIcon);
        setBackButtonBackground(backBtnBackground);
    }

    /**
     * Setup on click listeners.
     */
    private void setupListeners() {
        // Set number callbacks
        for (int i = 0; i < numericKeys.size(); i++) {
            final TextView key = numericKeys.get(i);
            final int number = i;
            key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onNumberClicked(number);
                    }
                }
            });
        }
        // Set auxiliary key callbacks
        commaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCommaButtonClicked();
                }
            }
        });

        // Set auxiliary key callbacks
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onBackButtonClicked();
                }
            }
        });

    }

    /**
     * Utility method to convert dp to pixels.
     */
    public int dpToPx(float valueInDp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, getResources().getDisplayMetrics());
    }

    /**
     * Utility method to convert sp to pixels.
     */
    public int spToPx(float valueInSp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInSp, getResources().getDisplayMetrics());
    }
}
