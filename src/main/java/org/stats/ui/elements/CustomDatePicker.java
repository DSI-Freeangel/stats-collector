package org.stats.ui.elements;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

import org.stats.utils.DateFormatter;

public class CustomDatePicker extends JDatePickerImpl {
	private static final long serialVersionUID = -4615837924634900840L;

	public CustomDatePicker(DateFormat dateFormat) {
		super(new JDatePanelImpl(new UtilCalendarModel()), new DateFormatter(dateFormat));
	}

	public CustomDatePicker setDate(Date date) {
		if (null != date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			this.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		}
		return this;
	}

	public Date getDate() {
		Calendar value = (Calendar) this.getModel().getValue();
		if (null != value) {
			value.set(Calendar.AM_PM, Calendar.AM);
			value.set(Calendar.HOUR, 0);
			value.set(Calendar.MINUTE, 0);
			value.set(Calendar.SECOND, 0);
			value.set(Calendar.MILLISECOND, 0);
			return value.getTime();
		}
		return null;
	}

}
