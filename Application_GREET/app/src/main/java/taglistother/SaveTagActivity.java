package taglistother;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.four.application_greet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.NametagOutputUtil;

public class SaveTagActivity extends AppCompatActivity {

    EditText et_tag_key, et_tag_name;
    Button btn_save, btn_main;
    ArrayList<Map<String, String>> arrayList = new ArrayList<>();
    /*map : key, image, name*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_tag);

        et_tag_key = findViewById(R.id.tag_key);
        et_tag_name = findViewById(R.id.tag_name);
        btn_save = findViewById(R.id.btn_save);
        btn_main = findViewById(R.id.btn_main);

        arrayList = getMapArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);

        //저장하기
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //유효성 검사 후 ArrayList에 추가
                out : if(et_tag_key.getText().toString().equals("")){//받아온 값이 없는 경우
                    Toast.makeText(getApplicationContext()
                    ,"키값을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else if(et_tag_name.getText().toString().trim().length() < 2) {
                    Toast.makeText(getApplicationContext(),"제목을 두 글자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < arrayList.size(); i++){//받아온 값이 이미 리스트에 있을 경우
                        if (arrayList.get(i).get("key").equals(et_tag_key.getText().toString())){
                            Toast.makeText(getApplicationContext(),"같은 명함이 존재합니다", Toast.LENGTH_SHORT).show();
                            break out;
                        }
                    }
                    String param = "key=" + et_tag_key.getText().toString();
                    Map<String, String> resultMap = NametagOutputUtil.getDBoutputUtil().output_database(param);
                    String res = resultMap.get("result");
                    String res_key = resultMap.get("key");
                    String res_image = resultMap.get("image");

                    if(res.equals("success")){
                        Map<String, String> map = new HashMap<>();
                        map.put("key", res_key);
                        map.put("image", res_image);
                        map.put("name", et_tag_name.getText().toString());
                        arrayList.add(map);
                        setMapArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON, arrayList);
                        Toast.makeText(getApplicationContext(),"저장에 성공했습니다", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"저장에 실패했습니다. 관리자에게 문의하세요", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(SaveTagActivity.this, ShowListActivity.class);
                    startActivity(intent);
                }
            }
        });

        //액티비티 종료
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveTagActivity.this, ShowListActivity.class);
                startActivity(intent);
            }
        });
    }

    // 키 값
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";



    // ArrayList -> Json으로 변환
    private void setMapArrayPref(Context context, String key, ArrayList<Map<String, String>> values) {
        Log.i("json-list", "SetstringArrayPref");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            JSONObject object = new JSONObject(values.get(i));
            Log.i("list-json", object.toString());
            jsonArray.put(object);
        }
        Log.i("list-json", jsonArray.toString());
        if (!values.isEmpty()) {
            editor.putString(key, jsonArray.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();
    }
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