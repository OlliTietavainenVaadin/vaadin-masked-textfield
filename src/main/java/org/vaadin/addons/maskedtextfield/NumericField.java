package org.vaadin.addons.maskedtextfield;

import com.vaadin.ui.TextField;

public class NumericField extends TextField {

	private static final long serialVersionUID = 1L;

	public NumericField() {
		super();
	}

	public void setValue(Number number) {
		if(number != null) {
			setValue(String.valueOf(number));
		} else {
			setValue( (String) null);
		}
	}

	public NumericField(String caption, String value) {
		super(caption, value);
	}

	public NumericField(String caption) {
		super(caption);
	}
}
