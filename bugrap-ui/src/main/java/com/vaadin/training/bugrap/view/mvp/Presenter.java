package com.vaadin.training.bugrap.view.mvp;

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
}
