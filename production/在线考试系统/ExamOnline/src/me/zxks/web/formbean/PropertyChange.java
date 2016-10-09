package me.zxks.web.formbean;

public class PropertyChange {
	public int TFChangeInt(String TF){
		int i = 0;
		if(TF.equals("T")){
			i=1;}
		else if(TF.equals("F"))
			i= 0;
		return i;
	}
	public int GenderChangeInt(String gender){
		int i=0;
		if(gender.equals("男")){
			i=1;
		}
		else if(gender.equals("女")){
			i=0;
		}
		return i;
	}
}
