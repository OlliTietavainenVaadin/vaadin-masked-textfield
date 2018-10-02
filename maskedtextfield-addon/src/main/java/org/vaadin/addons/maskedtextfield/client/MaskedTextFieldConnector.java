package org.vaadin.addons.maskedtextfield.client;

import com.google.gwt.core.client.GWT;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.addons.maskedtextfield.MaskedTextField;

@Connect(MaskedTextField.class)
public class MaskedTextFieldConnector extends TextFieldConnector {

	private static final long serialVersionUID = 1L;
	
		@Override
		protected MaskedTextFieldWidget createWidget() {
			return GWT.create(MaskedTextFieldWidget.class);
		}
		
		@Override
		public MaskedTextFieldWidget getWidget() {
			return (MaskedTextFieldWidget) super.getWidget();
		}

		@Override
		public MaskedTextFieldState getState() {
			return (MaskedTextFieldState) super.getState();
		}

		@Override
		public void onStateChanged(StateChangeEvent stateChangeEvent) {
			getWidget().setPlaceHolder(getState().maskPlaceHolder);
			getWidget().setMask(getState().mask);
			super.onStateChanged(stateChangeEvent);
		}
	
}
