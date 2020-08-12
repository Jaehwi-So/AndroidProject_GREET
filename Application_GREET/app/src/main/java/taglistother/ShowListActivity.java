package taglistother;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import main.MainActivity;
import com.four.application_greet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowListActivity extends AppCompatActivity {

    ListView listView;
    Button btn_main, btn_search;
    ArrayAdapter<String> adapter;
    ViewModelAdapter adapter_cus;
    ArrayList<Map<String, String>> arrayList = new ArrayList<>();
    ImageView imageView;
    Dialog dialog;
    SweetAlertDialog sweetAlertDialog;
    TextView card_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        //Intent intent = getIntent();
        arrayList = getMapArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);
        listView = findViewById(R.id.listView);
        btn_main = findViewById(R.id.btn_main);
        btn_search = findViewById(R.id.btn_search);


        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        if( adapter_cus == null){
            adapter_cus = new ViewModelAdapter(ShowListActivity.this, R.layout.listview_layout, arrayList, listView );
            //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sample_list);
            listView.setAdapter(adapter_cus);
        }
        adapter_cus.notifyDataSetChanged();


        //검색화면으로 넘어가기
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowListActivity.this, SaveTagActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //액티비티 종료
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    // 키 값
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";

    // Json -> ArrayList으로 변환
    private ArrayList getMapArrayPref(Context context, String key) {
        Log.i("json-list", "GetstringArrayPref");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<Map<String, String>> list = new ArrayList();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    JSONObject resultObject = a.getJSONObject(i);
                    Map<String, String> map = new HashMap<>();
                    for (int j = 0; j < resultObject.length(); j++) {
                        map.put(resultObject.names().getString(j), resultObject.getString(resultObject.names().getString(j)));
                    }
                    list.add(map);
                }
                for(int i = 0; i < list.size(); i++){
                    Log.i("json-list", list.get(i).get("key"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}