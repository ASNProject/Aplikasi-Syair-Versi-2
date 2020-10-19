package com.example.syair20.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static final String PREF_NAME="Pegawai";

   public SharePreference(Context context){
       this.context = context;
       pref = context.getSharedPreferences(PREF_NAME, private_mode);
       editor = pref.edit();
   }

    public void setEmail (String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail (){
        return pref.getString("email", null);
    }
}
