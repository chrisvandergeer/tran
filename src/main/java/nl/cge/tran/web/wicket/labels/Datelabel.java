package nl.cge.tran.web.wicket.labels;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.DateConverter;

public class Datelabel extends Label {
	private static final long serialVersionUID = 1L;

	public Datelabel(String id, Date datum) {
		super(id, new Model<Date>(datum));
	}
	
	@Override
	public <C> IConverter<C> getConverter(Class<C> type) {
		return (IConverter<C>) new DateConverter() {
			private static final long serialVersionUID = 1L;
			@Override
			public String convertToString(Date value, Locale locale) {
				return DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("nl", "NL")).format(value);
			}
		};
	}

}
