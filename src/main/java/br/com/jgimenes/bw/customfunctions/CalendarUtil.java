package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;

public class CalendarUtil implements Serializable {

	private static final long serialVersionUID = -8167134812970450862L;

	
	/**
	 * 
	 * Provides a list of national holidays corresponding to the specified year.
	 * 
	 * @param year
	 * @return holidays
	 * 
	 */
	public static List<Holiday> getHolidays(int year) {

		List<Holiday> holidays = new ArrayList<>();

		holidays.add(new Holiday(LocalDate.of(year, 1, 1).format(dateFormatter), null, "Ano Novo"));
		holidays.add(
				new Holiday(getEasterDay(year).minusDays(48).format(dateFormatter), null, "Segunda-Feira de Carnaval"));
		holidays.add(new Holiday(getEasterDay(year).minusDays(47).format(dateFormatter), null, "Carnaval"));
		holidays.add(
				new Holiday(getEasterDay(year).minusDays(46).format(dateFormatter), null, "Quarta-Feira de Cinzas"));
		holidays.add(new Holiday(getEasterDay(year).minusDays(2).format(dateFormatter), null, "Sexta-Feira da Paixão"));
		holidays.add(new Holiday(getEasterDay(year).format(dateFormatter), null, "Domingo de Páscoa"));
		holidays.add(new Holiday(LocalDate.of(year, 4, 21).format(dateFormatter), null, "Dia de Tiradentes"));
		holidays.add(new Holiday(LocalDate.of(year, 5, 1).format(dateFormatter), null, "Dia do Trabalhador"));
		holidays.add(new Holiday(getEasterDay(year).minusDays(60).format(dateFormatter), null, "Corpo de Cristo"));
		holidays.add(new Holiday(LocalDate.of(year, 9, 7).format(dateFormatter), null, "Independência do Brasil"));
		holidays.add(
				new Holiday(LocalDate.of(year, 10, 12).format(dateFormatter), null, "Dia de Nossa Senhora Aparecida"));
		holidays.add(new Holiday(LocalDate.of(year, 11, 2).format(dateFormatter), null, "Dia de Finados"));
		holidays.add(new Holiday(LocalDate.of(year, 11, 15).format(dateFormatter), null, "Proclamação da República"));
		holidays.add(new Holiday(LocalDate.of(year, 12, 25).format(dateFormatter), null, "Natal"));

		for (Holiday holiday : holidays) {
			LocalDate currentDate = LocalDate.parse(holiday.getDate(), dateFormatter);
			String dayOfWeek = translateDayOfWeek(currentDate.getDayOfWeek().getValue());
			holiday.setDayOfWeek(dayOfWeek);
		}

		return holidays;
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

	/**
	 * Date formatting for the Brazilian calendar.
	 */

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

}
