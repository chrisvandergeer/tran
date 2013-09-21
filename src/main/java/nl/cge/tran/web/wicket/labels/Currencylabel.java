package nl.cge.tran.web.wicket.labels;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.DoubleConverter;

public class Currencylabel extends Label {
	private static final long serialVersionUID = 1L;
	
	public Currencylabel(String id, Double value) {
		super(id, new Model<Double>(value));
	}
	
	@Override
	public <C> IConverter<C> getConverter(Class<C> type) {
		DoubleConverter doubleConverter = new DoubleConverter();
		doubleConverter.setNumberFormat(new Locale("nl", "NL"), NumberFormat.getCurrencyInstance());
		return (IConverter<C>) doubleConverter;
	}

}
