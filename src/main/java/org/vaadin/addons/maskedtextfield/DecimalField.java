package org.vaadin.addons.maskedtextfield;

import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.AbstractStringToNumberConverter;
import com.vaadin.ui.TextField;
import org.vaadin.addons.maskedtextfield.client.DecimalFieldState;
import org.vaadin.addons.maskedtextfield.server.Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class DecimalField extends TextField {

	private static final long serialVersionUID = 1L;

	private MaskNumberConverter localConverter = null;
	private MaskNumberConverter converter;

	public DecimalField() {
		super();
		initConverter();
	}

	public DecimalField(String caption, String value) {
		super(caption, value);
		initConverter();
	}

	public DecimalField(String caption) {
		super(caption);
		initConverter();
	}
	
	public DecimalField(String mask, char decimalSeparator, char groupingSeparator) {
		super();
		setMask(mask);
		setDecimalSeparator(decimalSeparator);
		setGroupingSeparator(groupingSeparator);
		initConverter();
	}

	private void initConverter() {
		localConverter = new MaskNumberConverter(0, "Error converting string to number");
		setConverter(localConverter);
	}
	
	@Override
	public void setValue(String string) {
		super.setValue(string);
	}

	public void setValue(Number number) {
		if (number != null) {
			if (getConverter() != null) {
				String v = getConverter().convertToPresentation(number, new ValueContext(getLocale()));
				setValue(v);
			} else {
				setValue((String) null);
			}
		} else {
			setValue((String) null);
		}
	}
	
	public void setMask(String mask) {
		if(mask == null) {
			throw new NullPointerException("The format mask cannot be null");
		}
		if(mask.trim().isEmpty()) {
			throw new IllegalStateException("The format mask cannot be empty");
		}
		getState().mask = mask;
		if(localConverter != null)
			localConverter.refreshFormatter();
	}
	
	public String getMask() {
		return getState().mask;
	}
	
	public void setDecimalSeparator(char decimalSeparator) {
		getState().decimalSeparator = decimalSeparator;
	}
	
	public char getDecimalSeparator() {
		return getState().decimalSeparator;
	}
	
	public void setGroupingSeparator(char groupingSeparator) {
		getState().groupingSeparator = groupingSeparator;
	}
	
	public char getGroupingSeparator() {
		return getState().groupingSeparator;
	}
	
	@Override
	public DecimalFieldState getState() {
		return (DecimalFieldState) super.getState();
	}
	
	public void setSelectValueOnFocus(boolean selectOnFocus) {
		getState().selectValuesOnFocus = selectOnFocus;
	}
	
	public boolean isSelectValueOnFocus() {
		return getState().selectValuesOnFocus;
	}

	public void setConverter(MaskNumberConverter converter) {
		this.converter = converter;
	}

	public MaskNumberConverter getConverter() {
		return converter;
	}

	/**
	 * Custom converter to handle custom separators
	 * @author eduardo
	 *
	 */
	private class MaskNumberConverter extends AbstractStringToNumberConverter<Number> {

		private static final long serialVersionUID = 1L;

		private DecimalFormat formatter;

		protected MaskNumberConverter(Number emptyValue, String errorMessage) {
			super(emptyValue, errorMessage);
			refreshFormatter();
		}

		public void refreshFormatter() {
			if(formatter == null || 
					(	formatter.getDecimalFormatSymbols().getGroupingSeparator() != getGroupingSeparator()
					||  formatter.getDecimalFormatSymbols().getDecimalSeparator() != getDecimalSeparator()
					)
			) 
			{
				DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols();
				decimalSymbols.setGroupingSeparator(getGroupingSeparator());
				decimalSymbols.setDecimalSeparator(getDecimalSeparator());
				formatter = new DecimalFormat(getMask());
				formatter.setDecimalFormatSymbols(decimalSymbols);
			}
		}


		@Override
		public Result<Number> convertToModel(String value, ValueContext valueContext) {
			refreshFormatter();
			try {
				if(value == null || value.trim().isEmpty()) {
					return null;
				}
				Number number = formatter.parse(value);
				return Result.ok(number);
			} catch (ParseException e) {
				return Result.ok(0);
			}
		}
	}

}
