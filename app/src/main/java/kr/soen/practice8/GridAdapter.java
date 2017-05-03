package kr.soen.practice8;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by manggi on 2017. 5. 2..
 */

public class GridAdapter extends BaseAdapter{

    Boolean isDataChanged = false;
    Boolean isSelected = false;
    ArrayList<Fruit> data;
    ArrayList<String> cart = new ArrayList<String>();
    Context context;

    public GridAdapter(ArrayList<Fruit> data, Context context){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,null);
            final TextView textView1 = (TextView)convertView.findViewById(R.id.imageName);
            final TextView textView2 = (TextView)convertView.findViewById(R.id.imagePrice);
            final ImageView imageView = (ImageView)convertView.findViewById(R.id.gridImage);
            final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);

        Fruit fruit = data.get(position);
        if(isDataChanged)textView2.setVisibility(View.VISIBLE);
        else textView2.setVisibility(View.GONE);
        if(isSelected)checkBox.setVisibility(View.VISIBLE);
        else checkBox.setVisibility(View.GONE);
        if(checkBox.isChecked())cart.add(fruit.getName());
        textView1.setText(fruit.getName());
        textView2.setText(fruit.getPrice());
        imageView.setImageResource(Fruit.imgList[fruit.getImgno()]);
        return convertView;
    }
    public void setSelectVisible(Boolean isVisible){
        cart.clear();
        isDataChanged = isVisible;
        isSelected = isVisible;
        notifyDataSetChanged();
    }

    public void addFruit(Fruit one){
        data.add(one);
        notifyDataSetChanged();
    }

    public void ModifiedFruit(Fruit one, int position){
        Fruit fruitdata = data.get(position);
        fruitdata.setName(one.getName());
        fruitdata.setImgno(one.getImgno());
        fruitdata.setPrice(one.getPrice());
        notifyDataSetChanged();
    }

    public void toastMsg(){
        String msg = "";
        for(int i =0 ; i<cart.size();i++)
            msg += "," + cart.get(i);
        Toast.makeText(context,"카트에 " + msg.substring(1)+ "가 담겨있습니다",Toast.LENGTH_SHORT).show();
    }
}
