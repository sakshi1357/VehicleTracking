package com.vehicletracking.filter;


public class lowpassfilter extends AverageFilter {
    private static final String tag = lowpassfilter.class.getSimpleName();

    // Gravity and linear accelerations components for the
    // Wikipedia low-pass filter
    private float[] output;

    public lowpassfilter() {
        this(DEFAULT_TIME_CONSTANT);
    }

    public lowpassfilter(float timeConstant) {
        this.timeConstant = timeConstant;
        reset();
    }
}