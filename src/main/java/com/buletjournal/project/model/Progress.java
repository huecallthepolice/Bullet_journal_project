package com.buletjournal.project.model;

public enum Progress {
    In_Progress("In progress right now"),
    Cancelled("Cancelled for now"),
    Completed("Already done"),
    Moved("Moved to another date");

    private final String displayValue;

    private Progress(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}