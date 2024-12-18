package com.uns.api.backend.shipments.models;

import java.util.List;

public class Fedex {
    private String transactionId;
    private Output output;
    private CompleteTrackResult completeTrackResult;
    private TrackResult trackResult;
    private LatestStatusDetail latestStatusDetail;

    public static class Output {
        private List<CompleteTrackResult> completeTrackResults;

        // Getters and setters
        public List<CompleteTrackResult> getCompleteTrackResults() {
            return completeTrackResults;
        }

        public void setCompleteTrackResults(List<CompleteTrackResult> completeTrackResults) {
            this.completeTrackResults = completeTrackResults;
        }
    }

    public static class CompleteTrackResult {
        private String trackingNumber;
        private List<TrackResult> trackResults;

        // Getters and setters
        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public List<TrackResult> getTrackResults() {
            return trackResults;
        }

        public void setTrackResults(List<TrackResult> trackResults) {
            this.trackResults = trackResults;
        }
    }

    public static class TrackResult {
        private LatestStatusDetail latestStatusDetail;
        private Error error;
        // Getters and setters for all fields
        // ...

        public LatestStatusDetail getLatestStatusDetail() {
            return latestStatusDetail;
        }

        public Error getError() {
            return error;
        }

        public void setLatestStatusDetail(LatestStatusDetail latestStatusDetail, Error error) {
            this.latestStatusDetail = latestStatusDetail;
            this.error = error;
        }
    }

    public static class Error {
        private String code;
        private String message;

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class LatestStatusDetail {
        private String code;
        private String derivedCode;
        private String statusByLocale;
        private String description;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDerivedCode() {
            return derivedCode;
        }

        public void setDerivedCode(String derivedCode) {
            this.derivedCode = derivedCode;
        }

        public String getStatusByLocale() {
            return statusByLocale;
        }

        public void setStatusByLocale(String statusByLocale) {
            this.statusByLocale = statusByLocale;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public Fedex(String transactionId, Output output, CompleteTrackResult completeTrackResult, TrackResult trackResult,
            LatestStatusDetail latestStatusDetail) {
        this.transactionId = transactionId;
        this.output = output;
        this.completeTrackResult = completeTrackResult;
        this.trackResult = trackResult;
        this.latestStatusDetail = latestStatusDetail;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public Output getOutput() {
        return output;
    }

    public CompleteTrackResult getCompleteTrackResult() {
        return completeTrackResult;
    }

    public TrackResult getTrackResult() {
        return trackResult;
    }

    public LatestStatusDetail getLatestStatusDetail() {
        return latestStatusDetail;
    }

    // Setters

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public void setCompleteTrackResult(CompleteTrackResult completeTrackResult) {
        this.completeTrackResult = completeTrackResult;
    }

    public void setTrackResult(TrackResult trackResult) {
        this.trackResult = trackResult;
    }

    public void setLatestStatusDetail(LatestStatusDetail latestStatusDetail) {
        this.latestStatusDetail = latestStatusDetail;
    }

}
