package org.vaadin.addons.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addons.maskedtextfield.DecimalField;
import org.vaadin.addons.maskedtextfield.MaskedTextField;
import org.vaadin.addons.maskedtextfield.NumericField;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@PreserveOnRefresh
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final MaskedTextField maskedTextField = new MaskedTextField("Masked field", "###-###-###");
        maskedTextField.addValueChangeListener(event -> System.out.println("New value of the masked field: " + maskedTextField.getValue()));
        maskedTextField.setMask("##-##-##");
        maskedTextField.setValue("454545454");
        //maskedTextField.setPlaceHolder(' ');
        maskedTextField.setPlaceHolder('\0');

        final DecimalField decimalField = new DecimalField("Decimal field");

        final NumericField numericField = new NumericField("Numeric field");

        setContent(new VerticalLayout(maskedTextField, decimalField, numericField));
}
}
