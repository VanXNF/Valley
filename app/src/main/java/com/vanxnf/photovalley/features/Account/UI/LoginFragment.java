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
public class LoginFragment extends BaseFragment {

    private ExtendedEditText mEtAccount;
    private ExtendedEditText mEtPassword;
    private SubmitButton mBtnLogin;
    private TextView mTvRegister;
    private boolean isLoginSuccess;
    private DrawerLayout parentDrawerLayout;
    private OnLoginSuccessListener mOnLoginSuccessListener;
    private Account account;
    private Call callForLogin;
    private String token = null;

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
        mEtAccount = view.findViewById(R.id.et_account);
        mEtPassword = view.findViewById(R.id.et_password);
        mBtnLogin = view.findViewById(R.id.btn_login);
        mTvRegister = view.findViewById(R.id.tv_register);

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

                account = new Account(strAccount, strPassword);

                //Ali yun "http://120.79.162.134:80/api" 本地 "http://192.168.4.73:80/api"
                callForLogin = HttpUtil.sendOkHttpRequest("login", account, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mBtnLogin.reset();
                                Toast.makeText(_mActivity, getString(R.string.sign_in_failed), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseData = response.body().string();
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
                                    setToken(token);
                                    isLoginSuccess = true;
                                    mBtnLogin.doResult(true);
                                    setAccountStatus(true);
                                    mOnLoginSuccessListener.onLoginSuccess(account.getUserName());
                                }
                            });
                        } else if (status.equals("Error Username or Password")) {

                            post(new Runnable() {
                                @Override
                                public void run() {
                                    mBtnLogin.reset();
                                    Toast.makeText(_mActivity, getString(R.string.wrong_in_name_or_pwd), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

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
        if (callForLogin != null) {
            callForLogin.cancel();
            callForLogin = null;
        } else {
            parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            pop();
        }
        return true;
    }
}
