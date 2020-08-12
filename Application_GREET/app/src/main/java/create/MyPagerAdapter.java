package create;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import main.Page;

public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //position위치를 포함한 앞뒤의 fragment를 생성하는 메서드
        Page fragment = new Page();
        fragment.setPosition(position);
        return fragment;
    }

    @Override
    public int getCount() {
        //개발자가 사용할 뷰페이지의 총 갯수
        return 7;
    }
}