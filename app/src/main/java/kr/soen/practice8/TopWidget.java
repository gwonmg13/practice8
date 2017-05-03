package kr.soen.practice8;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * Created by manggi on 2017. 5. 2..
 */

public class TopWidget extends LinearLayout implements View.OnClickListener {

    CheckBox c;
    Button b1,b2;
    int FLAG = 0;

    public TopWidget(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }

    interface OnCheckBoxChangedListener{
        void Checked(Boolean ischecked);
    }
    public OnCheckBoxChangedListener onCheckBoxChangedListener;

    public void setOnCheckBoxChangedListener(OnCheckBoxChangedListener onCheckBoxChangedListener){
        this.onCheckBoxChangedListener = onCheckBoxChangedListener;
    }

    interface  OnButtonListener{
        void OnSetButton();
        void OnAddItemButton();
        void OnItemMsg();
    }
    public OnButtonListener onButtonListener;

    public void setOnButtonListener(OnButtonListener onButtonListener){
        this.onButtonListener = onButtonListener;
    }


    public void init(Context context){

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        c = (CheckBox)findViewById(R.id.checkBox);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckBoxChangedListener.Checked(isChecked);
            }
        });

    }


    @Override
    public void onClick(View v) {

        FLAG++;


        if(v==b2){
            if(FLAG %2==1) onButtonListener.OnSetButton();
            else onButtonListener.OnAddItemButton();
        }
        if(v==b1) onButtonListener.OnItemMsg();


    }
}
