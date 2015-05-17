package wimmity.rsstraining;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wimmity.rsstraining.ITPro.ITProRssReader;
import wimmity.rsstraining.NikkeMedical.MedicalRssReader;

/**
 * Created by teru123123 on 15/05/11.
 *
 *
 *
 *  Copyright 2013 Tim Cocks

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 *
 */


public class CustomFragmentAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 2;
    private Fragment fragment;

    private String[] title = {"ITPro", "Medical"};

    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                fragment = new ITProRssReader();
                break;
            case 1:
                fragment = new MedicalRssReader();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
