package it.tickets.manager.Model;

public enum TicketState {
    Open("Open"),
    Close("Close"),
    InProgress("In Progress");


    private final String displayValue;

    private TicketState(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
