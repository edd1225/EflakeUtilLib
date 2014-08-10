package com.eflake.utils.library.utils;import android.content.Context;import android.content.pm.PackageManager.NameNotFoundException;/** * <p>Title: ReflectionUtil</p> * <p>Description:通过反射，动态获取资源文件 </p> * @author	Eflake * @date	2014-8-10 */public class ReflectionUtil {	public static Context getSkinContext(Context context, String skinPkgName) {		try {			return context.createPackageContext(skinPkgName, Context.CONTEXT_IGNORE_SECURITY);		} catch (NameNotFoundException e) {			e.printStackTrace();		}		return null;	}		public static int getIdentifier(Context context,String name,String defType) {//		String skinPkgName = "com.jit.skin_1";		String skinPkgName = context.getPackageName();    	return getSkinContext(context, skinPkgName).getResources().getIdentifier(name, defType, skinPkgName);    }		public static int anim(Context context,String name){		return getIdentifier(context,name,"anim");	}		public static int attr(Context context,String name){		return getIdentifier(context,name,"attr");	}		public static int dimen(Context context,String name){		return getIdentifier(context,name,"dimen");	}		public static int drawable(Context context,String name){		return getIdentifier(context,name,"drawable");	}		public static int id(Context context,String name){		return getIdentifier(context,name,"id");	}		public static int layout(Context context,String name){		return getIdentifier(context,name,"layout");	}		public static int menu(Context context,String name){		return getIdentifier(context,name,"menu");	}		public static int string(Context context,String name){		return getIdentifier(context,name,"string");	}		public static int style(Context context,String name){		return getIdentifier(context,name,"style");	}		public static int xml(Context context,String name){		return getIdentifier(context,name,"xml");	}	}