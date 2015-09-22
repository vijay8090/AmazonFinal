/**
 * Title : FileUtil.java
 * Description : This Class is to 
 * Copyright: Ford Motor Company
 */
package com.hackthon.samurai.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @project	:ApdmCheckoutCheckin
 * @author	:Vijayakumar Anbu (avijaya8)
 * @date	:Feb 3, 2012
 * @since 	:JDK 1.5
 */
public class FileUtil {
	static Logger logger = LogManager.getLogger(FileUtil.class.getName());
	public static String CHECKIN_FILE_NAME = "checkinfilename";
	public static String CHECKOUT_FILE_NAME = "checkoutfilename";

	/**
	 * 
	 * This Method is to createFileFromString
	 * @param content
	 * @param fileName
	 * @throws Exception
	 */
	public static void createFileFromString(String content, String fileName)
			{

		FileWriter resultFile = null;
		
		try {
			resultFile = new FileWriter(fileName);
			resultFile.write(content);
			resultFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();			
		}		
	}

	/**
	 * 
	 * This Method is to get File As String
	 * @param fullPath
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static String getFileAsString(String fullPath) {

		File file = new File(fullPath);
		String content = null ;
		FileInputStream fin = null ;
		if(file.exists()){
			try {
			fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			fin.read(fileContent);
			content = new String(fileContent);
			fin.close();
			} catch (IOException e) {
				e.printStackTrace();	
				content = null ;
			} 
		}

		return content;
	}
	

	/**
	 * 
	 * This Method is to convertToSize
	 * 
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
	 * This Method is to roundToDecimals
	 * 
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
	 * This Method is to checkZipEntry
	 * 
	 * @param file
	 * @param searchFileExt
	 * @return
	 */
	/*public static NvhDataLoaderUtil checkZipEntry(File file, String searchFileExt) {

		logger.debug("start checkZipEntry");
		NvhDataLoaderUtil prop = null;
		logger.debug("Zip file : " + file);
		logger
				.debug("Zip file size : " + convertToSize(file.length()));
		logger.debug("Searching for file extension:" + searchFileExt);
		// File we want to search for inside the zip file
		if (searchFileExt == null) {
			logger.debug("Searching for file extension is null ");
		} else if (file.getName().toLowerCase().endsWith(".zip")) {
			try {
				// open the source zip file
				logger.debug("PatternFileExtExists :" + file.getName());
				ZipFile zipFile = new ZipFile(file);

				int numberOfEntries = zipFile.size();
				logger.debug("There are  :" + numberOfEntries
						+ " entries in zip file:" + file);
				Enumeration e = zipFile.entries();
				while (e.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					logger.debug("zip file entry :"
							+ entry.getName().toLowerCase());
					if (entry.getName().toLowerCase().endsWith(searchFileExt)) {
						logger.debug("zip file Found " + entry.getName());
						InputStream inputStream = zipFile
								.getInputStream(zipFile.getEntry(entry
										.getName()));
						
						StringWriter writer = new StringWriter();
						Charset encoding = null;
						IOUtils.copy(inputStream, writer, encoding);
						String theString = writer.toString();
						
						Gson gson = new Gson();
						prop = gson.fromJson(theString, NvhDataLoaderUtil.class);
						break;
					}
				}
				zipFile.close();
				logger.debug("Job file size:" + file.length());

			} catch (IOException ioe) {
				System.out.println("Error opening zip file" + ioe);
			}
		} else {
			logger.debug(file.getName() + " is not a zip file");
		}

		logger.debug("end checkZipEntry");
		return prop;
	}*/

	/**
	 * 
	 * This Method is to moveFile
	 * 
	 * @param f
	 * @param destinationFolder
	 */
	public static boolean moveFile(File f, String destinationFolder) {
		boolean result = false;
		String newFileName = destinationFolder + File.separator + f.getName();
		logger.debug("New file location: " + newFileName);
		File destination = new File(newFileName);
		if (f.renameTo(destination)) {
			result = f.delete();
		}
		return result;
	}
	
	  private static final int  BUFFER_SIZE = 4096;

	  private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException
	  {
	    byte[] buffer = new byte[BUFFER_SIZE];
	    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir,name)));
	    int count = -1;
	    while ((count = in.read(buffer)) != -1)
	      out.write(buffer, 0, count);
	    out.close();
	  }

	  private static void mkdirs(File outdir,String path)
	  {
	    File d = new File(outdir, path);
	    if( !d.exists() )
	      d.mkdirs();
	  }

	  private static String dirpart(String name)
	  {
	    int s = name.lastIndexOf( File.separatorChar );
	    return s == -1 ? null : name.substring( 0, s );
	  }



	/**
	 * 
	 * This Method is to unZipIt
	 * 
	 * @param zipFile
	 * @param outputFolder
	 */
	public static void unZipIt(File zipfile, File outdir) throws IOException{

		ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
	      ZipEntry entry;
	      String name, dir;
	      while ((entry = zin.getNextEntry()) != null)
	      {
	        name = entry.getName();
	        if( entry.isDirectory() )
	        {
	          mkdirs(outdir,name);
	          continue;
	        }
	        /* this part is necessary because file entry can come before
	         * directory entry where is file located
	         * i.e.:
	         *   /foo/foo.txt
	         *   /foo/
	         */
	        dir = dirpart(name);
	        if( dir != null )
	          mkdirs(outdir,dir);

	        extractFile(zin, outdir, name);
	      }
	      zin.close();

			System.out.println("Done");

		
	}

/*	public static boolean zipIt(String zipFile, ArrayList<String> srcFiles, boolean isNvh, String cdsId) {

		// String zipFile = "C:/archive.zip";

		// String[] srcFiles = { "C:/srcfile1.txt", "C:/srcfile2.txt",
		// "C:/srcfile3.txt"};
		boolean result = false;
		try {

			// create byte buffer
			byte[] buffer = new byte[10*1024];

			FileOutputStream fos = new FileOutputStream(zipFile);

			ZipOutputStream zos = new ZipOutputStream(fos);
			
			int attachmentSeq = 1; 

			for (int i = 0; i < srcFiles.size(); i++) {

				File srcFile = new File(srcFiles.get(i));

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the
				// start of the entry data
				if (isNvh) {
					
					if (srcFile.getName().toLowerCase().endsWith(".atfx")) {
						zos.putNextEntry(new ZipEntry("NVHDM.atfx"));
					} else if (srcFile.getName().toLowerCase()
							.endsWith(DataLoaderConstants.HDF_ATFX)) {
						zos.putNextEntry(new ZipEntry("NVHDM" + attachmentSeq++
								+ ".hdf"));
					} else if (srcFile.getName().toLowerCase()
							.endsWith(DataLoaderConstants.NVL)) {
						zos.putNextEntry(new ZipEntry(cdsId+ "_nvhDataLoaderUtil"
								+ ".nvl"));

					} else if (srcFile.getName().toLowerCase()
							.endsWith(DataLoaderConstants.BIN_ATFX)) {
						zos.putNextEntry(new ZipEntry("NVHDM" + attachmentSeq++
								+ ".bin"));
					} else if (srcFile.getName().toLowerCase()
							.endsWith(DataLoaderConstants.HADX_ATFX)
							|| srcFile.getName().toLowerCase()
									.endsWith(DataLoaderConstants.HATX_ATFX)) {
						zos.putNextEntry(new ZipEntry("NVHDM.hadx"));
					} else {
						zos.putNextEntry(new ZipEntry(srcFile.getName()));
					}
				} else {
					if (srcFile.getName().toLowerCase().endsWith(".atfx")) {
						zos.putNextEntry(new ZipEntry("LMSGVLDM.atfx"));
					} else{
					zos.putNextEntry(new ZipEntry(srcFile.getName()));
					}
				}

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();

			}

			// close the ZipOutputStream
			zos.close();

			result = true;
		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}

		return result;
	}*/
	
	public static void deleteFilesRecursively(File src, boolean deleteParent ) {
		if (src.isDirectory()) {
			File list[] = src.listFiles();
				for (int i = 0; i < list.length; i++) {
					deleteFilesRecursively(list[i], true);
				}	
				if(deleteParent){
					src.delete();	
				}
				
		} else {
			src.delete();	
		}
			
					
	}
	

	public static ArrayList<String> readFileAsArrayList(String fullPath) {
		ArrayList<String> result = new ArrayList<String>();
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream(fullPath));
			String tempLine = scan.nextLine();
			while (scan.hasNextLine()) {
				result.add(tempLine);
				tempLine = scan.nextLine();
			}
			result.add(tempLine);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	

public static void main(String[] args) {
	
	
	
	System.out.println(readFileAsArrayList("C:\\Users\\avijaya8.FORDAP1\\Desktop\\nvhdm loader problem\\input files\\NVH-user-Template-sample.hatx"));
	
	try {
		File fileDir = new File("C:\\Users\\avijaya8.FORDAP1\\Desktop\\nvhdm loader problem\\input files\\NVH-user-Template-sample.hatx");
 
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF16"));
 
		String str;
 
		while ((str = in.readLine()) != null) {
		    System.out.println(str);
		}
 
                in.close();
	    } 
	    catch (UnsupportedEncodingException e) 
	    {
			System.out.println(e.getMessage());
	    } 
	    catch (IOException e) 
	    {
			System.out.println(e.getMessage());
	    }
	    catch (Exception e)
	    {
			System.out.println(e.getMessage());
	    }
	}
	

}
