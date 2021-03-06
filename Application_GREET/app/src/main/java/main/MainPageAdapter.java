package main;
import java.io.File;
import java.io.FilenameFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.four.application_greet.R;

public class MainPageAdapter extends PagerAdapter {

    /** 이 어댑터와 관계되는 컨텍스트. 이 프로젝트에서는 MainActivity 객체가 이에 해당됩니다. */
    private Context context = null;

    /** 화면에 보여줄 이미지들이 위치한 경로입니다. */
    private String imagePath;
    private String[] imageFileNames;


    public MainPageAdapter(Context context, String imagePath) {
        this.context = context;
        this.imagePath = imagePath;

// imagePath 경로로부터 jpg, png 파일들을 검색합니다.
        File imagePathAsFile = new File(imagePath);
        imageFileNames = imagePathAsFile.list(new ImageFileFilter());

    }


    @Override
    public int getCount() {
        if(imageFileNames == null ){
            return 0;
        }
        return imageFileNames.length;
    }

    @Override
    public boolean isViewFromObject(View pageView, Object pageKey) {
        return (pageView == pageKey);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 뷰 페이저에 추가할 페이지
        View page = inflater.inflate(R.layout.my_namecard, null);

        // 페이지의 이미지를 설정한 후 페이지를 뷰 페이저에 추가
        ImageView iv = (ImageView)page.findViewById(R.id.ivPicture);
        Bitmap myBitmap = BitmapFactory.decodeFile(imagePath + "/" + imageFileNames[position]);
        iv.setImageBitmap(myBitmap);

        container.addView(page, 0);

        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object pageKey) {
        container.removeView((View)pageKey);
    }


}
class ImageFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String filename) {
        if (filename.endsWith(".jpg"))
            return true;
        else if (filename.endsWith(".JPG"))
            return true;
        else if (filename.endsWith(".png"))
            return true;
        else if (filename.endsWith(".PNG"))
            return true;
        else
            return false;
    }

}