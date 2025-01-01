package sgg.study.sggdemo.TabLayout.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private final String title;
    private final String content;
    Context mContext;
    TextView textView;

    public MyFragment(String title,String content){
        super();
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public Context getmContext() {
        return mContext;
    }

    /*构建Fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    /*创建视图*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       textView = new TextView(mContext);
       textView.setTextColor(Color.RED);
       textView.setTextSize(25);
       textView.setGravity(Gravity.CENTER);

        return textView;
    }

    /*绑定数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置内容
        textView.setText(content);


    }
}
