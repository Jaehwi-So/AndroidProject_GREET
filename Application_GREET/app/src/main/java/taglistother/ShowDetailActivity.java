package taglistother;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.four.application_greet.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowDetailActivity extends AppCompatActivity {

    ImageView img_detail;
    TextView img_name, img_key;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        img_detail = findViewById(R.id.img_detail);
        img_key = findViewById(R.id.img_key);
        img_name = findViewById(R.id.img_name);
        btn_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        img_key.setText(bundle.getString("key"));
        img_name.setText(bundle.getString("name"));

        loadBitmap(bundle.getString("image"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /* 이미지 경로를 통해 이미지뷰에 이미지 집어넣기 */
    public void loadBitmap(String strUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn =  (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            img_detail.setImageBitmap(bitmap);
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }
}