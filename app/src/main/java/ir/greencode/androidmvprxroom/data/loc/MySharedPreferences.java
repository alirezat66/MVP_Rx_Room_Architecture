package ir.greencode.androidmvprxroom.data.loc;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class MySharedPreferences {
    private SharedPreferences mSharedPrefrences;
    @Inject
    public MySharedPreferences(SharedPreferences mSharedPrefrences){
        this.mSharedPrefrences = mSharedPrefrences;
    }
    public void putData(String key,int data){
        mSharedPrefrences.edit().putInt(key,data).apply();
    }
    public void putData(String key,long data){
        mSharedPrefrences.edit().putLong(key,data).apply();
    }
    public void putData(String key,String data){
        mSharedPrefrences.edit().putString(key,data).apply();
    }
    public void putData(String key,boolean data){
        mSharedPrefrences.edit().putBoolean(key,data).apply();
    }
    public int getDataInt(String key) {
        return mSharedPrefrences.getInt(key,0);
    }
    public long getDataLong(String key) {
        return mSharedPrefrences.getLong(key,0);
    }
    public String getDataString(String key) {
        return mSharedPrefrences.getString(key,"");
    }
    public boolean getDataBoolean(String key) {
        return mSharedPrefrences.getBoolean(key,false);
    }
}
