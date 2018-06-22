package com.vanxnf.photovalley.features.Account.UI;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Account.Entity.Account;
import com.vanxnf.photovalley.features.Account.Util.HttpUtil;
import com.vanxnf.photovalley.widget.SubmitButton.SubmitButton;
import com.vanxnf.photovalley.widget.TextEdit.ExtendedEditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by VanXN on 18/3/22.
 */
public class RegisterFragment extends BaseFragment {
    private ExtendedEditText mEtAccount;
    private ExtendedEditText mEtPassword;
    private ExtendedEditText mEtRepeatPwd;
    private SubmitButton mBtnRegister;
    private boolean isRegisterSuccess;
    private DrawerLayout parentDrawerLayout;
    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;
    private String token;
    private Account account;
    private Call call;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.OnLoginSuccessListener) {
            mOnLoginSuccessListener = (LoginFragment.OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        parentDrawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        isRegisterSuccess = false;
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mEtAccount = (ExtendedEditText) view.findViewById(R.id.et_account);
        mEtPassword = (ExtendedEditText) view.findViewById(R.id.et_password);
        mEtRepeatPwd = (ExtendedEditText) view.findViewById(R.id.et_repeat_password);
        mBtnRegister = (SubmitButton) view.findViewById(R.id.btn_register);
        showSoftInput(mEtAccount);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAccount = mEtAccount.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                String strPasswordConfirm = mEtRepeatPwd.getText().toString();
                if (TextUtils.isEmpty(strAccount.trim())) {
                    Toast.makeText(_mActivity, R.string.error_username, Toast.LENGTH_SHORT).show();
                    mBtnRegister.reset();
                    return;
                }
                if (TextUtils.isEmpty(strPassword.trim()) || TextUtils.isEmpty(strPasswordConfirm.trim())) {
                    Toast.makeText(_mActivity, R.string.error_pwd, Toast.LENGTH_SHORT).show();
                    mBtnRegister.reset();
                    return;
                }
                if (!strPassword.trim().equals(strPasswordConfirm.trim())) {
                    Toast.makeText(_mActivity, R.string.confirm_pwd_error, Toast.LENGTH_SHORT).show();
                    mBtnRegister.reset();
                    return;
                }

                account = new Account(strAccount, strPassword);

                call = HttpUtil.sendOkHttpRequest("register", account, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mBtnRegister.reset();
                                Toast.makeText(_mActivity, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        //获取服务器返回的Json数据
                        Gson gson = new Gson();

                        String status = null;

                        Map<String, String> map = gson.fromJson(responseData, HashMap.class);

                        for (String key : map.keySet()) {
                            if (key.equals("status")) {
                                status = new String(map.get(key));
                            }
                            if (key.equals("token")) {
                                token = new String(map.get(key));
                            }
                        }

                        if (status.equals("OK")) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    // 注册成功
                                    setToken(token);
                                    mOnLoginSuccessListener.onLoginSuccess(account.getUserName());
                                    isRegisterSuccess = true;
                                    setAccountStatus(true);
                                    mBtnRegister.doResult(true);
                                }
                            });
                        } else if (status.equals("Username Already Existed")) {

                            post(new Runnable() {
                                @Override
                                public void run() {
                                    mBtnRegister.reset();
                                    Toast.makeText(_mActivity, "用户名已存在，请登录", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });


            }
        });
        mBtnRegister.setOnResultEndListener(new SubmitButton.OnResultEndListener() {
            @Override
            public void onResultEnd() {
                if (isRegisterSuccess) {
                    parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    popTo(LoginFragment.class, true);
                }
            }
        });
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (call != null) {
            call.cancel();
            call = null;
        } else {
            parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            pop();
        }

        return true;
    }
}