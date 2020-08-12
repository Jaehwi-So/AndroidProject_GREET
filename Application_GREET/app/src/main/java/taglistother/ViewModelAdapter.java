package taglistother;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.four.application_greet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ViewModelAdapter extends ArrayAdapter<Map<String, String>> implements AdapterView.OnItemClickListener {

    Context context;
    int resource;
    ArrayList<Map<String, String>> list;
    Dialog dialog;

    public ViewModelAdapter(Context context, int resource, ArrayList<Map<String, String>> list, ListView listView) {
        super(context, resource, list);


        this.context = context;
        this.resource = resource;
        this.list = list;

        //리스트뷰에 이벤트 감지자 등록
        listView.setOnItemClickListener( this );//감지자를 ViewModelAdapter
    }//생성자

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater linf = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        convertView = linf.inflate(resource, null);

        TextView card_name = convertView.findViewById(R.id.card_name);
        TextView card_key = convertView.findViewById(R.id.card_key);
        Button btn_detail = convertView.findViewById(R.id.btn_detail);
        Button btn_del = convertView.findViewById(R.id.btn_del);

        card_name.setText(list.get(position).get("name"));
        card_key.setText(list.get(position).get("key"));


        //상세보기 버튼
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", list.get(position).get("name"));
                bundle.putString("key", list.get(position).get("key"));
                bundle.putString("image", list.get(position).get("image"));

                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        //삭제버튼
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                setMapArrayPref(context, SETTINGS_PLAYER_JSON, list);
                notifyDataSetChanged();
                Toast.makeText(context,"삭제되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }//getView()

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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


}
