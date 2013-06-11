package com.vaadin.training.bugrap.view.mvp;


import java.util.List;

public abstract class Presenter {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void viewEntered(String params);

    public abstract void projectVersionChanged(String version);

    public abstract void reportsStatusFilterChanged(List<ReportStatus> statuses);

    public abstract void reportsCustomFilterChanged(List<ReportStatus> statuses, ArrayList<ReportResolution> reportResolutions);
}
