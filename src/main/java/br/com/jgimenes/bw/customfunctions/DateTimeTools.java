package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeTools implements Serializable {

	/**
	 * 
	 * TIBCO Business Works 5.X - Date And time Custom Functions.
	 *
	 * @author jgimenes
	 * @version 1.0
	 */

	private static final long serialVersionUID = 4775471864696291772L;

	/**
	 * Convert from Epoch to Human Readable Date.
	 * 
	 * @param timestamp
	 * @return human readable date
	 * 
	 */

	public static String epochToHumanReadable(String timestamp) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX");
		formatter.setTimeZone(TimeZone.getDefault());
		long epoch = Long.parseLong(timestamp);
		Date date = new Date(epoch);
		return formatter.format(date);

	}

	/**
	 * Convert from Human Readable Date to Epoch.
	 * 
	 * @param timestamp
	 * @return epoch
	 * 
	 */

	public static String humanReadableToEpoch(String datetime) {

		LocalDateTime localDateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME);
		ZonedDateTime LocalDateTimeZone = localDateTime.atZone(ZoneId.systemDefault());
		long epoch = LocalDateTimeZone.toEpochSecond() * 1000;
		return String.valueOf(epoch);

	}

	/**
	 * Retrieves the day number within a year for a given date.
	 * 
	 * @param date
	 * @return day number of year
	 * 
	 */

	public static int extractDayOfYear(String date) {
		return extractFromDateOfYear("day", date);
	}

	/**
	 * Retrieves the week number within a year for a given date.
	 * 
	 * @param date
	 * @return month number of year
	 * 
	 */

	public static int extractWeekOfYear(String date) {
		return extractFromDateOfYear("week", date);
	}

	/**
	 * Retrieves the day, week or month number within a year for a given date.
	 * 
	 * @param query
	 * @param date
	 * @return integer
	 * 
	 */

	private static int extractFromDateOfYear(String query, String date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getDefault());
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		try {
			d = formatter.parse(date);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int fromDateOfYear = 0;

		if (query == "day") {
			fromDateOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		} else if (query == "week") {
			fromDateOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		}

		return fromDateOfYear;

	}

	public static final String[][] HELP_STRINGS = {
			{ "epochToHumanReadable", "Convert from Epoch to Human Readable Date.",
					"epochToHumanReadable(\"1678995698996\")", "2023-03-16T16:40:23.023-03:00" },
			{ "humanReadableToEpoch", "Convert from Human Readable Date to Epoch.",
					"humanReadableToEpoch(\"2023-03-16T16:40:23.023-03:00\")", "1678995623000" },
			{ "extractDayOfYear", "Retrieves the day number within a year for a given date.",
					"extractWeekOfYear(\"2023-12-31\")", "365" },
			{ "extractWeekOfYear", "Retrieves the week number within a year for a given date.",
					"extractWeekOfYear(\"2023-12-31\")", "53" },

	};
}
