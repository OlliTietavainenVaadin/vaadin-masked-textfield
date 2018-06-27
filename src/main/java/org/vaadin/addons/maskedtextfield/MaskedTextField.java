package org.vaadin.addons.maskedtextfield;

import java.util.Arrays;


import org.vaadin.addons.maskedtextfield.client.MaskedTextFieldState;

import org.vaadin.addons.maskedtextfield.shared.Constants;

import com.vaadin.ui.TextField;

/**
 * Server side component for the VMaskedTextField widget.
 */
public class MaskedTextField extends TextField {

	private static final long serialVersionUID = 1L;
	
	private char digitRepresentation = '#';
	
	private Boolean maskClientOnly = false;
	
	public MaskedTextField() {
		super();
	}

	public MaskedTextField(String caption) {
		setCaption(caption);
	}

	public MaskedTextField(String caption, String mask) {
		setCaption(caption);
		setMask(mask);
	}

	private void validateNumberPropertyWithMask() {
		char[] maskChars = getMask().replaceAll("\\+", "").toCharArray();
		for(char s : maskChars) {
			if(Arrays.binarySearch(Constants.MASK_REPRESENTATIONS, s) >= 0 && s != digitRepresentation) {
				throw new IllegalArgumentException("This mask is not compatible with numeric datasources");
			}
		}
	}

	public String getMask() {
		return getState().mask;
	}
	
	public void setMask(String mask) {
		getState().mask = mask;
	}
	
	public char getPlaceHolder() {
		return getState().maskPlaceHolder;
	}
	
	public void setPlaceHolder(char placeHolder) {
		getState().maskPlaceHolder = placeHolder;
	}
	
	public boolean isMaskClientOnly() {
		return maskClientOnly.booleanValue();
	}

	@Override
	protected MaskedTextFieldState getState() {
		return (MaskedTextFieldState) super.getState();
	}
	
	protected String unmask(final String value, String mask) {
		if(value == null || value.trim().isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder(value);
		mask = mask.replaceAll("\\+", "");
		int removedChars = 0;
		for(int i = 0; i<mask.length(); i++) {
			char s = mask.charAt(i);
			if(Arrays.binarySearch(Constants.MASK_REPRESENTATIONS, s) < 0) {
				if(i < value.length() && sb.charAt(i-removedChars) == s) {
					sb.deleteCharAt(i-removedChars);
					removedChars++;
				}
			}
		}
		return sb.toString();
	}
	
	protected String unmask(final String value) {
		return unmask(value, getMask());
	}

}