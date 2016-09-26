/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.umes.jeb.hww.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.dominio.SensorType;
import com.umes.jeb.hww.view.bean.HomeBean;
import com.umes.jeb.hww.view.res.SlidingTabLayout;
import com.umes.jeb.hww.view.res.TabPagerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic sample which shows how to use {@link com.umes.jeb.hww.view.res.SlidingTabLayout}
 * to display a custom {@link ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 */
public class HomeTabsColorsFragment extends Fragment {

    private List<HomeBean> listHomeBean;

    /**
     * This class represents a tab to be displayed by {@link ViewPager} and it's associated
     * {@link SlidingTabLayout}.
     */

    static final String LOG_TAG = "SlidingTabsColorsFragment";

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    /**
     * List of {@link TabPagerItem} which represent this sample's tabs.
     */
    private List<TabPagerItem> mTabs = new ArrayList<TabPagerItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // BEGIN_INCLUDE (populate_tabs)
        /**
         * Populate our tab list with tabs. Each item contains a title, indicator color and divider
         * color, which are used by {@link SlidingTabLayout}.
         */

        for(HomeBean categorias : listHomeBean){
            mTabs.add(new TabPagerItem(
                    categorias.getNombre(), // Title
                    getResources().getColor(R.color.accent), // Indicator color
                    Color.GRAY // Divider color
            ));
        }
        /*mTabs.add(new TabPagerItem(
                getString(R.string.app_tab_view_dashboard), // Title
                getResources().getColor(R.color.accent), // Indicator color
                Color.GRAY // Divider color
        ));

        mTabs.add(new TabPagerItem(
                getString(R.string.app_tab_view_pulse_monitor), // Title
                getResources().getColor(R.color.accent), // Indicator color
                Color.GRAY // Divider color
        ));

        mTabs.add(new TabPagerItem(
                getString(R.string.app_tab_view_breath_monitor), // Title
                getResources().getColor(R.color.accent), // Indicator color
                Color.GRAY // Divider color
        ));*/
        // END_INCLUDE (populate_tabs)
    }

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_tab, container, false);
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)

    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     * <p/>
     * We set the {@link ViewPager}'s adapter to be an instance of
     * {@link SampleFragmentPagerAdapter}. The {@link SlidingTabLayout} is then given the
     * {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        // BEGIN_INCLUDE (tab_colorizer)
        // Set a TabColorizer to customize the indicator and divider colors. Here we just retrieve
        // the tab at the position, and return it's set color
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mTabs.get(position).getDividerColor();
            }

        });
        // END_INCLUDE (tab_colorizer)
        // END_INCLUDE (setup_slidingtablayout)
    }
    // END_INCLUDE (fragment_onviewcreated)

    /**
     * The {@link FragmentPagerAdapter} used to display pages in this sample. The individual pages
     * are instances of {@link ContentFragment} which just display three lines of text. Each page is
     * created by the relevant {@link TabPagerItem} for the requested position.
     * <p/>
     * The important section of this class is the {@link #getPageTitle(int)} method which controls
     * what is displayed in the {@link SlidingTabLayout}.
     */
    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the {@link android.support.v4.app.Fragment} to be displayed at {@code position}.
         * <p/>
         * Here we return the Fragment corresponding
         */
        @Override
        public Fragment getItem(int i) {
            //return mTabs.get(i).createFragment();
            HomeBean bean = getHomeBean(i);
            if (bean.getSensorBean().getType()==null) {
                DashboardFragment fragment = new DashboardFragment();
                fragment.setHomeBean(bean);
                return fragment;
            }else if(bean.getSensorBean().getType() == SensorType.PO){
                PulseOxygenFragment fragment = new PulseOxygenFragment();
                fragment.setHomeBean(bean);
                return fragment;
            }else if(bean.getSensorBean().getType() == SensorType.BS){
                BreathFragment fragment = new BreathFragment();
                fragment.setHomeBean(bean);
                return fragment;
            }else if(bean.getSensorBean().getType() == SensorType.TP){
                TemperatureFragment fragment = new TemperatureFragment();
                fragment.setHomeBean(bean);
                return fragment;
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we return the value returned from {@link TabPagerItem#getTitle()}.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }
        // END_INCLUDE (pageradapter_getpagetitle)

    }

    protected HomeBean getHomeBean(Integer i){
        return listHomeBean.get(i);
    }

    public List<HomeBean> getListHomeBean() {
        return listHomeBean;
    }

    public void setListHomeBean(List<HomeBean> listHomeBean) {
        this.listHomeBean = listHomeBean;
    }
}