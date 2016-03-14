package star.com.provincecityarea.activity;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import star.com.provincecityarea.entity.Area;

public class DBhelper {
	private SQLiteDatabase db;
	private Context context;
	private DBManager dbm;
	
	public DBhelper(Context context) {
		super();
		this.context = context;
		dbm = new DBManager(context);
	}

	/**
	 * 根据省份的pcode，得到城市city
	 * @param pcode
	 * @return
     */
	public ArrayList<Area> getCity(String pcode) {
		dbm.openDatabase();
		db = dbm.getDatabase();
		ArrayList<Area> list = new ArrayList<Area>();
		
	 	try {    
	        String sql = "select * from city where pcode='"+pcode+"'";  
	        Cursor cursor = db.rawQuery(sql,null);  
	        cursor.moveToFirst();
	        while (!cursor.isLast()){ 
	        	String code=cursor.getString(cursor.getColumnIndex("code")); 
	        	byte bytes[]=cursor.getBlob(2); 
		        String name=new String(bytes,"gbk");
		        Area area=new Area();
		        area.setName(name);
		        area.setCode(code);
		        area.setPcode(pcode);
		        list.add(area);
		        cursor.moveToNext();
	        }
	        String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        Area area=new Area();
	        area.setName(name);
	        area.setCode(code);
	        area.setPcode(pcode);
	        list.add(area);
	        
	    } catch (Exception e) {  
	    	return null;
	    } 
	 	dbm.closeDatabase();
	 	db.close();	

		return list;

	}

	/**
	 * 省份
	 * @return
     */
	public ArrayList<Area> getProvince() {
		dbm.openDatabase();
	 	db = dbm.getDatabase();
	 	ArrayList<Area> list = new ArrayList<Area>();
		
	 	try {    
	        String sql = "select * from province";  
	        Cursor cursor = db.rawQuery(sql,null);  
	        cursor.moveToFirst();
	        while (!cursor.isLast()){ 
	        	String code=cursor.getString(cursor.getColumnIndex("code")); 
		        byte bytes[]=cursor.getBlob(2); 
		        String name=new String(bytes,"gbk");
		        Area area=new Area();
		        area.setName(name);
		        area.setCode(code);
		        list.add(area);
		        cursor.moveToNext();
	        }
	        String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        Area area=new Area();
	        area.setName(name);
	        area.setCode(code);
	        list.add(area);
	        
	    } catch (Exception e) {  
	    	return null;
	    } 
	 	dbm.closeDatabase();
	 	db.close();	
		return list;
		
	}

	/**
	 * 根据城市的pcode,得到对应的区域
	 * @param pcode
	 * @return
     */
	public ArrayList<Area> getDistrict(String pcode) {
		dbm.openDatabase();
	 	db = dbm.getDatabase();
	 	ArrayList<Area> list = new ArrayList<Area>();
	 	try {    
	        String sql = "select * from district where pcode='"+pcode+"'";  
	        Cursor cursor = db.rawQuery(sql,null);
	        if (cursor.moveToFirst()) {
				while (!cursor.isLast()) {
					String code = cursor.getString(cursor
							.getColumnIndex("code"));
					byte bytes[] = cursor.getBlob(2);
					String name = new String(bytes, "gbk");
					Area Area = new Area();
					Area.setName(name);
					Area.setPcode(code);
					list.add(Area);
					cursor.moveToNext();
				}
				String code = cursor.getString(cursor.getColumnIndex("code"));
				byte bytes[] = cursor.getBlob(2);
				String name = new String(bytes, "gbk");
				Area Area = new Area();
				Area.setName(name);
				Area.setPcode(code);
				list.add(Area);
			}
	        
	    } catch (Exception e) { 
	    	Log.i("wer", e.toString());
	    } 
	 	dbm.closeDatabase();
	 	db.close();	
		return list;
		
	}
}
