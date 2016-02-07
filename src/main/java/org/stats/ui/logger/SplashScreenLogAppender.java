package org.stats.ui.logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.stats.utils.InternalStringUtils;
import org.stats.utils.SeparatedStringBuilder;

public class SplashScreenLogAppender extends AppenderSkeleton {
	private static final int TRUNCATE_LENGTH = 55;
	private List<LoggingEvent> lastLines = new ArrayList<LoggingEvent>();
	private static final Integer MAX_LINES = 10;
	private static JLabel loggingArea;

	public void close() {
	}

	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		if (this.lastLines.size() >= MAX_LINES) {
			lastLines.remove(0);
		}
		lastLines.add(event);
		decorate();
	}

	private void decorate() {
		if (null != loggingArea) {
			loggingArea.setText(getEventsHtml());
		}
	}

	private String getEventsHtml() {
		SeparatedStringBuilder builder = new SeparatedStringBuilder("<br>");
		Iterator<LoggingEvent> iterator = lastLines.iterator();
		while (iterator.hasNext()) {
			LoggingEvent event = iterator.next();
			builder.append(InternalStringUtils.truncate(getLayout().format(event), TRUNCATE_LENGTH, true));
		}
		return wrapHtml(builder.toString());
	}

	private String wrapHtml(String string) {
		return "<html>" + string + "</html>";
	}

	public static JLabel getLoggingArea() {
		return loggingArea;
	}

	public static void setLoggingArea(JLabel loggingArea) {
		SplashScreenLogAppender.loggingArea = loggingArea;
	}

}
