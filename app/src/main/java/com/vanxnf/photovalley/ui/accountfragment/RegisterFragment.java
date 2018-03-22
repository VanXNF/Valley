package com.vanxnf.photovalley.ui.accountfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.widget.Button.SubmitButton;
import com.vanxnf.photovalley.widget.TextEdit.ExtendedEditText;

/**
 * Created by VanXN on 18/3/22.
 */
public class RegisterFragment extends BaseFragment {
    private ExtendedEditText mEtAccount;
    private ExtendedEditText mEtPassword;
    private ExtendedEditText mEtRepeatPwd;
    private SubmitButton mBtnRegister;
    private boolean isRegisterSuccess;
    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        isRegisterSuccess = false;
        return view;
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

                // 注册成功
                mOnLoginSuccessListener.onLoginSuccess(strAccount);
                isRegisterSuccess = true;
                setAccountStatus(true);
                mBtnRegister.doResult(true);
            }
        });
        mBtnRegister.setOnResultEndListener(new SubmitButton.OnResultEndListener() {
            @Override
            public void onResultEnd() {
                if (isRegisterSuccess) {
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
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }
}