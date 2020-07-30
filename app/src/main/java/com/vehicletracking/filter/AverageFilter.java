package com.vehicletracking.filter;

import com.vehicletracking.BaseFilter;

public abstract class AverageFilter implements BaseFilter {
    public static float DEFAULT_TIME_CONSTANT = 0.18f;

    protected float timeConstant;
    protected long startTime;
    protected long timestamp;
    protected int count;

    public AverageFilter() {
        this(DEFAULT_TIME_CONSTANT);
    }

    public AverageFilter(float timeConstant) {
        this.timeConstant = timeConstant;
        reset();
    }

    public void reset() {
        startTime = 0;
        timestamp = 0;
        count = 0;
    }
}
