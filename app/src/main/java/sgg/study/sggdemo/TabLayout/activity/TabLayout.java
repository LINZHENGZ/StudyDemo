package sgg.study.sggdemo.TabLayout.activity;

import android.annotation.SuppressLint;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import sgg.study.sggdemo.R;
import sgg.study.sggdemo.TabLayout.adapter.ViewPagerAdapter;
import sgg.study.sggdemo.TabLayout.fragment.MyFragment;

import java.util.ArrayList;

public class TabLayout extends AppCompatActivity {

    private static final int MODE_SCROLLABLE = 4 ;

    ViewPager mviewpage;

    TextView tv_title;

    ArrayList<MyFragment> fragments;

    ViewPagerAdapter mAdapter;

    com.google.android.material.tabs.TabLayout mtabLayout;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        mviewpage = findViewById(R.id.viewpage);
        tv_title = (TextView) findViewById(R.id.title);

        tv_title.setText("TabLayout的使用");

        mtabLayout = findViewById(R.id.mtabLayout);

        //初始化数据
        fragments = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            fragments.add(new MyFragment("标题" + i,"内容" + i));
        }

        //设置ViewPager的适配器
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        mviewpage.setAdapter(mAdapter);

        //关联ViewPager
        mtabLayout.setupWithViewPager(mviewpage);
        mtabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }
}