package com.hackthon.samurai.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	public static final String YYYYMMDD_SLASH = "yyyy/MM/dd";
	public static final String YYYYMMDD_HYPEN = "yyyy-MM-dd";
	public static final String YYYYMMDD_BLANK = "yyyyMMdd";
	public static final String YYYYMMDD_UNDERSCORE = "yyyy_MM_dd";
	public static final String YYYYMMMDD_SLASH = "yyyy/MMM/dd";
	public static final String YYYYMMMDD_HYPEN = "yyyy-MMM-dd";
	public static final String YYYYMMMDD_BLANK = "yyyyMMMdd";
	public static final String YYYYMMMDD_UNDERSCORE = "yyyy_MM_dd";

	public static final String DDMMYYYY_SLASH = "dd/MM/yyyy";
	public static final String DDMMYYYY_HYPEN = "dd-MM-yyyy";
	public static final String DDMMYYYY_BLANK = "ddMMyyyy";
	public static final String DDMMYYYY_UNDERSCORE = "dd_MM_yyyy";
	public static final String DDMMMYYYY_SLASH = "dd/MMM/yyyy";
	public static final String DDMMMMYYYY_HYPEN = "dd-MMM-yyyy";
	public static final String DDMMMYYYY_BLANK = "ddMMMyyyy";
	public static final String DDMMMYYYY_UNDERSCORE = "dd_MMM_yyyy";

	public static final String MMDDYYYY_SLASH = "MM/dd/yyyy";
	public static final String MMDDYYYY_HYPEN = "MM-dd-yyyy";
	public static final String MMDDYYYY_BLANK = "MMddyyyy";
	public static final String MMDDYYYY_UNDERSCORE = "MM_dd_yyyy";
	public static final String MMMDDYYYY_SLASH = "MMM/dd/yyyy";
	public static final String MMMDDYYYY_HYPEN = "MMM-dd-yyyy";
	public static final String MMMDDYYYY_BLANK = "MMMddyyyy";
	public static final String MMMDDYYYY_UNDERSCORE = "MMM_dd_yyyy";
	public static final String HHMMSS = "HHmmss";
	public static final String HHMMSS_COLON = "HH:mm:ss";

	/**
	 * All minutes have this many milliseconds except the last minute of the day
	 * on a day defined with a leap second.
	 */
	public static final long MILLISECS_PER_MINUTE = 60 * 1000;

	/**
	 * Number of milliseconds per hour, except when a leap second is inserted.
	 */
	public static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;

	/**
	 * Number of leap seconds per day expect on <BR/>
	 * 1. days when a leap second has been inserted, e.g. 1999 JAN 1. <BR/>
	 * 2. Daylight-savings "spring forward" or "fall back" days.
	 */
	protected static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;

	/**
	 * Method to get the time stamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp getTimeStamp(Date date) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dt.format(date);
		Timestamp t = null;
		t = Timestamp.valueOf(strDate.replace("/", "-"));
		return t;
	}

	/**
	 * 
	 * This Method is to get the current date and time in specified format ( eg
	 * :"yyyy-MM-dd_HHmmss")
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		DateFormat df = new SimpleDateFormat(format);
		String curDateTime = df.format(new java.util.Date());
		return curDateTime;
	}

	/**
	 * 
	 * This Method is to check the string date and date are equal
	 * 
	 * @param strDate
	 * @param date
	 * @return
	 */
	public static boolean checkStrDateAndDate(String strDate, java.util.Date date) {

		boolean result = false;

		if (strDate.equals(getStringDateFromDate(date, YYYYMMDD_SLASH))) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * This Method is to get the Date as YMD String
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDateFromDate(java.util.Date date, String dateFormat) {
		SimpleDateFormat dt = new SimpleDateFormat(dateFormat);
		String strDate = dt.format(date);
		return strDate;
	}

	/**
	 * 
	 * This Method is to getTodayWithoutSlash
	 * 
	 * @return
	 */
	public static String getTodayWithoutSlash() {
		java.util.Date today = Calendar.getInstance().getTime();
		String dat = "" + today.getDate();
		dat = dat.length() == 1 ? "0" + dat : dat;
		String mon = "" + (today.getMonth() + 1);
		mon = mon.length() == 1 ? "0" + mon : mon;
		String year = "" + (1900 + today.getYear());
		year = year.length() == 1 ? "0" + year : year;
		String to = dat + mon + year;
		String to1 = year + "" + mon + "" + dat;
		return to1;
	}

	/**
	 * Method to get the time stamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp getTimeStamp() {

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = dt.format(new java.util.Date());
		Timestamp t = null;
		t = Timestamp.valueOf(strDate.replace("/", "-"));
		return t;
	}

	/**
	 * 
	 * This Method is to getPreviousFridayDateFromDate
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getPreviousFridayDateFromDate(java.util.Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH) - (c.get(Calendar.DAY_OF_WEEK) + 1));
		return c.getTime();
	}

	/**
	 * 
	 * This Method is to check for Friday and Future
	 * 
	 * @param str_date
	 * @return
	 */
	public static boolean isFridayAndNotFuture(String str_date) throws Exception {
		boolean result = false;

		java.util.Date date = null;
		if (str_date != null && !str_date.trim().equals("")) {
			date = getDateFromString(str_date, YYYYMMDD_SLASH);
			if (isFuture(date)) {
				new Exception("Date Should not be today or future day");
				result = false;
			} else {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
					result = true;
				} else {
					new Exception("Entered Date is not Friday");
				}
			}

		} else {
			new Exception("Date Should not be blank");
		}
		return result;
	}

	/**
	 * 
	 * This Method is to
	 * 
	 * @param str_date
	 * @return
	 */
	public static java.util.Date getPreviousFridayDateFromString(String str_date, String dateFormat) throws Exception {
		java.util.Date date;
		if (str_date != null && !str_date.trim().equals("")) {
			date = getDateFromString(str_date, dateFormat);
		} else {
			date = getDateFromString(getStringDateFromDate(new java.util.Date(), dateFormat), dateFormat);
		}
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH) - (c.get(Calendar.DAY_OF_WEEK) + 1));
		return c.getTime();
	}

	/**
	 * 
	 * This Method is to
	 * 
	 * @param str_date
	 * @return
	 */
	public static java.util.Date[] getPreviousFiveFridayDate(String dateFormat) throws Exception {
		java.util.Date[] dates = new java.util.Date[5];

		dates[0] = getPreviousFridayDateFromString(null, dateFormat);
		for (int i = 1; i < 5; i++) {
			dates[i] = getDateFromString(getStringDateFromDate(getPreviousFridayDateFromDate(dates[i - 1]), dateFormat),
					dateFormat);
			;
		}
		return dates;
	}

	/**
	 * 
	 * This Method is to get the Previous Fifth Friday Date
	 * 
	 * @param str_date
	 * @return
	 */
	public static java.util.Date getPreviousFifthFridayDate(String dateFormat) throws Exception {
		java.util.Date[] dates;
		dates = getPreviousFiveFridayDate(dateFormat);
		return dates[4];
	}

	/**
	 * 
	 * This Method is to get next Friday Date
	 * 
	 * @param str_date
	 * @return
	 */
	public static java.util.Date getNextFridayDate(String str_date, String dateFormat) throws Exception {

		java.util.Date date;
		if (str_date != null && !str_date.trim().equals("")) {
			date = getDateFromString(str_date, dateFormat);
		} else {
			date = getDateFromString(getStringDateFromDate(new java.util.Date(), dateFormat), dateFormat);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int toAdd = Calendar.FRIDAY - c.get(Calendar.DAY_OF_WEEK);
		if (toAdd < 0) {
			toAdd = Calendar.FRIDAY;
		} else if (toAdd == 0) {
			toAdd = Calendar.FRIDAY + 1;
		}
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + toAdd);

		if (isFuture(c.getTime())) {
			new Exception("Next Friday should not be today or a future date");
			return getPreviousFridayDateFromDate(c.getTime());
		}
		return c.getTime();
	}

	/**
	 * 
	 * This Method is to check the given date is future or not
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFuture(java.util.Date date) {
		boolean result = false;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Calendar d = Calendar.getInstance();
		if ((c.get(Calendar.MONTH) == d.get(Calendar.MONTH))
				&& (c.get(Calendar.DAY_OF_MONTH) == d.get(Calendar.DAY_OF_MONTH))) {
			result = true;
		} else if (c.getTimeInMillis() > d.getTimeInMillis()) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * This Method is to get date object from date String
	 * 
	 * @param str_date,
	 *            format (eg: "yyyy/MM/dd")
	 * @return
	 */
	public static java.util.Date getDateFromString(String str_date, String dateFormat) throws Exception {

		DateFormat formatter;
		java.util.Date date = null;
		formatter = new SimpleDateFormat(dateFormat);
		try {
			date = (java.util.Date) formatter.parse(str_date);
		} catch (Exception e) {
			new Exception(e.getMessage());
		}
		return date;
	}

	public static long diffDayPeriods(java.util.Date oldDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(oldDate);
		Calendar d = Calendar.getInstance();

		long diff = d.getTimeInMillis() - c.getTimeInMillis();
		// System.out.println("diff : "+ diff);
		// System.out.println("diff/MILLISECS_PER_DAY : "+
		// diff/MILLISECS_PER_DAY);
		// return diff/MILLISECS_PER_DAY;
		return diff / MILLISECS_PER_MINUTE;

	}

	public static long diffInDays(java.util.Date oldDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(oldDate);
		Calendar d = Calendar.getInstance();

		long diff = d.getTimeInMillis() - c.getTimeInMillis();
		// System.out.println("diff : "+ diff);
		// System.out.println("diff/MILLISECS_PER_DAY : "+
		// diff/MILLISECS_PER_DAY);
		return diff / MILLISECS_PER_DAY;
		// return diff/MILLISECS_PER_MINUTE;
	}

	public static long diffInHrs(java.util.Date oldDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(oldDate);
		Calendar d = Calendar.getInstance();

		long diff = d.getTimeInMillis() - c.getTimeInMillis();
		// System.out.println("diff : "+ diff);
		// System.out.println("diff/MILLISECS_PER_DAY : "+
		// diff/MILLISECS_PER_DAY);
		return diff / MILLISECS_PER_HOUR;
		// return diff/MILLISECS_PER_MINUTE;
	}

	public static boolean validateWakeupTime(String wakeupTime) {
		boolean result = false;
		Calendar c = Calendar.getInstance();
		String[] wakeupTime1 = wakeupTime.split(":");
		int hr = Integer.parseInt(wakeupTime1[0]);
		int min = Integer.parseInt(wakeupTime1[1]);
		wakeupTime = hr + ":" + min;
		String currentWakeupTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
		// System.out.println("Checking wakeuptime : " + wakeupTime +" current
		// time : "+currentWakeupTime);
		// System.out.println(currentWakeupTime);
		if (wakeupTime.equalsIgnoreCase(currentWakeupTime)) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * This Method is to getStringDate
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getPtdmStringDate(String date) {

		String rStringDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		if (date != null && date.length() > 0) {
			try {
				java.util.Date rdate = (java.util.Date) formatter.parse(date);
				rStringDate = getStringDateFromDate(rdate, "MM/dd/yyyy" + " " + "HH:mm:ss");
				// System.out.println("String date is: " +
				// dynamicFormat(rdate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return rStringDate;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().getTime().getTime());
		System.out.println(Calendar.getInstance().getTime().getTime());
		System.out.println(validateWakeupTime("20:25"));
		System.out.println(" getStringDateFromDate  ::::: " + getPtdmStringDate("20121213130506"));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, -3);
		System.out.println(diffDayPeriods(c.getTime()));
		try {
			System.out.println(" getNextFridayDate  ::::: " + getNextFridayDate(null, YYYYMMMDD_SLASH));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
