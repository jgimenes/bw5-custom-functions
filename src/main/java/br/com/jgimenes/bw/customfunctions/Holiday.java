package br.com.jgimenes.bw.customfunctions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "data", "diaDaSemana", "descricao" })
public class Holiday {
	
	/**
	 * 
	 * Holiday Model Class
	 *
	 */


	@JsonProperty("data")
	private String date;

	@JsonProperty("diaDaSemana")
	private String dayOfWeek;

	@JsonProperty("descricao")
	private String description;

	public Holiday() {

	}

	public Holiday(String date, String dayOfWeek, String description) {
		this.date = date;
		this.dayOfWeek = dayOfWeek;
		this.description = description;

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDescription() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}

	/*public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}*/

}
