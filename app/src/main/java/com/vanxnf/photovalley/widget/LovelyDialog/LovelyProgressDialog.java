package com.vanxnf.photovalley.widget.LovelyDialog;

import android.app.AlertDialog;
import android.content.Context;

import com.vanxnf.photovalley.R;

/**
 * Created by yarolegovich on 16.04.2016.
 */
public class LovelyProgressDialog extends AbsLovelyDialog<LovelyProgressDialog> {

    public LovelyProgressDialog(Context context) {
        super(context);
    }

    public LovelyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    {
        setCancelable(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }
}
