package com.cos.blog.util;

public class Script {
	public static String back(String msg) {
		StringBuilder sb=new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();		
	}
	
	public static String page(String msg) {
		StringBuilder sb=new StringBuilder();
		sb.append("<script>");
		sb.append("location.href='"+msg+"'");
		sb.append("</script>");
		return sb.toString();		
	}
}
