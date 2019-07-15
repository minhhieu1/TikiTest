package com.minhhieu.tikitest;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private ArrayList<String> data=new ArrayList<>();

    public RecycleViewAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item, parent, false);

        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.txtKeyWord.setText(stringDisplay(data.get(position)));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView txtKeyWord;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            GradientDrawable gradientDrawable = new GradientDrawable();
            String [] color={"#800000","#FF8C00","#B8860B","#808000","#006400","#2E8B57","#2F4F4F","#191970","#00008B","#8B008B","#800080","#8B4513"};
            Random random=new Random();

            gradientDrawable.setColor(Color.parseColor(color[random.nextInt(color.length-1)]));
            setCornerRadius(gradientDrawable, 40f);

            itemView.setBackground(gradientDrawable);
            txtKeyWord=itemView.findViewById(R.id.txtKeyWord);
        }
    }
    static void setCornerRadius(GradientDrawable drawable, float topLeft,
                                float topRight, float bottomRight, float bottomLeft) {
        drawable.setCornerRadii(new float[] { topLeft, topLeft, topRight, topRight,
                bottomRight, bottomRight, bottomLeft, bottomLeft });
    }

    static void setCornerRadius(GradientDrawable drawable, float radius) {
        drawable.setCornerRadius(radius);

    }
    public String stringDisplay(String input){
        String string="";
        input=input.trim();
        if(input.trim().split(" ").length==1){
            return input;
        }else{
           int midle=input.length()/2;
           if(input.charAt(midle-1)==' '){
                string=input.substring(0,midle-1)+"\n"+input.substring(midle,input.length());
           }else{
               int starIndex=0;
               int endIndex=input.length()-1;
               for(int i=midle; i>0; i--){
                   if(input.charAt(i)==' '){
                       starIndex=i+1;
                       break;
                   }
               }
               for(int i=midle; i<input.length(); i++){
                   if(input.charAt(i)==' '){
                       endIndex=i+1;
                       break;
                   }
               }

               if(input.length()-starIndex-starIndex+1<endIndex-1-(input.length()-endIndex)
                       ||input.length()-starIndex-starIndex+1==endIndex-1-(input.length()-endIndex)){
                   string=input.substring(0, starIndex-1)+"\n"+input.substring(starIndex, input.length());
               } else{
                   string=input.substring(0, endIndex-1)+"\n"+input.substring(endIndex, input.length());
               }
           }
           return string;
        }
    }
}
