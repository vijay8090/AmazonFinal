/**
 * Title : CSVFileUtil.java
 */
package com.hackthon.samurai.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 19, 2015
 * @java Version : Java 8
 */
public class CSVFileUtil {
	static Logger logger = LogManager.getLogger(CSVFileUtil.class.getName());
	public static String CHECKIN_FILE_NAME = "checkinfilename";
	public static String CHECKOUT_FILE_NAME = "checkoutfilename";
	private static final int  BUFFER_SIZE = 4096;

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
	 * This Method is to moveFile
	 * 
	 * @param file
	 * @param destinationFolder
	 */
	public static boolean moveFile(File f, String destinationFolder) throws Exception{
		boolean result = false;
		String newFileName = destinationFolder + File.separator + f.getName();
		logger.debug("New file location: " + newFileName);
		File destination = new File(newFileName);
		if(!destination.exists()){
			if (f.renameTo(destination)) {
				result = f.delete();
			}
			else{
				FileUtils.moveFileToDirectory(f, new File(destinationFolder), true);
			}
		}
		return result;
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

	
	/*
	private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException
	{
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir,name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1)
			out.write(buffer, 0, count);
		out.close();
	}*/


	/**
	 * 
	 * This Method is to unZipIt
	 * 
	 * @param zipFile
	 * @param outputFolder
	 */
/*	public static void unZipIt(File zipfile, File outdir) throws IOException{

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
			 this part is necessary because file entry can come before
			 * directory entry where is file located
			 * i.e.:
			 *   /foo/foo.txt
			 *   /foo/
			 
			dir = dirpart(name);
			if( dir != null )
				mkdirs(outdir,dir);

			extractFile(zin, outdir, name);
		}
		zin.close();

		System.out.println("Done");


	}*/
	
	/*
	public static NvhDataLoaderUtil getILFile(File file, String searchFileExt) {
		logger.debug("start checkZipEntry");
		NvhDataLoaderUtil objNvhDataLoaderUtil = null;
		logger.debug("Zip file : " + file);
		logger.debug("Zip file size : " + convertToSize(file.length()));
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
				Enumeration<?> e = zipFile.entries();
				while (e.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					logger.debug("zip file entry :"
							+ entry.getName().toLowerCase());
					if (entry.getName().toLowerCase().endsWith(searchFileExt)) {
						logger.debug("zip file Found " + entry.getName());
						InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(entry.getName()));

						Writer writer = new StringWriter();
						String encoding = null;
						IOUtils.copy(inputStream, writer, encoding);
						String theString = writer.toString();

						Gson gson = new Gson();
						objNvhDataLoaderUtil = gson.fromJson(theString, NvhDataLoaderUtil.class);
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
		return objNvhDataLoaderUtil;
	}*/
}
