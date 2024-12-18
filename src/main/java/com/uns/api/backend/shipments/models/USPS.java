package com.uns.api.backend.shipments.models;

import java.util.List;

public class USPS {
    private String trackingNumber;
    private EventSummaries eventSummaries;

    // error fields
    private String apiVersion;
    private Error error;

    public USPS(String trackingNumber, EventSummaries eventSummaries) {
        this.trackingNumber = trackingNumber;
        this.eventSummaries = eventSummaries;
    }

    public USPS(String apiVersion, Error error) {
        this.apiVersion = apiVersion;
        this.error = error;
    }

    public String getTrackingNumber() {
        if (trackingNumber == null) {
            return "Tracking number not found";
        }
        return trackingNumber;
    }

    public EventSummaries getEventSummaries() {
        if (eventSummaries == null) {
            return new EventSummaries(List.of("No events found"));
        }
        return eventSummaries;
    }

    public String getApiVersion() {
        if (apiVersion == null) {
            return "API version not found";
        }
        return apiVersion;
    }

    public Error getError() {
        if (error == null) {
            return new Error("No error found", "No error found",
                    List.of(new USPS.Error("No error found", "No error found", List.of()).new Errors("No error found",
                            "No error found", "No error found")));
        }
        return error;
    }

    public static class EventSummaries {
        private List<String> eventSummary;

        public EventSummaries(List<String> eventSummary) {
            this.eventSummary = eventSummary;
        }

        public List<String> getEventSummary() {
            return eventSummary;
        }
    }

    public static class Error {
        private String code;
        private String message;
        private List<Errors> errors;

        public Error(String code, String message, List<Errors> errors) {
            this.code = code;
            this.message = message;
            this.errors = errors;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<Errors> getErrors() {
            return errors;
        }

        public class Errors {
            private String title;
            private String detail;
            private String code;

            public Errors(String title, String detail, String code) {
                this.title = title;
                this.detail = detail;
                this.code = code;
            }

            public String getTitle() {
                return title;
            }

            public String getDetail() {
                return detail;
            }

            public String getCode() {
                return code;
            }
        }

    }
}
