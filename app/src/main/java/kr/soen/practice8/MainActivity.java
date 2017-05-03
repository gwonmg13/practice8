package kr.soen.practice8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fruit> fruit = new ArrayList<Fruit>();
    ArrayList<String> searchlist = new ArrayList<String>();
    ArrayAdapter<String> s_adapter;
    GridAdapter adapter;
    GridView gridview;
    int dataPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = (GridView) findViewById(R.id.gridView);

        fruit.add(new Fruit("abocado", 0, "1000"));
        fruit.add(new Fruit("banana", 1, "2000"));
        fruit.add(new Fruit("cherry", 2, "3000"));
        fruit.add(new Fruit("crenberry", 3, "4000"));
        fruit.add(new Fruit("grape", 4, "5000"));
        fruit.add(new Fruit("kiwi", 5, "6000"));
        fruit.add(new Fruit("orange", 6, "7000"));
        fruit.add(new Fruit("watermelon", 7, "8000"));

        for (int i = 0; i < fruit.size(); i++) searchlist.add(fruit.get(i).getName());

        s_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, searchlist);
        adapter = new GridAdapter(fruit, this);
        gridview.setAdapter(adapter);
        final BottomWidget bottomWidget = (BottomWidget) findViewById(R.id.bottomwidget);
        final TopWidget topWidget = (TopWidget) findViewById(R.id.topwidget);
        bottomWidget.autoCompleteTextView.setAdapter(s_adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit item = (Fruit) adapter.getItem(position);
                String name = item.getName();
                int img = item.getImgno();
                bottomWidget.setButtomWidget(name, img);
                dataPosition = position;
            }
        });

        bottomWidget.setOnListener(new BottomWidget.OnItemListener() {
            @Override
            public void OnAdd(String name, int imgno, String price) {
                adapter.addFruit(new Fruit(name, imgno, price));

            }

            @Override
            public void OnModified(String name, int imgno, String price) {
                adapter.ModifiedFruit(new Fruit(name, imgno, price), dataPosition);
            }
        });
        topWidget.setOnCheckBoxChangedListener(new TopWidget.OnCheckBoxChangedListener() {
            @Override
            public void Checked(Boolean ischecked) {
                adapter.setVisible(ischecked);
            }
        });

        topWidget.setOnListener(new TopWidget.OnButtonListener() {
            @Override
            public void OnSetButton() {
                adapter.setSelectVisible(true);
            }

            @Override
            public void OnAddItemButton() {
                adapter.notifyDataSetChanged();
                adapter.setSelectVisible(false);
            }

            @Override
            public void OnItemMsg() {
                adapter.toastMsg();
            }
        });


    }
}
