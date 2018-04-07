package com.vanxnf.photovalley.features.Setting.UI;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.Setting.Adapter.SettingAdapter;
import com.vanxnf.photovalley.features.Setting.Entity.SettingItem;
import com.vanxnf.photovalley.features.Setting.Util.ClearUtil;
import com.vanxnf.photovalley.features.Setting.Util.ItemUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.widget.Dialog.SingleChoiceDialogFragment;

import java.util.List;

/**
 * Created by VanXN on 2018/3/17.
 */

public class SettingFragment extends BaseMainFragment {

    private RecyclerView mRecycler;
    private SettingAdapter mSAdapter;
    private List<SettingItem> itemData;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        return view;
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.setting);
        initToolbarNav(mToolbar);

        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_setting);
        mRecycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        itemData = ItemUtil.getSettingItemData();
        mSAdapter = new SettingAdapter(_mActivity, itemData);
        mSAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //主题
                        showThemeDialog();
                        break;
                    case 1:
                        //语言
                        showLanguageDialog();
                        break;
                    case 2:
                        //启动默认显示页
                        showStartPageDialog();
                        break;
                    case 3:
                        //清理图片缓存
                        clearPicCache();
                        break;
                }
            }
        });
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

    private void clearPicCache() {
        ClearUtil.clearCache(_mActivity);
        SnackbarUtils.Short(view, getString(R.string.clear_success)).info().show();
    }
}
