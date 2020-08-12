package main;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.four.application_greet.R;

import create.SelectThemeActivity;

public class Page extends Fragment {
    //각각의 Fragment가 하나로 연결되어 슬라이드를 통해 페이징 전환이 가능하도록 처리
    int position;

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = null;
        String company = (SelectThemeActivity.company).toString();
        String name = (SelectThemeActivity.name).toString();
        String job = (SelectThemeActivity.job).toString();
        String tel = (SelectThemeActivity.tel).toString();
        String email = (SelectThemeActivity.email).toString();
        String address = (SelectThemeActivity.address).toString();
        Bitmap image=(SelectThemeActivity.bitmap);

        if (position == 0) {
            layout = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample1, container, false);

            TextView text_1 = layout.findViewById(R.id.txt_company);
            TextView text_2 = layout.findViewById(R.id.txt_name);
            TextView text_3 = layout.findViewById(R.id.txt_job);
            TextView text_4 = layout.findViewById(R.id.txt_tel);
            TextView text_5 = layout.findViewById(R.id.txt_email);
            TextView text_6 = layout.findViewById(R.id.txt_address);
            ImageView img_1=layout.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
        } else if (position == 1){
            LinearLayout layout_2 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample2, container, false);

            TextView text_1 = layout_2.findViewById(R.id.txt_company);
            TextView text_2 = layout_2.findViewById(R.id.txt_name);
            TextView text_3 = layout_2.findViewById(R.id.txt_job);
            TextView text_4 = layout_2.findViewById(R.id.txt_tel);
            TextView text_5 = layout_2.findViewById(R.id.txt_email);
            TextView text_6 = layout_2.findViewById(R.id.txt_address);
            ImageView img_1=layout_2.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_2;
        } else if (position == 2){
            LinearLayout layout_3 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample3, container, false);

            TextView text_1 = layout_3.findViewById(R.id.txt_company);
            TextView text_2 = layout_3.findViewById(R.id.txt_name);
            TextView text_3 = layout_3.findViewById(R.id.txt_job);
            TextView text_4 = layout_3.findViewById(R.id.txt_tel);
            TextView text_5 = layout_3.findViewById(R.id.txt_email);
            TextView text_6 = layout_3.findViewById(R.id.txt_address);
            ImageView img_1 = layout_3.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_3;
        } else if(position == 3){
            LinearLayout layout_4 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample4, container, false);

            TextView text_1 = layout_4.findViewById(R.id.txt_company);
            TextView text_2 = layout_4.findViewById(R.id.txt_name);
            TextView text_3 = layout_4.findViewById(R.id.txt_job);
            TextView text_4 = layout_4.findViewById(R.id.txt_tel);
            TextView text_5 = layout_4.findViewById(R.id.txt_email);
            TextView text_6 = layout_4.findViewById(R.id.txt_address);
            ImageView img_1 = layout_4.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_4;
        } else if(position == 4){
            LinearLayout layout_5 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample5, container, false);

            TextView text_1 = layout_5.findViewById(R.id.txt_company);
            TextView text_2 = layout_5.findViewById(R.id.txt_name);
            TextView text_3 = layout_5.findViewById(R.id.txt_job);
            TextView text_4 = layout_5.findViewById(R.id.txt_tel);
            TextView text_5 = layout_5.findViewById(R.id.txt_email);
            TextView text_6 = layout_5.findViewById(R.id.txt_address);
            ImageView img_1 = layout_5.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_5;
        }else if(position == 5){
            LinearLayout layout_6 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample6, container, false);

            TextView text_1 = layout_6.findViewById(R.id.txt_company);
            TextView text_2 = layout_6.findViewById(R.id.txt_name);
            TextView text_3 = layout_6.findViewById(R.id.txt_job);
            TextView text_4 = layout_6.findViewById(R.id.txt_tel);
            TextView text_5 = layout_6.findViewById(R.id.txt_email);
            TextView text_6 = layout_6.findViewById(R.id.txt_address);
            ImageView img_1 = layout_6.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_6;
        }else if(position == 6){
            LinearLayout layout_7 = (LinearLayout) inflater.inflate(R.layout.activity_thema_sample7, container, false);

            TextView text_1 = layout_7.findViewById(R.id.txt_company);
            TextView text_2 = layout_7.findViewById(R.id.txt_name);
            TextView text_3 = layout_7.findViewById(R.id.txt_job);
            TextView text_4 = layout_7.findViewById(R.id.txt_tel);
            TextView text_5 = layout_7.findViewById(R.id.txt_email);
            TextView text_6 = layout_7.findViewById(R.id.txt_address);
            ImageView img_1 = layout_7.findViewById(R.id.image);

            text_1.setText(company);
            text_2.setText(name);
            text_3.setText(job);
            text_4.setText(tel);
            text_5.setText(email);
            text_6.setText(address);
            img_1.setImageBitmap(image);
            return layout_7;
        }
        return layout;
    }
}