package com.nextfour.datetimelabel;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("datetimelabel")
public class DatetimelabelUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        // Create new DateTimeLabel
        final DateTimeLabel dateTimeLabel = new DateTimeLabel();

        CssLayout layout = new CssLayout();
        final TextField tf1 = new TextField("Refresh interval (ms):");
        final TextField tf2 = new TextField("Format (leave empty for default):");
        Button updateButton = new Button("Update");
        updateButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    dateTimeLabel.setRefreshIntervalMs(Integer.parseInt(tf1
                            .getValue()));
                } catch (NumberFormatException e) {
                    dateTimeLabel.setRefreshIntervalMs(1000);
                }
                dateTimeLabel.setDateFormat(tf2.getValue());
            }
        });
        layout.addComponent(tf1);
        layout.addComponent(tf2);
        layout.addComponent(updateButton);
        layout.addComponent(dateTimeLabel);
        setContent(layout);
    }
}