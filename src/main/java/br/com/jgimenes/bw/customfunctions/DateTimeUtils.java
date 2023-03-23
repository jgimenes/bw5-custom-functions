package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils implements Serializable {

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

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date day = null;
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		try {
			day = formatter.parse(date);

			calendar.setTime(day);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return calendar.get(Calendar.DAY_OF_YEAR);

	}

	/**
	 * Retrieves the week number within a year for a given date.
	 * 
	 * @param date
	 * @return month number of year
	 * 
	 */

	public static int extractWeekOfYear(String date) {

		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		weekFields.getFirstDayOfWeek();
		weekFields.getMinimalDaysInFirstWeek();
		TemporalField weekOfYear = weekFields.weekOfYear();
		LocalDate day = LocalDate.parse(date);

		return day.get(weekOfYear);
	}

	/**
	 * Retrieves the day, week or month number within a year for a given date.
	 * 
	 * @param query
	 * @param date
	 * @return integer
	 * 
	 */

	public static final String[][] HELP_STRINGS = {
			{ "epochToHumanReadable", "Convert Epoch time to a readable date format.",
				"epochToHumanReadable(\"1678995698996\")", "2023-03-16T16:40:23.023-03:00" },
			{ "humanReadableToEpoch", "Convert a readable date format to an Epoch timestamp..",
					"humanReadableToEpoch(\"2023-03-16T16:40:23.023-03:00\")", "1678995623000" },
			{ "extractDayOfYear", "Retrieves the day number within a year for a given date.",
						"extractWeekOfYear(\"2023-12-31\")", "365" },
			{ "extractWeekOfYear", "Retrieves the week number within a year for a given date.",
							"extractWeekOfYear(\"2023-12-31\")", "53" },

	};
}
