package com.vanxnf.photovalley.features.Setting.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.Setting.Adapter.SettingAdapter;
import com.vanxnf.photovalley.features.Setting.Util.SettingDataUtil;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;
import com.vanxnf.photovalley.widget.Dialog.SingleChoiceDialogFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by VanXN on 2018/3/17.
 */

public class SettingFragment extends BaseMainFragment {

    private RecyclerView mRecycler;
    private SettingAdapter mSAdapter;
    private List<Integer> mIconIds = new ArrayList<>();
    private List<Integer> mTitleIds = new ArrayList<>();
    private View view;


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable final Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mSAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (position == 0) {
                    //主题
                    showThemeDialog();
                } else if (position == 1) {
                    //语言
                    showLanguageDialog();
                } else if (position == 2) {
                    //启动默认显示页
                    showStartPageDialog();
                }
            }
        });
        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                Collections.addAll(mIconIds, SettingDataUtil.IconIds);
                Collections.addAll(mTitleIds, SettingDataUtil.TitleIds);
                mSAdapter.setData(mIconIds, mTitleIds);
            }
        });
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.setting);
//        getActivity().openOptionsMenu();
        initToolbarNav(mToolbar);

        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_setting);
        mSAdapter = new SettingAdapter(_mActivity);
        mRecycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecycler.setAdapter(mSAdapter);

    }

    private void showThemeDialog() {
        final SingleChoiceDialogFragment themeDialog = new SingleChoiceDialogFragment();
        String[] items = {getString(R.string.light_theme), getString(R.string.dark_theme)};
        final int ThemeTag = getThemeTag();
        themeDialog.show(getString(R.string.theme), items, ThemeTag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isNeedChangeTheme = false;
                if (which == 0) {
                    if (ThemeTag == 1) {
                        setThemeTag(0);
                        isNeedChangeTheme = true;
                    } else {
                        themeDialog.dismiss();
                    }
                } else if (which == 1) {
                    if (ThemeTag == 0) {
                        setThemeTag(1);
                        isNeedChangeTheme = true;
                    } else {
                        themeDialog.dismiss();
                    }
                }
                if (isNeedChangeTheme) {
                    //重启Activity
                    Intent intent = getActivity().getIntent();
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
                }
            }
        }, getFragmentManager());
    }

    private void showLanguageDialog() {
        final SingleChoiceDialogFragment languageDialog = new SingleChoiceDialogFragment();
        String[] items = {getString(R.string.auto_language), getString(R.string.zh_cn), getString(R.string.en_us)};
        final int LanguageTag = getLanguageTag();
        languageDialog.show(getString(R.string.language), items, LanguageTag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isNeedChangeLanguage = false;
                if (which == 0) {
                    if (LanguageTag == 1 || LanguageTag == 2) {
                        setLanguageTag(0);
                        isNeedChangeLanguage = true;
                    } else {
                        languageDialog.dismiss();
                    }
                } else if (which == 1) {
                    if (LanguageTag == 0 || LanguageTag == 2) {
                        setLanguageTag(1);
                        isNeedChangeLanguage = true;
                    } else {
                        languageDialog.dismiss();
                    }
                } else if (which == 2) {
                    if (LanguageTag == 0 || LanguageTag == 1) {
                        setLanguageTag(2);
                        isNeedChangeLanguage = true;
                    } else {
                        languageDialog.dismiss();
                    }
                }
                if (isNeedChangeLanguage) {
                    //重启Activity
                    Intent intent = getActivity().getIntent();
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
                }
            }
        }, getFragmentManager());
    }

    private void showStartPageDialog() {
        final SingleChoiceDialogFragment startPageDialog = new SingleChoiceDialogFragment();
        String[] items = {getString(R.string.square), getString(R.string.recommend), getString(R.string.filter)};
        final int PageTag = getStartPageTag();
        startPageDialog.show(getString(R.string.start_page), items, PageTag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (PageTag == 1 || PageTag == 2) {
                        setStartPageTag(0);
                    }
                } else if (which == 1) {
                    if (PageTag == 0 || PageTag == 2) {
                        setStartPageTag(1);
                    }
                } else if (which == 2) {
                    if (PageTag == 0 || PageTag == 1) {
                        setStartPageTag(2);
                    }
                }
                startPageDialog.dismiss();

            }
        }, getFragmentManager());
    }
}
