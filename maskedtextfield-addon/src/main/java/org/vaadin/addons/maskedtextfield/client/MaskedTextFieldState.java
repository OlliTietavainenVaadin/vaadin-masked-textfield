package org.vaadin.addons.maskedtextfield.client;

import com.vaadin.shared.ui.textfield.TextFieldState;

public class MaskedTextFieldState extends TextFieldState {

    private static final long serialVersionUID = 1L;

    /**
     * The mask
     */
    public String mask="";

    /**
     * A Placeholder
     */
    public char maskPlaceHolder = '_';

}
