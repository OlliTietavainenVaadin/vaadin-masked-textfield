package org.vaadin.addons.maskedtextfield.client;

import com.vaadin.shared.ui.textfield.TextFieldState;

public class DecimalFieldState extends TextFieldState {

    private static final long serialVersionUID = 1L;

    public char decimalSeparator = '.';

    public char groupingSeparator = ',';

    public String mask = "#.00";

    public boolean selectValuesOnFocus = false;

}
