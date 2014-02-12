package com.nextfour.datetimelabel.client.datetimelabel;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.nextfour.datetimelabel.DateTimeLabel;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.ui.Connect;

@Connect(DateTimeLabel.class)
public class DateTimeLabelConnector extends LabelConnector {

    private Timer t = null;

    public DateTimeLabelConnector() {
    }

    private void setDate() {
        String dateFormat = getState().dateFormat;
        Date now = new Date();
        String dateStr;
        if (!dateFormat.isEmpty()) {
            try {
                DateTimeFormat fmt = DateTimeFormat.getFormat(dateFormat);
                dateStr = fmt.format(now);
            } catch (IllegalArgumentException e) {
                // Fallback to default if error in parsing
                dateStr = now.toString();
            }
        } else {
            dateStr = now.toString();
        }
        getWidget().setText(dateStr);
    }

    private void startTimer(int intervalMs) {
        if (t == null) {
            t = new Timer() {
                @Override
                public void run() {
                    setDate();
                }
            };
        } else {
            t.cancel();
        }
        t.scheduleRepeating(intervalMs);
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(VLabel.class);
    }

    @Override
    public VLabel getWidget() {
        return super.getWidget();
    }

    @Override
    public DateTimeLabelState getState() {
        return (DateTimeLabelState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        setDate();
        startTimer(getState().refreshIntervalMs);
    }
}
