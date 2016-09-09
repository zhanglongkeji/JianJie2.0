package com.zlkj.jianjie.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * App缓存
 * @author  	zenghui.he
 * @email 	hezh@dxyer.com 
 * @date 	2014-2-10
 */
public class AppCache {

	private SharedPreferences cache;

	private static final String KEY_FIRST_START_APP 		= "first_start_app_";//是否第一次启动App
	private static final String KEY_LOCATION_CITY			= "location_city"; //定位的城�?
	private static final String KEY_LOCATION_longitude	    = "location_longitude"; //经度
	private static final String KEY_LOCATION_latitude	    = "location_latitude"; //纬度
	private static final String KEY_LOCATION_CITY_CODE		= "location_city_code"; //定位的城�?
	private static final String KEY_SHOP_ID					= "shop_id";//定位的店铺ID
	private static final String KEY_SHOP_NAME				= "shop_name";//定位的店铺名�?

	private static final String LOCATION_CITYID			= "location_cityId"; //定位的城�?
	private static final String LOCATION_CITYS			= "location_cityss"; //定位的城�?
	private static final String HOST_URL="host_urls";

	private static final String ISVALUSES="ismnoee";  //是否获取到数�?
	private static final String ISVALUSES2="ismnoee22";  //是否获取到数�?
	private static final String ISVALUSES3="ismnoee33";  //是否获取到数�?

	private static final String YAOYIYKAIG="close";  //摇一摇开�?

	private static final String USERJIFEN="0";  //用户积分

	private static final String LOCATION_areaDistrict="areaDistrict";

	private static final String ONCLICKERR="onclikk";  //


	private static final String USERNAMW="user_name_asd";
	private static final String USERPASSWOED="user_password_asd";

	private static final String SHUBKEY="subkey";


	private static final String EVICETOKEN="";//设备号

	private static final String KEY_USER_INFO = ""; // 用户信息


	public void setEvicetoken(String evicetoken){
		Editor editor = cache.edit();
		editor.putString(EVICETOKEN, evicetoken);
		editor.commit();
	}

	public String getEvicetoken(){
		return cache.getString(EVICETOKEN,"");
	}



	public void setSubkey(String subkey){
		Editor editor = cache.edit();
		editor.putString(SHUBKEY, subkey);
		editor.commit();
	}

	public String getSubkey(){
		return cache.getString(SHUBKEY,"");
	}

	


	public void setUserName(String name){
		Editor editor = cache.edit();
		editor.putString(USERNAMW, name);
		editor.commit();
	}

	public String getUserName(){
		return cache.getString(USERNAMW,"");
	}


	public void setUserPassword(String password){
		Editor editor = cache.edit();
		editor.putString(USERPASSWOED, password);
		editor.commit();
	}

	public String getUserPassword(){
		return cache.getString(USERPASSWOED,"");
	}


	public void setUserJifen(String jifen){
		Editor editor = cache.edit();
		editor.putString(USERJIFEN, jifen);
		editor.commit();
	}

	public String getUserJifen(){
		return cache.getString(USERJIFEN,"");
	}


	public void setIsNoYao(String city){
		Editor editor = cache.edit();
		editor.putString(YAOYIYKAIG, city);
		editor.commit();
	}

	public String getIsNoYao(){
		return cache.getString(YAOYIYKAIG,"");
	}





	public void setIsOnclick(String city){
		Editor editor = cache.edit();
		editor.putString(ONCLICKERR, city);
		editor.commit();
	}

	public String getIsOnclick(){
		return cache.getString(ONCLICKERR,"");
	}




	public void setLocatCity(String city){
		Editor editor = cache.edit();
		editor.putString(KEY_LOCATION_CITY, city);
		editor.commit();
	}

	public String getLocatCity(){
		return cache.getString(KEY_LOCATION_CITY,"");
	}

	public void setHostUrls(String url){
		Editor editor = cache.edit();
		editor.putString(HOST_URL, url);
		editor.commit();
	}

	public String getHostUrls(){
		return cache.getString(HOST_URL,"");
	}


	public void setIsValues(String url){
		Editor editor = cache.edit();
		editor.putString(ISVALUSES, url);
		editor.commit();
	}

	public String getIsValues(){
		return cache.getString(ISVALUSES,"");
	}


	public void setIsValues2(String url){
		Editor editor = cache.edit();
		editor.putString(ISVALUSES2, url);
		editor.commit();
	}

	public String getIsValues2(){
		return cache.getString(ISVALUSES2,"");
	}


	public void setIsValues3(String url){
		Editor editor = cache.edit();
		editor.putString(ISVALUSES3, url);
		editor.commit();
	}

	public String getIsValues3(){
		return cache.getString(ISVALUSES3,"");
	}



	public void setLocatLongitude(String city){
		Editor editor = cache.edit();
		editor.putString(KEY_LOCATION_longitude, city);
		editor.commit();
	}

	public String getLocatLatitude(){
		return cache.getString(KEY_LOCATION_longitude,"");
	}

	public void setLocatLatitude(String city){
		Editor editor = cache.edit();
		editor.putString(KEY_LOCATION_latitude, city);
		editor.commit();
	}

	public String getLocatLongitude(){
		return cache.getString(KEY_LOCATION_latitude,"");
	}

	public void setLocatCitys(String city){
		Editor editor = cache.edit();
		editor.putString(LOCATION_CITYS, city);
		editor.commit();
	}

	public String getLocatCitys(){
		return cache.getString(LOCATION_CITYS,"");
	}


	public void setLocatCityId(String city){
		Editor editor = cache.edit();
		editor.putString(LOCATION_CITYID, city);
		editor.commit();
	}

	public String getLocatCityId(){
		return cache.getString(LOCATION_CITYID,"");
	}

	public AppCache(Context context){
		cache = context.getSharedPreferences("app_cache", Context.MODE_PRIVATE);
	}

	public void setFirstStartApp(boolean isFirst){
		Editor editor = cache.edit();
		editor.putBoolean(KEY_FIRST_START_APP+"111", isFirst);
		editor.commit();
	}

	public boolean isFirstStartApp(){
		return cache.getBoolean(KEY_FIRST_START_APP+"111", true);
	}

	public void setLocationCity(String city){
		Editor editor = cache.edit();
		editor.putString(KEY_LOCATION_CITY, city);
		editor.commit();
	}

	public String getLocationCity(){
		return cache.getString(KEY_LOCATION_CITY,"杭州");
	}

	public void setLocationCityCode(String cityCode){
		Editor editor = cache.edit();
		editor.putString(KEY_LOCATION_CITY_CODE, cityCode);
		editor.commit();
	}

	public String getLocationCityCode(){
		return cache.getString(KEY_LOCATION_CITY_CODE, "330100");
	}

	public void setShopId(String shopId){
		Editor editor = cache.edit();
		editor.putString(KEY_SHOP_ID, shopId);
		editor.commit();
	}

	public String getShopId(){
		return cache.getString(KEY_SHOP_ID, "");
	}

	public void setShopName(String shopName){
		Editor editor = cache.edit();
		editor.putString(KEY_SHOP_NAME, shopName);
		editor.commit();
	}

	public String getShopName(){
		return cache.getString(KEY_SHOP_NAME, "");
	}


	public void setAreaDistrict(String areaDistrict){
		Editor editor = cache.edit();
		editor.putString(LOCATION_areaDistrict, areaDistrict);
		editor.commit();
	}

	public String getAreaDistrict(){
		return cache.getString(LOCATION_areaDistrict, "");
	}


}
