package org.vaadin.addons.maskedtextfield;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

/**
 * @author mavi
 */
@Widgetset("org.vaadin.addons.maskedtextfield.MaskedTextFieldWidgetset")
public class TestUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        final MaskedTextField maskedTextField = new MaskedTextField("Masked field", "###-###-###");
        maskedTextField.addValueChangeListener(event -> System.out.println("New value of the masked field: " + maskedTextField.getValue()));

        final DecimalField decimalField = new DecimalField("Decimal field");

        final NumericField numericField = new NumericField("Numeric field");

        setContent(new VerticalLayout(maskedTextField, decimalField, numericField));
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = TestUI.class, productionMode = false)
    public static class MyServlet extends VaadinServlet {
    }
}
