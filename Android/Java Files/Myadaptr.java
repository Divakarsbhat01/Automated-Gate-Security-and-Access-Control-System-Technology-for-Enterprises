package com.example.rfida;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Myadaptr extends BaseAdapter {
    List<Model> mlist;
    Context mcontext;

    public Myadaptr(Context context, List<Model> modelFList) {
        this.mcontext=context;
        this.mlist=modelFList;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1=layoutInflater.inflate(R.layout.modeltable,viewGroup,false);
        TextView textuid=view1.findViewById(R.id.Duid);
        TextView textdate=view1.findViewById(R.id.Ddate);
        TextView texttime= view1.findViewById(R.id.Dtime);
//        TextView textbranch= view1.findViewById(R.id.Dbranch);
//        TextView textsection=view1.findViewById(R.id.Dsection);
//        TextView textblock=view1.findViewById(R.id.Dblock);
//        TextView textrfid=view1.findViewById(R.id.Drfid);
//        TextView phnum= view1.findViewById(R.id.Dphnum);

        textuid.setText(mlist.get(i).getUid());
        textdate.setText(mlist.get(i).getDate());
        texttime.setText(mlist.get(i).getTime());
//        textbranch.setText(mlist.get(i).getBranch());
//        textsection.setText(mlist.get(i).getSection());
//        textblock.setText(mlist.get(i).getBloxk());
//        textrfid.setText(mlist.get(i).getRfid());
//        phnum.setText(mlist.get(i).getPhnum());
        return view1;
    }
}

