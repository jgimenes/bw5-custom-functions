package br.com.jgimenes.bw.customfunctions;

import java.io.IOException;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;

public class CalendarUtil implements Serializable {

	private static final long serialVersionUID = -8167134812970450862L;

	/**
	 * 
	 * Custom calendar function for validating workdays and Brazilian national
	 * holidays, based on the Febraban calendar available at
	 * https://feriadosbancarios.febraban.org.br/.
	 * 
	 * @author jgimenes
	 * @version 1.0
	 * 
	 * 
	 */

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String KEY_DATE = "date";
	private static final String KEY_DAY_OF_WEEK = "dayOfWeek";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_NEXT_WORKDAY = "workday";
	private static final String KEY_NEXT_WORKDAY_OF_WEEK = "workdayOfWeek";

	/**
	 * 
	 * This function determines the next working day based on the provided date.
	 * 
	 * @param date
	 * @return date, day of week, holiday description if present, next workday and
	 *         next workday of week
	 * 
	 */

	public static String getWorkday(String date) {
		String getHoliday = getHoliday(date);
		String getWorkday = getWorkdaysOfMonth(LocalDate.parse(date, dateFormatter).getMonthValue(),
				LocalDate.parse(date, dateFormatter).getYear());
		ObjectMapper mapper = new ObjectMapper();
		JsonNode holidayNode = null;
		JsonNode workdayNode = null;
		String nextWorkday = "";
		boolean foundDate = false;

		try {
			holidayNode = mapper.readTree(getHoliday);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error reading the JSON:", e.getMessage()));
		}

		try {
			workdayNode = mapper.readTree(getWorkday);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error reading the JSON:", e.getMessage()));
		}

		LocalDate searchDate = LocalDate.parse(date, dateFormatter);

		while (!foundDate) {

			for (JsonNode workdays : workdayNode.get("workday")) {
				LocalDate workdayDate = LocalDate.parse(workdays.get("date").asText(), dateFormatter);

				if (workdayDate.equals(searchDate)) {
					nextWorkday = workdays.get("date").asText();
					foundDate = true;
					break;
				}
			}
			if (!foundDate) {
				searchDate = searchDate.plusDays(1);

			}
		}

		Map<String, String> workday = new LinkedHashMap<>();

		workday.put(KEY_DATE, date);
		workday.put(KEY_DAY_OF_WEEK,
				translateDayOfWeek(LocalDate.parse(date, dateFormatter).getDayOfWeek().getValue()));
		if (!getHoliday.isEmpty()) {
			workday.put(KEY_DESCRIPTION, holidayNode.path(KEY_DESCRIPTION).asText());
		}
		if (!date.equals(nextWorkday)) {
			workday.put(KEY_NEXT_WORKDAY, nextWorkday);
			workday.put(KEY_NEXT_WORKDAY_OF_WEEK,
					translateDayOfWeek(LocalDate.parse(nextWorkday, dateFormatter).getDayOfWeek().getValue()));
		}

		String json = "";
		try {
			json = mapper.writeValueAsString(workday);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error converting object to JSON:", e.getMessage()));
			return "";
		}

		return json;

	}

	/**
	 * 
	 * Retrieve a list of working days for the given month and year.
	 * 
	 * @param month
	 * @param year
	 * @return workdays list of month
	 * 
	 */

	public static String getWorkdaysOfMonth(int month, int year) {

		int currentMonth = month;
		int currentYear = year;
		LocalDate date = LocalDate.of(year, month, 1);
		List<Map<String, String>> workdays = new ArrayList<>();
		String json = "";

		while (currentMonth == month && currentYear == year) {

			String strCurrentDate = date.format(dateFormatter);
			Boolean isHoliday = getHoliday(strCurrentDate).isEmpty() ? false : true;
			Boolean isWeekand = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
					? true
					: false;

			if (!isHoliday && !isWeekand) {
				Map<String, String> workday = new HashMap<>();
				workday.put(KEY_DATE, strCurrentDate);
				workday.put(KEY_DAY_OF_WEEK, translateDayOfWeek(date.getDayOfWeek().getValue()));
				workdays.add(workday);
			}

			date = date.plusDays(1);
			currentYear = date.getYear();
			currentMonth = date.getMonthValue();

		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonObjectRoot = Collections.singletonMap("workday", workdays);

		try {
			json = mapper.writeValueAsString(jsonObjectRoot);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error converting object to JSON:", e.getMessage()));
		}

		return json;
	}

	/**
	 * 
	 * Retrieve a list of working days for the given year.
	 * 
	 * @param year
	 * @return workdays list of year
	 * 
	 */

	public static String getWorkdaysOfYear(int year) {

		int currentYear = year;
		LocalDate date = LocalDate.of(year, 1, 1);
		List<Map<String, String>> workdays = new ArrayList<>();
		String json = "";

		while (currentYear == year) {

			String strCurrentDate = date.format(dateFormatter);
			Boolean isHoliday = getHoliday(strCurrentDate).isEmpty() ? false : true;
			Boolean isWeekand = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
					? true
					: false;

			if (!isHoliday && !isWeekand) {
				Map<String, String> workday = new HashMap<>();
				workday.put(KEY_DATE, strCurrentDate);
				workday.put(KEY_DAY_OF_WEEK, translateDayOfWeek(date.getDayOfWeek().getValue()));
				workdays.add(workday);
			}

			date = date.plusDays(1);
			currentYear = date.getYear();

		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonObjectRoot = Collections.singletonMap("workday", workdays);

		try {
			json = mapper.writeValueAsString(jsonObjectRoot);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error converting object to JSON:", e.getMessage()));
		}

		return json;
	}

	/**
	 * Returns a JSON string containing information about a holiday based on the
	 * provided date.
	 * 
	 * @param date
	 * @return a JSON string containing information about a holiday based on the
	 *         provided date.
	 * 
	 */

	public static String getHoliday(String date) {
		int year = LocalDate.parse(date, dateFormatter).getYear();
		String jsonHoliday = getHolidays(year);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;

		try {
			rootNode = mapper.readTree(jsonHoliday.toString());
		} catch (IOException e) {
			System.out.println(String.format("Error reading the JSON:", e.getMessage()));
			return "";
		}

		Map<String, String> holiday = new HashMap<>();

		Iterator<JsonNode> holidayNodes = rootNode.path("holiday").elements();

		while (holidayNodes.hasNext()) {
			JsonNode holidayNode = holidayNodes.next();
			String strDate = holidayNode.path(KEY_DATE).asText();
			LocalDate holidayDate;
			try {
				holidayDate = LocalDate.parse(strDate, dateFormatter);
			} catch (DateTimeParseException e) {
				System.out.println(String.format("Invalid date in JSON:", e.getMessage()));
				continue;
			}

			if (holidayDate.equals(LocalDate.parse(date, dateFormatter))) {
				holiday.put(KEY_DATE, holidayNode.path(KEY_DATE).asText());
				holiday.put(KEY_DAY_OF_WEEK, holidayNode.path(KEY_DAY_OF_WEEK).asText());
				holiday.put(KEY_DESCRIPTION, holidayNode.path(KEY_DESCRIPTION).asText());
				break;
			}
		}

		String json = "";
		try {
			json = mapper.writeValueAsString(holiday);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error converting object to JSON:", e.getMessage()));
			return "";
		}

		if (holiday.isEmpty()) {
			return "";
		}

		return json;
	}

	/**
	 * 
	 * Provides a list of national holidays corresponding to the specified year.
	 * 
	 * @param year
	 * @return holidays list
	 * 
	 */

	public static String getHolidays(int year) {

		List<Map<String, Object>> holidays = new ArrayList<>();

		holidays.add(mapHoliday(LocalDate.of(year, 1, 1), "Ano Novo"));
		holidays.add(mapHoliday(getEasterDay(year).minusDays(48), "Segunda-Feira de Carnaval"));
		holidays.add(mapHoliday(getEasterDay(year).minusDays(47), "Carnaval"));
		holidays.add(mapHoliday(getEasterDay(year).minusDays(46), "Quarta-Feira de Cinzas"));
		holidays.add(mapHoliday(getEasterDay(year).minusDays(2), "Sexta-Feira da Paixão"));
		holidays.add(mapHoliday(getEasterDay(year), "Páscoa"));
		holidays.add(mapHoliday(LocalDate.of(year, 4, 21), "Dia de Tiradentes"));
		holidays.add(mapHoliday(LocalDate.of(year, 5, 1), "Dia do Trabalhador"));
		holidays.add(mapHoliday(getEasterDay(year).plusDays(60), "Corpo de Cristo"));
		holidays.add(mapHoliday(LocalDate.of(year, 9, 7), "Independência do Brasil"));
		holidays.add(mapHoliday(LocalDate.of(year, 10, 12), "Dia de Nossa Senhora Aparecida"));
		holidays.add(mapHoliday(LocalDate.of(year, 11, 2), "Dia de Finados"));
		holidays.add(mapHoliday(LocalDate.of(year, 11, 15), "Proclamação da República"));
		holidays.add(mapHoliday(LocalDate.of(year, 12, 25), "Natal"));

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonObjectRoot = Collections.singletonMap("holiday", holidays);

		try {
			return mapper.writeValueAsString(jsonObjectRoot);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	 * Generate Holidays list
	 * 
	 * @param date
	 * @param description
	 * @return holiday
	 */

	private static Map<String, Object> mapHoliday(LocalDate date, String description) {
		Map<String, Object> holiday = new HashMap<>();
		holiday.put(KEY_DATE, date.format(dateFormatter));
		holiday.put(KEY_DAY_OF_WEEK, translateDayOfWeek(date.getDayOfWeek().getValue()));
		holiday.put(KEY_DESCRIPTION, description);
		return holiday;
	}

	/**
	 * 
	 * Performs the translation of the days of the week from English to Portuguese.
	 * 
	 * @param dayOfWeek
	 * @return poruguese day of week
	 * 
	 */

	private static String translateDayOfWeek(int dayOfWeek) {
		return daysOfWeek.get(dayOfWeek);

	}

	/**
	 * 
	 * This function returns the date of Easter for the specified year and adds it
	 * to the calendar. This function uses the Meeus-Jones-Butcher algorithm for the
	 * calculation.
	 * 
	 * @param year
	 * @return easter date
	 * 
	 */

	private static LocalDate getEasterDay(int year) {
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int month = (h + l - 7 * m + 114) / 31;
		int day = ((h + l - 7 * m + 114) % 31) + 1;
		return LocalDate.of(year, month, day);
	}

	/*
	 * Translation of the days of the week from English to Portuguese.
	 */

	private static final Map<Integer, String> daysOfWeek = new HashMap<>();
	static {
		daysOfWeek.put(1, "segunda-feira");
		daysOfWeek.put(2, "terça-feira");
		daysOfWeek.put(3, "quarta-feira");
		daysOfWeek.put(4, "quinta-feira");
		daysOfWeek.put(5, "sexta-feira");
		daysOfWeek.put(6, "sábado");
		daysOfWeek.put(7, "domingo");
	}

	public static final String[][] HELP_STRINGS = { { "getWorkday",
			"This function determines the next working day based on the provided date.", "getWorkday(\"01/01/2023\")",
			"{\"date\":\"01/01/2023\",\"dayOfWeek\":\"domingo\",\"description\":\"Ano Novo\",\"workday\":\"02/01/2023\",\"workdayOfWeek\":\"segunda-feira\"}" },
			{ "getWorkdaysOfMonth", "Retrieve a list of working days for the given month and year.",
					"getWorkdaysOfMonth(01, 2023)",
					"{\"workday\": [{\"date\": \"02/01/2023\", \"dayOfWeek\": \"segunda-feira\"}, ... {\"date\": \"31/01/2023\", \"dayOfWeek\": \"terça-feira\"} ]}" },
			{ "getWorkdaysOfYear", "Retrieve a list of working days for the given year.", "getWorkdaysOfYear(2023)",
					"{\"workday\": [{\"date\": \"02/01/2023\", \"dayOfWeek\": \"segunda-feira\"}, ... {\"date\": \"29/12/2023\", \"dayOfWeek\": \"sexta-feira\"}]}" },
			{ "getHoliday", "Returns a JSON string containing information about a holiday based on the provided date.",
					"getHoliday(\"01/01/2023\")",
					"{\"date\": \"01/01/2023\", \"dayOfWeek\": \"segunda-feira\", \"description\": \"Ano Novo\"}" },
			{ "getHolidays", "Returns a JSON string containing information about a holiday based on the provided date.",
					"getHolidays(2023)",
					"{\"holiday\": [{\"date\": \"01/01/2023\", \"dayOfWeek\": \"domingo\", \"description\": \"Ano Novo\"}, ... {\"date\": \"25/12/2023\", \"dayOfWeek\": \"segunda-feira\", \"description\": \"Natal\"}]}" },

	};

}
