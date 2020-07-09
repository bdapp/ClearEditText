package com.example.myapplication.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;

/**
 * @Info 带清除按钮的EditText
 * @Author Bello
 * @Time 20-7-8 下午2:38
 * @Ver
 */
public class ClearEditView extends FrameLayout {
    private final String TAG = "ClearEditView";
    private ImageView _leftImg;
    private ImageView _rightImg;
    private EditText _editText;

    // 左右图标内padding距离
    private int imgPaddingVal = 10;
    // 默认字体大小
    private int defaultTextSize = 16;
    private int resBackground;
    private int resLeftDrawable ;
    private int resRightDrawable;
    private boolean resLeftShow;
    private boolean resRightShow;
    private String resHint;
    private String resText;
    private int resHintColor = Color.parseColor("#cdcdcd");
    private int resTextColor = Color.parseColor("#333333");
    private int resEditPaddingLeft;
    private int resEditPaddingRight;
    private int resEditPaddingTop;
    private int resEditPaddingBottom;

    public ClearEditView(Context context) {
        this(context, null);
    }

    public ClearEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (null != attrs) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ClearEditView);
            resBackground = ta.getResourceId(R.styleable.ClearEditView_background, Color.TRANSPARENT);
            resLeftDrawable = ta.getResourceId(R.styleable.ClearEditView_left_drawable, R.drawable.ic_clr_left);
            resRightDrawable = ta.getResourceId(R.styleable.ClearEditView_right_drawable, R.drawable.ic_clr_right);
            resHintColor = ta.getColor(R.styleable.ClearEditView_hint_color, resHintColor);
            resTextColor = ta.getColor(R.styleable.ClearEditView_text_color, resTextColor);
            resLeftShow = ta.getBoolean(R.styleable.ClearEditView_left_show, false);
            resRightShow = ta.getBoolean(R.styleable.ClearEditView_right_show, false);
            resHint = ta.getString(R.styleable.ClearEditView_hint);
            resText = ta.getString(R.styleable.ClearEditView_text);
            defaultTextSize = px2dip(ta.getDimension(R.styleable.ClearEditView_text_size, 0));
            if (defaultTextSize == 0) {
                defaultTextSize = 16;
            }
            resEditPaddingLeft = px2dip(ta.getDimension(R.styleable.ClearEditView_text_padding_left, 0));
            resEditPaddingRight = px2dip(ta.getDimension(R.styleable.ClearEditView_text_padding_right, 0));
            resEditPaddingTop = px2dip(ta.getDimension(R.styleable.ClearEditView_text_padding_top, 0));
            resEditPaddingBottom = px2dip(ta.getDimension(R.styleable.ClearEditView_text_padding_bottom, 0));

        }

        // 加载布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_clear_edit, this);
        _leftImg = findViewById(R.id.clr_left_image);
        _rightImg = findViewById(R.id.clr_right_image);
        _editText = findViewById(R.id.clr_edit_text);


        // 设置整个view的背景
        view.setBackgroundResource(resBackground);
        // 左侧图标样式
        if (resLeftShow) {
            _leftImg.setVisibility(VISIBLE);
            _leftImg.setImageResource(resLeftDrawable);
            ViewGroup.LayoutParams p = _leftImg.getLayoutParams();
            p.height = dip2px(defaultTextSize + imgPaddingVal);
            p.width = dip2px(defaultTextSize + imgPaddingVal);
            _leftImg.setLayoutParams(p);
            _leftImg.setPadding(imgPaddingVal, imgPaddingVal, imgPaddingVal, imgPaddingVal);
        } else {
            _leftImg.setVisibility(GONE);
        }
        // 右侧图标样式
        if (resRightShow) {
            _rightImg.setVisibility(INVISIBLE);
            _rightImg.setImageResource(resRightDrawable);
            ViewGroup.LayoutParams p = _rightImg.getLayoutParams();
            p.height = dip2px(defaultTextSize + imgPaddingVal);
            p.width = dip2px(defaultTextSize + imgPaddingVal);
            _rightImg.setLayoutParams(p);
            _rightImg.setPadding(imgPaddingVal, imgPaddingVal, imgPaddingVal, imgPaddingVal);
        } else {
            _rightImg.setVisibility(GONE);
        }

        // edit的padding
        _editText.setPadding(resEditPaddingLeft, resEditPaddingTop, resEditPaddingRight, resEditPaddingBottom);

        // 设置文字大小
        _editText.setTextSize(defaultTextSize);
        _editText.setText(resText);
        _editText.setTextColor(resTextColor);
        // 设置hint
        _editText.setHint(resHint);
        _editText.setHintTextColor(resHintColor);


        // 点击清除按钮
        _rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _editText.setText("");
                _editText.requestFocus(0);
                _rightImg.setVisibility(INVISIBLE);
            }
        });

        // 输入框焦点变化时
        _editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Editable s = ((EditText) v).getText();
                if (resRightShow){
                    if (hasFocus && s.length() > 0) {
                        _rightImg.setVisibility(VISIBLE);
                    } else {
                        _rightImg.setVisibility(INVISIBLE);
                    }
                }

            }
        });
        // 监听输入框内容变化
        _editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (resRightShow && _editText.hasFocus()) {
                    if (s.length() > 0) {
                        _rightImg.setVisibility(VISIBLE);
                    } else {
                        _rightImg.setVisibility(INVISIBLE);
                    }
                }
            }
        });

    }

    /**
     * 返回内嵌的EditText对象
     * @return
     */
    public EditText getEditView() {
        return _editText;
    }

    /**
     * 设置EditText内容
     * @param str
     */
    public void setText(String str) {
        resText = str;
        _editText.setText(resText);
    }

    /**
     * 获取EditText内容
     * @return
     */
    public String getText() {
        return _editText.getText().toString().trim();
    }

    private int px2dip(float pxValue) {
        float ds = getResources().getDisplayMetrics().density;
        return (int) (pxValue / ds + 0.5f);
    }

    private int dip2px(float dipValue) {
        float ds = getResources().getDisplayMetrics().density;
        return (int) (dipValue * ds + 0.5f);
    }
}
