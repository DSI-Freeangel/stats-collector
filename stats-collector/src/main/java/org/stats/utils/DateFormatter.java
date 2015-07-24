package org.stats.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.springframework.util.StringUtils;

public class DateFormatter extends AbstractFormatter {

	private static final long serialVersionUID = -6906670514506537710L;
	
	private DateFormat format;
	
	public DateFormatter(DateFormat dateFormat){
		format = dateFormat;
	}
	
	@Override
	public String valueToString(Object value) throws ParseException {
		Calendar cal = (Calendar)value;
		if (cal == null) {
			return "";
		}
		return format.format(cal.getTime());
	}
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		Date date = format.parse(text);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

}