package com.vanxnf.photovalley.features.Home.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.PhotoPickerActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Util.HttpUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class PublishFragment extends BaseFragment {

    private DrawerLayout parentDrawerLayout;
    private View view;
    private ImageView publishPhoto;
    private EditText editText;
    private FloatingActionButton btnCheck;
    private String uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_publish, container, false);
        parentDrawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initView();
        return view;
    }

    private void initView() {
        btnCheck = (FloatingActionButton) view.findViewById(R.id.action_check_publish);
        editText = (EditText) view.findViewById(R.id.publish_description);
        publishPhoto = (ImageView) view.findViewById(R.id.publish_photo);
        publishPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(getContext(), PhotoPickerActivity.class);
                    startActivityForResult(intent, 0);
                }

            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(uri)) {
                    SnackbarUtils.Short(view, getString(R.string.please_choose_photo)).info().show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("PhotoUri", uri);
                    bundle.putString("PhotoDescription", editText.getText().toString().trim());
                    setFragmentResult(RESULT_OK, bundle);
                    parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    pop();
                }
            }
        });
    }


    public static PublishFragment newInstance() {
        return new PublishFragment();
    }

    @Override
    public boolean onBackPressedSupport() {
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK && data != null) {
                uri = data.getStringExtra(PhotoPickerActivity.SELECT_RESULTS);
                Glide.with(view).load(uri).into(publishPhoto);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(getContext(), PhotoPickerActivity.class);
                startActivityForResult(intent, 0);
            } else {
                SnackbarUtils.Short(view, getString(R.string.please_grant_permission)).info().show();
            }
        }
    }
}
