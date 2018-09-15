package com.phanduy.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.phanduy.coinhub.R;
import com.phanduy.utils.FontTypeface;
import com.rengwuxian.edittext.MaterialEditText;

import java.util.regex.Pattern;

public class MultichoiceDialogEdittext extends MaterialEditText {

    private static String[] dayDisplay, dayToServer;

    public String textToServer = "";

    public MultichoiceDialogEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(dayDisplay == null) {
            dayDisplay = context.getResources().getStringArray(R.array.dayOfWeekDisplay);
        }

        if(dayToServer == null) {
            dayToServer = context.getResources().getStringArray(R.array.dayOfWeeksToServer);
        }

        init(context, attrs, 0);
    }

    // public ButtonFlatWithFont(Context context, AttributeSet attrs, int
    // defStyle) {
    // super(context, attrs, defStyle);
    // mContext = context;
    // init(attrs, defStyle);
    // }

    private void init(Context context, AttributeSet attrs, int defStyle) {


        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.font, defStyle, 0);
        int fontType = a.getInteger(R.styleable.font_type, 0);
        a.recycle();
        Typeface robotoFont = FontTypeface.getTypecace((Activity) context, FontRules.fontNames[fontType]);
        if (robotoFont != null) {
            setTypeface(robotoFont);
        }

        setTextColor(context.getResources().getColor(FontRules.colors[fontType]));
//		setAlpha(FontRules.alphas[fontType]);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, FontRules.textSizes[fontType]);

    }

    public String getTextToServer() {
        return textToServer;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            showDialog();

        return super.onTouchEvent(event);
    }

    public void showDialog() {

        Integer[] selected = null;
        String text = getText().toString().trim();
        if(!text.isEmpty()) {
            String[] arraySchedule = text.split(Pattern.quote("-"));
            selected = new Integer[arraySchedule.length];
            for (int i = 0, length = arraySchedule.length; i < length; i++) {
                for (int j = 0, l = dayDisplay.length; j < l; j++) {
                    if (dayDisplay[j].equals(arraySchedule[i])) {
                        selected[i] = j;
                        break;
                    }
                }
            }
        }

        new MaterialDialog.Builder(getContext())
                .title(R.string.txtLabelDialogChoseDay)
                .items(R.array.dayOfWeekDisplayDialog)
                .positiveText(R.string.labelAccept)
                .negativeText(R.string.labelCancel)
                .negativeColor(getContext().getResources().getColor(R.color.black54))
                .itemsCallbackMultiChoice(selected, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        onChose(which);
                        return true;
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    }
                })
                .show();
    }

    public void onChose(Integer[] which) {
        String s = "";
        if (which == null) {
            setText("");
            return;
        }
        for (Integer i : which) {
            if (s.length() == 0) {
                s += dayDisplay[i];
                textToServer += dayToServer[i];
            } else {
                s += "-" + dayDisplay[i];
                textToServer += "-" + dayToServer[i];
            }
        }

        setText(s);
    }

    public void onChose(CharSequence[] text) {
        String s = "";
        if (text == null) {
            setText("");
            return;
        }
        for (CharSequence i : text) {
            if (s.length() == 0) {
                s += i;
            } else {
                s += "-" + i;
            }
        }

        setText(s);
    }

}
