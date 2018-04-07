package com.vanxnf.photovalley.features.Account.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.widget.SubmitButton.SubmitButton;
import com.vanxnf.photovalley.widget.TextEdit.ExtendedEditText;


/**
 * Created by VanXN on 18/3/22.
 */
public class LoginFragment extends BaseFragment {

    private ExtendedEditText mEtAccount;
    private ExtendedEditText mEtPassword;
    private SubmitButton mBtnLogin;
    private TextView mTvRegister;
    private boolean isLoginSuccess;
    private DrawerLayout parentDrawerLayout;
    private OnLoginSuccessListener mOnLoginSuccessListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginSuccessListener) {
            mOnLoginSuccessListener = (OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        parentDrawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        isLoginSuccess = false;
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(final View view) {
        mEtAccount = (ExtendedEditText) view.findViewById(R.id.et_account);
        mEtPassword = (ExtendedEditText) view.findViewById(R.id.et_password);
        mBtnLogin = (SubmitButton) view.findViewById(R.id.btn_login);
        mTvRegister = (TextView) view.findViewById(R.id.tv_register);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAccount = mEtAccount.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(strAccount.trim())) {
                    Toast.makeText(_mActivity, R.string.error_username, Toast.LENGTH_SHORT).show();
                    mBtnLogin.reset();
                    return;
                }
                if (TextUtils.isEmpty(strPassword.trim())) {
                    Toast.makeText(_mActivity, R.string.error_pwd, Toast.LENGTH_SHORT).show();
                    mBtnLogin.reset();
                    return;
                }
                // 登录成功
                isLoginSuccess = true;
                mBtnLogin.doResult(true);
                setAccountStatus(true);
                mOnLoginSuccessListener.onLoginSuccess(strAccount);

            }
        });
        mBtnLogin.setOnResultEndListener(new SubmitButton.OnResultEndListener() {
            @Override
            public void onResultEnd() {
                if (isLoginSuccess) {
                    parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    pop();
                }
            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(RegisterFragment.newInstance());
            }
        });
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String account);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @Override
    public boolean onBackPressedSupport() {
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }
}
