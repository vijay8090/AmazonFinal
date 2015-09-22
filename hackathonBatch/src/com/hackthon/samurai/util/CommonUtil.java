/**
 * Title : CommonUtil.java
 * Description : This Class is to 
 * Copyright: Ford Motor Company
 */
package com.hackthon.samurai.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * @project :ApdmCheckoutCheckin
 * @author :Vijayakumar Anbu (avijaya8)
 * @date :Feb 3, 2012
 * @since :JDK 1.5
 */
public class CommonUtil {

	/**
	 * 
	 * This Method is to replaceBackslash
	 * 
	 * @param myStr
	 * @return
	 */
	public static String replaceBackslash(String myStr) {
		
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				myStr);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {

			if (character == '\\') {
				result.append("\\\\");
			} else {
				result.append(character);
			}

			character = iterator.next();
		}
		return result.toString();
	}
	
	/**
	 * 
	 * This Method is to replaceDoubleBackslashWithSingle
	 * @param myStr
	 * @return
	 * @throws Exception
	 */
	public static String replaceDoubleBackslashWithSingle(String myStr) throws Exception {
		return replaceAll(myStr,"\\\\", "\\");
	}
	
	/**
	 * 
	 * This Method is to replaceAll
	 * @param source
	 * @param toReplace
	 * @param replacement
	 * @return
	 * @throws Exception
	 */
	public static String replaceAll(String source, String toReplace,
			String replacement) throws Exception {
		int idx = source.lastIndexOf(toReplace);
		if (idx != -1) {
			StringBuffer ret = new StringBuffer(source);
			ret.replace(idx, idx + toReplace.length(), replacement);
			while ((idx = source.lastIndexOf(toReplace, idx - 1)) != -1) {
				ret.replace(idx, idx + toReplace.length(), replacement);
			}
			source = ret.toString();
		}

		return source;
	}
	
    /**
     * 
     * This Method is to backlashReplace
     * 
     * @param myStr
     * @return
     */
    public static String backlashReplace(final String myStr) {
       
        return replaceBacklashWithForward(myStr);
    }
    
    /**
     * 
     * This Method is to replaceSpecialCharaters
     * @param myStr
     * @return
     */
    public static String replaceSpecialCharaters(String myStr) {
    	System.out.println("CommonUtil.replaceSpecialCharaters - original string : "+ myStr);
    	if(myStr!=null && !myStr.trim().equalsIgnoreCase("")){
    	myStr = replaceStr(myStr,"%20", " ");
    	myStr = replaceStr(myStr,":", ";");
    	myStr = replaceStr(myStr,"/", "#");
    	myStr = replaceStr(myStr,"\\", "%");
    	myStr = replaceStr(myStr,"*", "+");
    	myStr = replaceStr(myStr,"|", "!");
    	myStr = replaceStr(myStr,"?", "#");
    	myStr = replaceStr(myStr,"\"", "'");
    	myStr = replaceStr(myStr,"<", "(");
    	myStr = replaceStr(myStr,">", ")");
    	}
    	System.out.println("CommonUtil.replaceSpecialCharaters - modified string : "+ myStr);
        return myStr;
    }
	
	
	/**
	 * 
	 * This Method is to replaceBacklashWithForward
	 * @param myStr
	 * @return
	 */
	 public  static String replaceBacklashWithForward(String myStr){
		    final StringBuffer result = new StringBuffer();
		    final StringCharacterIterator iterator = new StringCharacterIterator(myStr);
		    char character =  iterator.current();
		    while (character != CharacterIterator.DONE ){
		     
		      if (character == '\\') {
		         result.append("/");
		      }
		       else {
		        result.append(character);
		      }
		      character = iterator.next();
		    }
		return result.toString();
	}
	
	/**
	 * 
	 * This Method is to fileSizeinGB
	 * @param size
	 * @return
	 */
	public static  double fileSizeinGB(final double size) {
		return roundToDecimals(size / (1024 * 1024 * 1024), 2);
	}
	
	/**
	 * 
	 * This Method is to roundToDecimals
	 * @param d
	 * @param c
	 * @return
	 */
	public static double roundToDecimals(final double d, final int c) {
		final int temp = (int) ((d * Math.pow(10, c)));
		return (((double) temp) / Math.pow(10, c));
	}
	

	
	/**
	 * 
	 * This Method is to convertToSize
	 * @param size
	 * @return
	 */
	public static String convertToSize(double size) {
		String unit = "KB";
		if (size > 0) {
			size = size / 1024;
			if (size == 0)
				size = 1;
		}
		if (size > 1024) {
			size = size / 1024;
			unit = "MB";
		}
		if (size > 1024) {
			size = size / 1024;
			unit = "GB";
		}

		return roundToDecimals(size, 2) + " " + unit;
	}
	
	/**
	 * 
	 * This Method is to convertToDate
	 * @param modified
	 * @return
	 */
	public static String convertToDate(final long modified) {
		String dt = "";
		dt = new SimpleDateFormat("dd MMM yyyy HH:mm a").format(modified);
		return dt;
	}
	
	/**
	 * 
	 * This Method is to replaceStr
	 * @param str
	 * @param pattern
	 * @param replace
	 * @return
	 */
	public static String replaceStr(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}
	
	/**
	 * 
	 * This Method is to setNewFont
	 * @param obj
	 */
	/*public static void setNewFont(final Object obj) {

        ((JComponent)obj).setFont(new Font(DataLoaderConstants.FONT_NAME, 0,
                DataLoaderConstants.FONT_SIZE));
        
        //((JComponent)obj).setBorder(BorderFactory.createLineBorder(Color.red));
    }
	
	public static void setBoldFont(final Object obj) {

        ((JComponent)obj).setFont(new Font(DataLoaderConstants.FONT_NAME, 1,13));
        
        //((JComponent)obj).setBorder(BorderFactory.createLineBorder(Color.red));
    }*/
	
	public static void setAsTxt(final Object obj) {

		((JComponent)obj).setBorder(BorderFactory.createLineBorder(Color.darkGray));
		((JComponent)obj).setBackground(Color.WHITE);
		((JComponent)obj).setOpaque(true);
    }
	
	/**
	 * 
	 * This Method is to reverseConvertSize
	 * @param strSize
	 * @return
	 */
    public static long reverseConvertSize(String strSize) {
    	
    	strSize = replaceStr(strSize,"%20", " ");
    	
        long size = 0;
        if(strSize.toUpperCase().endsWith("GB".toUpperCase())){
        	strSize = strSize.replaceAll("GB","").trim();
        	size = Long.parseLong(strSize);
        	size = size * (1024*1024*1024);
        } else if(strSize.toUpperCase().endsWith("MB".toUpperCase())){
        	strSize = strSize.replaceAll("MB","").trim();
        	size = Long.parseLong(strSize);
        	size = size * (1024*1024);
        } else if(strSize.toUpperCase().endsWith("KB".toUpperCase())){
        	strSize = strSize.replaceAll("KB","").trim();
        	size = Long.parseLong(strSize);
        	size = size * (1024);
        } else if(strSize.toUpperCase().endsWith("Byte".toUpperCase())){
        	strSize = strSize.replaceAll("Byte","").trim();
        	size = Long.parseLong(strSize);        	
        }

        return size;
    }
    
	/**
	 * 
	 * This Method is to convert stringArray To ArrayList
	 * @param strArray
	 * @param list
	 * @return
	 */
	public static ArrayList<String> stringArrayToArrayList(String[] strArray  ) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		if (strArray != null) {
			
			for (int i = 0; i < strArray.length; i++) {
				list.add(strArray[i]);
			}
			Collections.sort(list);
		}
		return list;
	}
	
	/**
	 * 
	 * This Method is to getArrayListAsString
	 * @param list
	 * @return
	 */
	public static String getArrayListAsString( ArrayList<String> list  ) {
		String newStr = null;
		
		if(list!=null && !list.isEmpty()){
			newStr = "";
			int i= 1;
			for(String content : list){
				newStr += "("+ i++ + ") " +content + "\n";
			}
			
		}
		
		return newStr;
	}
	
	/**
	 * 
	 * This Method is to getClassAndMethodName
	 * @param obj
	 * @return
	 */
	public static String debug( Object obj  ) {
		if(obj != null && obj.getClass()!= null){
			try {
				return new Date().toString()+" : "+ obj.getClass().getEnclosingClass().getName() + " : " + obj.getClass().getEnclosingMethod().getName()+"() : ";
			} catch(Exception e){
				return new Date().toString()+" : ";
			}
			} else {
			return new Date().toString()+" : ";
		 }
	}
	
/*	
	public static boolean checkMinJavaVersion() {
		boolean result = false ;
		
		 String JAVAversion = System.getProperty("java.version");
		 JAVAversion = JAVAversion.substring(0,3);
		 double jv = 1.0;
		 try {
		 jv = Double.parseDouble(JAVAversion);
		 System.out.println("The version of JAVA is: "+jv);
		 } catch (Exception e){
			 e.printStackTrace();
			 result = true ;
		 }
        if(jv < DataLoaderConstants.JAVA_MIN_VER){
        	JOptionPane.showMessageDialog(null,
			"You have an older version of Java2 Runtime Environment installed.\n You need the latest version to run the APDM upload/download java applet.\n Please put a ticket at www.request.ford.com to have the latest version installed.","APDM ",JOptionPane.INFORMATION_MESSAGE);
        }  else {
        	result = true ;
        }
        
        return result ;
	}*/
	

//	public static String generateZipFile(String fileList, String zipFileName) throws Exception {
//
//		FileInputStream zipFile = null;
//
//		ZipOutputStream zipFilesource = null;
//
//		try {
//
//			File f = new File(fileList);
//			String sourceFiles[] = f.list();
//
//			zipFilesource = new ZipOutputStream(new FileOutputStream(zipFileName));
//
//			for (int i = 0; i < sourceFiles.length; i++) {
//				if (sourceFiles[i].startsWith("NVHTDM_")
//						|| sourceFiles[i].endsWith(Constants.HADX_ATFX)) {
//
//					zipFile = new FileInputStream(f.getPath() + File.separator
//							+ sourceFiles[i]);
//
//					zipFilesource.putNextEntry(new ZipEntry(new File(
//							sourceFiles[i]).getName()));
//
//					byte[] filebyte = new byte[1024];
//					int count;
//					while ((count = zipFile.read(filebyte)) > 0) {
//						zipFilesource.write(filebyte, 0, count);
//					}
//					zipFilesource.closeEntry();
//					zipFile.close();
//				}
//			}
//			zipFilesource.close();
//
//			for (int i = 0; i < sourceFiles.length; i++) {
//				if (sourceFiles[i].startsWith("NVHTDM_")) {
//					deleteFile(f.getPath() + File.separator + sourceFiles[i]);
//				}
//			}
//			// discordFiles(zipFilepath);
//			return "Added sucessfully";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Failed to add file";
//		}
//	}
	

	public static String generateZipFile(ArrayList<String> fileList, String zipFileName) throws Exception {

		FileInputStream zipFile = null;

		ZipOutputStream zipFilesource = null;

		try {

			zipFilesource = new ZipOutputStream(new FileOutputStream(zipFileName));

			for (String filePath : fileList) {
				File f  = new File(filePath);			

					zipFile = new FileInputStream(filePath);

					zipFilesource.putNextEntry(new ZipEntry(f.getName()));

					byte[] filebyte = new byte[1024];
					int count;
					while ((count = zipFile.read(filebyte)) > 0) {
						zipFilesource.write(filebyte, 0, count);
					}
					zipFilesource.closeEntry();
					zipFile.close();
				}
			
			zipFilesource.close();

//			
			// discordFiles(zipFilepath);
			return "generated Zip File sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to generate Zip File";
		}
	}
	
	
	/**
	 * 
	 * This Method is to deleteFile
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File deleteFile = new File(fileName);

		if (deleteFile.delete()) {
			System.out.println(deleteFile.getName() + "File is deleted");
		} else {
			System.out.println(deleteFile.getName() + "File is not deleted");
		}

		return false;

	}
	

//	/**
//	 * 
//	 * This Method is to getBaseFolder
//	 * 
//	 * @return
//	 */
//	public static String getBaseFolder() {
//		FTPClient clientFTP = null;
//		String currentDate = DateUtil
//				.getCurrentDateTime(DateUtil.YYYYMMDD_BLANK);
//		try {
//			clientFTP = new FTPClient();
//			clientFTP.connect(host);
//			boolean login = clientFTP.login(user, pwd);
//			boolean exists = false;
//			if (login) {
//				FTPFile[] listDircetory = clientFTP.listDirectories(parentDir);
//				System.out.println("Sub folders of " + parentDir + " : ");
//				for (int i = 0; i < listDircetory.length; i++) {
//					System.out.println(i + ")" + listDircetory[i].getName());
//					if (listDircetory[i].getName()
//							.equalsIgnoreCase(currentDate)) {
//						exists = true;
//						System.out.println(CommonUtil.debug(this) + currentDate
//								+ " Folder already exists");
//						break;
//					}
//				}
//				if (!exists) {
//					System.out.println(CommonUtil.debug(this) + currentDate
//							+ " Folder does not exists");
//				}
//			}
//			clientFTP.disconnect();
//			clientFTP = null;
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "/" + currentDate + "/";
//	}
	
	


    public static List<NameValuePair> getNameValuePairs(
			ArrayList<String[]> detailsList) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		for (String[] details : detailsList) {
			nameValuePairs.add(new BasicNameValuePair(details[0], details[1]));
		}

		return nameValuePairs;

	}
    
/*    public static void sendReminderMail(NvhDataLoaderUtil objNvhDataLoaderUtil,
			String subject, String message, boolean forceSend) throws Exception {
    	
    	String[] cdsid = null;
		if(objNvhDataLoaderUtil.getCdsid() != null && !objNvhDataLoaderUtil.getCdsid().trim().equals("")) {
			cdsid = new String[]{objNvhDataLoaderUtil.getCdsid()};
		}
    	
    	String[] ccAddress = null;
		if(objNvhDataLoaderUtil.getCcList() != null && !objNvhDataLoaderUtil.getCdsid().trim().equals("")){
			ccAddress = objNvhDataLoaderUtil.getCcList().split(",");
		}

		if(cdsid != null){
		NVHSendMail objSendMail = new NVHSendMail();
		try {
			if (forceSend) {
				objSendMail.sendMail(cdsid, subject,	message, "nvhdm@ford.com",ccAddress);
			} else {
				if (objNvhDataLoaderUtil.isEmailReq()) {					
					objSendMail.sendMail(cdsid, subject, message, "nvhdm@ford.com",ccAddress);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			throw new Exception("CDSID is null, Can't send Email");
		}
	}*/
    
	

	
	/**
	 * 
	 * This Method is to main
	 * @param args
	 */
    public static void main(String[] args) {
    	
//    	String s = "dfd%pudfdsf";
//    	System.out.println( s );
//    	s = s.replaceAll("%", "!@#") ;
//    	System.out.println( s );
//    	
//    	s = s.replaceAll( "!@#","%") ;
//    	System.out.println( s );
    	
    	LineNumberReader lnr;
		try {
			lnr = new LineNumberReader(new FileReader("t1.xml"));
			lnr.skip(Long.MAX_VALUE);
			System.out.println(lnr.getLineNumber());
			lnr.close();
			  LineNumberReader reader  = new LineNumberReader(new FileReader("t1.xml"));
			  int cnt = 0;
			  String lineRead = "";
			  while ((lineRead = reader.readLine()) != null) {}

			  cnt = reader.getLineNumber(); 
			  reader.close();
			  System.out.println(cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
//    	System.out.println(reverseConvertSize("1 MB"));
//    	System.out.println(replaceSpecialCharaters("C:<>"));
//    	String newDirToUpload ="\\js/new";
//        if(newDirToUpload!=null && !newDirToUpload.trim().equalsIgnoreCase("")){
//            newDirToUpload = newDirToUpload.trim();
//            if(newDirToUpload.startsWith("/")){
//            	newDirToUpload = newDirToUpload.substring(1);
//            }
//            if(newDirToUpload.startsWith("\\")){
//            	newDirToUpload = newDirToUpload.substring(1);
//            }
//            }
//    	String[] arr= newDirToUpload.split("/");
//    	System.out.println("0: "+arr[0]);
//    	System.out.println("1: "+arr[1]);
	}
}
