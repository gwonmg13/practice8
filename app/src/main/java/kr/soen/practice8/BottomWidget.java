package kr.soen.practice8;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by manggi on 2017. 5. 2..
 */

public class BottomWidget extends LinearLayout implements View.OnClickListener{

    int BASIC_MODE = 0;
    int MOD_MODE = 1;
    int ADD_MODE = 2;
    Button add_btn, next_btn ;
    public AutoCompleteTextView autoCompleteTextView;
    ImageView imageView;
    int imgNum=0, priceNum=0;


    public BottomWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    void setButtomWidget(String name, int num){
        this.imgNum = num;
        this.priceNum = num;
        autoCompleteTextView.setText(name);
        imageView.setImageResource(Fruit.imgList[imgNum]);
        BASIC_MODE = MOD_MODE;
        add_btn.setText("M");
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.BottomWidget,this);
        add_btn = (Button)findViewById(R.id.add_btn);
        next_btn = (Button)findViewById(R.id.next_btn);
        imageView = (ImageView)findViewById(R.id.image_btn);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.f_name);
        add_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        BASIC_MODE = ADD_MODE;

    }

    @Override
    public void onClick(View v) {

        if(v==next_btn){
            imgNum ++;
            priceNum ++;
            if(imgNum>7){
                imgNum=0;
                priceNum=0;
            }
            imageView.setImageResource(Fruit.imgList[imgNum]);
        }else if(v==add_btn){
            if(BASIC_MODE==MOD_MODE){
                onAdd_ModListener.OnAdd(autoCompleteTextView.getText().toString(),imgNum,
                        Integer.toString(Fruit.priceList[priceNum]));
                BASIC_MODE=ADD_MODE;
                add_btn.setText("ADD");
                autoCompleteTextView.setText("");

            }else if(BASIC_MODE==ADD_MODE){
                onAdd_ModListener.OnModify(autoCompleteTextView.getText().toString(),imgNum,
                        Integer.toString(Fruit.priceList[priceNum]));
                autoCompleteTextView.setText("");
            }
        }
    }
    interface OnAdd_ModListener{
        void OnAdd(String name,int imgno, String price);
        void OnModify(String name, int imgno, String price);
    }
    public OnAdd_ModListener onAdd_ModListener;

    public void setOnAdd_ModListener(OnAdd_ModListener onAdd_ModListener){
        this.onAdd_ModListener = onAdd_ModListener;
    }
}
