package com.vaadin.training.bugrap.view.reports.components.converter;

import com.vaadin.data.util.converter.Converter;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class TimestampConverter implements Converter<String, Date> {

    @Override
    public Date convertToModel(String value, Locale locale) throws ConversionException {
        return null;
    }

    @Override
    public String convertToPresentation(Date value, Locale locale) throws ConversionException {
        PrettyTime prettyTime = new PrettyTime(Locale.US);

        return prettyTime.format(value);
    }

    @Override
    public Class<Date> getModelType() {
        return Date.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
