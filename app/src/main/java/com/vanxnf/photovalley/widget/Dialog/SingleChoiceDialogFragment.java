package com.vanxnf.photovalley.widget.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;


public class SingleChoiceDialogFragment extends DialogFragment {

    private String title;
    private String[] items;
    private DialogInterface.OnClickListener onClickListener;
    private int checkedItem;

    public void show(String title, String[] items, int checkedItem, DialogInterface.OnClickListener onClickListener,
                     FragmentManager fragmentManager) {
        this.title = title;
        this.items = items;
        this.onClickListener = onClickListener;
        this.checkedItem = checkedItem;

        show(fragmentManager, "SingleChoiceDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setSingleChoiceItems(items, checkedItem, onClickListener);
        return builder.create();
    }

}
