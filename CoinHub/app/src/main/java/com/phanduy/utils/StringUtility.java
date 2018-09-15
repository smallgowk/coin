/*
 * Name: $RCSfile: StringUtility.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 1:54:00 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.phanduy.utils;

import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * StringUtility class
 * 
 * @author Lemon
 * 
 */
public final class StringUtility {
	
	public static boolean isEmpty(EditText editText) {
		if (editText == null
				|| editText.getEditableText() == null
				|| editText.getEditableText().toString().trim()
						.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	/**
	 * Check input string
	 * 
	 * @param editText
	 * @return
	 */
	public static boolean isEmpty(String editText) {
		if (editText == null || editText.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	/**
	 * Merge all elements of a string array into a string
	 * 
	 * @param strings
	 * @param separator
	 * @return
	 */
	public static String join(String[] strings, String separator) {
		StringBuffer sb = new StringBuffer();
		int max = strings.length;
		for (int i = 0; i < max; i++) {
			if (i != 0)
				sb.append(separator);
			sb.append(strings[i]);
		}
		return sb.toString();
	}

	/**
	 * Convert current date tempCreatedDate to string
	 * 
	 * @param updateTime
	 * @return
	 */
	public static String convertNowToFullDateString() {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		return dateformat.format(calendar.getTime());
	}

	/**
	 * Initial sync date string
	 * 
	 * @return
	 */
	public static String initDateString() {
		return "1900-01-01 09:00:00";
	}

	/**
	 * Convert a string divided by ";" to multiple xmpp users
	 * 
	 * @param userString
	 * @return
	 */
	public static String[] convertStringToXmppUsers(String userString) {
		return userString.split(";");
	}


	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

//	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append((line + "\n"));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
//	    finally {
//	        try {
//	            is.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
	    
	    return sb.toString();
	}
	public static String encode(String value) throws UnsupportedEncodingException{
		return URLEncoder.encode(value, "UTF-8");
	}
	
	public static String enCryptMD5(String s){
		 try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(s.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
	}
	
	public static String[] getImageUrlArray(String content){
		long start = System.currentTimeMillis();
		ArrayList<String> arr = new ArrayList<String>();
		int count = 0;
		int p = content.indexOf("http://", count);
		
		while (p >= 0) {
			
			String temp = "";
			int i = p;
			char curChar = content.charAt(i);
			while (curChar != '\"') {
				temp += curChar;
				i++;
				curChar = content.charAt(i);
				
			}
			count = i;
			p = content.indexOf("http://", count);
			arr.add(temp);
			
		}
		long end = System.currentTimeMillis();
		Log.e("Time Parse", "" + (end - start));
		int l = arr.size();
		String[] s = new String[l];
		for(int i = 0; i < l; i++){
			s[i] = arr.get(i);
		}
		
		return s;
	}
	
//	public static ArrayList<String> extractLinks(String url, byte type)
//			throws IOException {
//		final ArrayList<String> result = new ArrayList<String>();
//		Document doc = Jsoup.connect(url).get();
//		switch (type) {
//		case IMAGE_URL:
//			Elements media = doc.select("[src]");
//			// img ...
//			for (org.jsoup.nodes.Element src : media) {
//				String s = src.attr("abs:src");
//				String s1 = s.substring(s.lastIndexOf('.') + 1, s.length());
//				if (exts.contains(s1)) {
//					result.add(s);
//				}
//			}
//			return result;
//		case HREF_URL:
//			Elements links = doc.select("a[href]");
//			// href ...
//			for (org.jsoup.nodes.Element link : links) {
//				result.add(link.attr("abs:href"));
//			}
//			return result;
//		case JS_CSS_URL:
//			Elements imports = doc.select("link[href]");
//			// js, css, ...
//			for (org.jsoup.nodes.Element link : imports) {
//				result.add(link.attr("abs:href"));
//			}
//			return result;
//		case ALL_URL:
//			Elements media1 = doc.select("[src]");
//			Elements links1 = doc.select("a[href]");
//			Elements imports1 = doc.select("link[href]");
//			// img ...
//			for (org.jsoup.nodes.Element src : media1) {
//				result.add(src.attr("abs:src"));
//			}
//			// href ...
//			for (org.jsoup.nodes.Element link : links1) {
//				result.add(link.attr("abs:href"));
//			}
//			// js, css, ...
//			for (org.jsoup.nodes.Element link : imports1) {
//				result.add(link.attr("abs:href"));
//			}
//			return result;
//		}
//
//		return null;
//	}
	
	public static String convertIntToString(int value) {
		DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
		return df.format(value);
	}

	public static String convertLongToString(long value) {
		DecimalFormat df = new DecimalFormat("###,###,###,###,###");
		return df.format(value);
	}


	public static String convertDoubleToString(double value) {

		String unit = "";

		if(value > 1000000000) {
			value = value / 1000000000;
			unit = "B";
		} else if(value > 1000000) {
			value = value / 1000000;
			unit = "M";
		}

		if(value == 0) return "0";

		int i = 1;

		while (Math.abs(value * Math.pow(10, i)) < 1) {
			i++;
		}
//		if(value * 100 >= 1) {
//			DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
//			return df.format(value);
//		}
		DecimalFormat df = new DecimalFormat(getFormat(i));
		return df.format(value) + unit;
	}
	public static String convertDoubleToStringMUnit(double value) {

		String unit = "";

		if(value > 1000000) {
			value = value / 1000000;
			unit = "M";
		}

		if(value == 0) return "0";

		int i = 1;

		while (Math.abs(value * Math.pow(10, i)) < 1) {
			i++;
		}
//		if(value * 100 >= 1) {
//			DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
//			return df.format(value);
//		}
		DecimalFormat df = new DecimalFormat(getFormat(i));
		return df.format(value) + unit;
	}

	static public String getBehindDot(int number) {
		StringBuilder sb = new StringBuilder("");
		if (number <= 2) return "##";

		for(int i = 0; i < number; i++) {
			sb.append("#");
		}

		return sb.toString();
	}

	public static String getFormat(int number) {
		return "###,###,###,###,###." + getBehindDot(number);
	}
	
	public static String createTimeStampKey(){
		long curTime = System.currentTimeMillis();
		return "" + curTime;
	}
	
	public static boolean checkImageUrlInHtml(String html){
		
		int p = 0;
		int countP = 0;
		p = html.indexOf("<img src=", countP);
		
		
		if (p >= 0) {
			countP = p + 1;
			p = html.indexOf("http:", countP);
			
			if(p >= 0){
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}
	
	public static String renderAdsUrl(int width, int height, int categoryId){
		
//		Log.e("StringUtility", "" + width + "   " + height);
		
//		String content = "<a href='http://zone.go.vn/smo/www/delivery/ck.php?n=a87515c4&amp;cb=INSERT_RANDOM_NUMBER_HERE' target='_blank'><img width="+width+" height="+height+" src='http://zone.go.vn/smo/www/delivery/avw.php?zoneid=971&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=a87515c4' border='0' alt='' /></a>";
		
//		String script = "<script type='text/javascript'><!--//<![CDATA["
//				+ "var m3_u = (location.protocol=='https:'?'https://go/zone/www/delivery/ajs.php':'http://zone.go.vn/smo/www/delivery/ajs.php');"
//				+ " var m3_r = Math.floor(Math.random()*99999999999);"
//				+ " if (!document.MAX_used) document.MAX_used = ',';"
//				+ " document.write (\"<scr\"+\"ipt type='text/javascript' src='\"+m3_u);"
//				+ " document.write (\"?zoneid=1005\");"
//				+ " document.write ('&amp;cb=' + m3_r);"
//				+ " if (document.MAX_used != ',') document.write (\"&amp;exclude=\" + document.MAX_used);"
//				+ " document.write (document.charset ? '&amp;charset='+document.charset : (document.characterSet ? '&amp;charset='+document.characterSet : ''));"
//				+ " document.write (\"&amp;loc=\" + escape(window.location));"
//				+ " if (document.referrer) document.write (\"&amp;referer=\" + escape(document.referrer));"
//				+ " if (document.context) document.write (\"&context=\" + escape(document.context));"
//				+ " if (document.mmm_fo) document.write (\"&amp;mmm_fo=1\");"
//				+ " document.write (\"'><\\/scr\"+\"ipt>\");"
//				+ "//]]>--></script><noscript>";
//		
		String content = "<a href='http://m.go.vn/Touch.aspx' target='_blank'><img width="+width+" height="+height+" src='http://zone.go.vn/smo/www/delivery/avw.php?zoneid="+categoryId+"&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=a87515c4' border='0' alt='' /></a>";
//		String content = "<a href='http://m.go.vn/Touch.aspx' target='_blank'><img src='http://zone.go.vn/smo/www/delivery/avw.php?zoneid="+categoryId+"&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=a87515c4' border='0' alt='' /></a>";
//		
//		String text =content;
//		
		String url = "<html><body style='margin:0; padding:5;text-align:justify;'>"
		+ content + "</body></html>";
		
//		String s = "" +
//				"<html>"+
//"<head>"+
//	"<style>"+
//		"img{max-width:100%;height:auto !important;width:auto !important;};"+
//	"</style>"+
//"</head>"+
//
//"<body style='margin:0; padding:0;text-align:justify;color:#FFFFFF;background-color: transparent;overflow-y:hidden;overflow-x:hidden;text-align:center;' leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">"+
//
//"<script type='text/javascript'><!--//<![CDATA["+
// "var m3_u = (location.protocol=='https:'?'https://go/zone/www/delivery/ajs.php':'http://zone.go.vn/smo/www/delivery/ajs.php');"+
// "var m3_r = Math.floor(Math.random()*99999999999);"+
// "if (!document.MAX_used) document.MAX_used = ',';"+
// "document.write (\"<scr\"+\"ipt type='text/javascript' src='\"+m3_u);"+
// "document.write (\"?zoneid=971\");"+
// "document.write ('&amp;cb=' + m3_r);"+
// "if (document.MAX_used != ',') document.write (\"&amp;exclude=\" + document.MAX_used);"+
// "document.write (document.charset ? '&amp;charset='+document.charset : (document.characterSet ? '&amp;charset='+document.characterSet : ''));"+
// "document.write (\"&amp;loc=\" + escape(window.location));"+
// "if (document.referrer) document.write (\"&amp;referer=\" + escape(document.referrer));"+
// "if (document.context) document.write (\"&context=\" + escape(document.context));"+
// "if (document.mmm_fo) document.write (\"&amp;mmm_fo=1\");"+
// "document.write (\"'><\\/scr\"+\"ipt>\");"+
////]]>-->
// "</script><noscript><a href='http://m.go.vn/Touch.aspx' target='_blank'><img src='http://zone.go.vn/smo/www/delivery/avw.php?zoneid=971&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=a87515c4' border='0' alt='' width='150' height='150'/></a></noscript>"+
//
//"</body></html>";
		
		return url;
	}
	
	public static String readFileText(File f){
		String everything = null;;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return everything;
	}
	public static int compare(String s1, String s2){
		return s1.compareTo(s2);
	}
	
public static String convertVietnameseToAscii(String input) {
		
		input = input.replace("A","A");
		input = input.replace("a","a");
		input = input.replace("Ă","A");
		input = input.replace("ă","a");
		input = input.replace("Â","A");
		input = input.replace("â","a");	
		input = input.replace("E","E");
		input = input.replace("e","e");
		input = input.replace("Ê","E");
		input = input.replace("ê","e");
		input = input.replace("I","I");
		input = input.replace("i","i");
		input = input.replace("O","O");
		input = input.replace("o","o");
		input = input.replace("Ô","O");
		input = input.replace("ô","o");
		input = input.replace("Ơ","O");
		input = input.replace("ơ","o");
		input = input.replace("U","U");
		input = input.replace("u","u");
		input = input.replace("Ư","U");
		input = input.replace("ư","u");
		input = input.replace("Y","Y");
		input = input.replace("y","y");
		
		input = input.replace("À","A");
		input = input.replace("à","a");
		input = input.replace("Ằ","A");
		input = input.replace("ằ","a");
		input = input.replace("Ầ","A");
		input = input.replace("ầ","a");	
		input = input.replace("È","E");
		input = input.replace("è","e");
		input = input.replace("Ề","E");
		input = input.replace("�?","e");
		input = input.replace("Ì","I");
		input = input.replace("ì","i");
		input = input.replace("Ò","O");
		input = input.replace("ò","o");
		input = input.replace("Ồ","O");
		input = input.replace("ồ","o");
		input = input.replace("Ờ","O");
		input = input.replace("�?","o");
		input = input.replace("Ù","U");
		input = input.replace("ù","u");
		input = input.replace("Ừ","U");
		input = input.replace("ừ","u");
		input = input.replace("Ỳ","Y");
		input = input.replace("ỳ","y");

		input = input.replace("�?","A");
		input = input.replace("á","a");
		input = input.replace("Ắ","A");
		input = input.replace("ắ","a");
		input = input.replace("Ấ","A");
		input = input.replace("ấ","a");	
		input = input.replace("É","E");
		input = input.replace("é","e");
		input = input.replace("Ế","E");
		input = input.replace("ế","e");
		input = input.replace("�?","I");
		input = input.replace("í","i");
		input = input.replace("Ó","O");
		input = input.replace("ó","o");
		input = input.replace("�?","O");
		input = input.replace("ố","o");
		input = input.replace("Ớ","O");
		input = input.replace("ớ","o");
		input = input.replace("Ú","U");
		input = input.replace("ú","u");
		input = input.replace("Ứ","U");
		input = input.replace("ứ","u");
		input = input.replace("�?","Y");
		input = input.replace("ý","y");

		input = input.replace("Ả","A");
		input = input.replace("ả","a");
		input = input.replace("Ẳ","A");
		input = input.replace("ẳ","a");
		input = input.replace("Ẩ","A");
		input = input.replace("ẩ","a");	
		input = input.replace("Ẻ","E");
		input = input.replace("ẻ","e");
		input = input.replace("Ể","E");
		input = input.replace("ể","e");
		input = input.replace("Ỉ","I");
		input = input.replace("ỉ","i");
		input = input.replace("Ỏ","O");
		input = input.replace("�?","o");
		input = input.replace("Ổ","O");
		input = input.replace("ổ","o");
		input = input.replace("Ở","O");
		input = input.replace("ở","o");
		input = input.replace("Ủ","U");
		input = input.replace("ủ","u");
		input = input.replace("Ử","U");
		input = input.replace("ử","u");
		input = input.replace("Ỷ","Y");
		input = input.replace("ỷ","y");	
		
		input = input.replace("Ã","A");
		input = input.replace("ã","a");
		input = input.replace("Ẵ","A");
		input = input.replace("ẵ","a");
		input = input.replace("Ẫ","A");
		input = input.replace("ẫ","a");	
		input = input.replace("Ẽ","E");
		input = input.replace("ẽ","e");
		input = input.replace("Ễ","E");
		input = input.replace("ễ","e");
		input = input.replace("Ĩ","I");
		input = input.replace("ĩ","i");
		input = input.replace("Õ","O");
		input = input.replace("õ","o");
		input = input.replace("Ỗ","O");
		input = input.replace("ỗ","o");
		input = input.replace("Ỡ","O");
		input = input.replace("ỡ","o");
		input = input.replace("Ũ","U");
		input = input.replace("ũ","u");
		input = input.replace("Ữ","U");
		input = input.replace("ữ","u");
		input = input.replace("Ỹ","Y");
		input = input.replace("ỹ","y");	
		
		input = input.replace("Ạ","A");
		input = input.replace("ạ","a");
		input = input.replace("Ặ","A");
		input = input.replace("ặ","a");
		input = input.replace("Ậ","A");
		input = input.replace("ậ","a");	
		input = input.replace("Ẹ","E");
		input = input.replace("ẹ","e");
		input = input.replace("Ệ","E");
		input = input.replace("ệ","e");
		input = input.replace("Ị","I");
		input = input.replace("ị","i");
		input = input.replace("Ọ","O");
		input = input.replace("�?","o");
		input = input.replace("Ộ","O");
		input = input.replace("ộ","o");
		input = input.replace("Ợ","O");
		input = input.replace("ợ","o");
		input = input.replace("Ụ","U");
		input = input.replace("ụ","u");
		input = input.replace("Ự","U");
		input = input.replace("ự","u");
		input = input.replace("Ỵ","Y");
		input = input.replace("ỵ","y");
		
		input = input.replace("�?","D");
		input = input.replace("đ","d");
		
		return input; 
		
	}
	
	
}
