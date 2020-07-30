package com.vehicletracking.filter;

import java.util.ArrayDeque;

public class meanfilter extends AverageFilter {

    private static final String tag = meanfilter.class.getSimpleName();

    private ArrayDeque<float[]> values;

    /**
     * Initialize a new MeanFilter object.
     */
    public meanfilter() {
        this(DEFAULT_TIME_CONSTANT);
    }

    public meanfilter(float timeConstant) {
        super(timeConstant);
        values = new ArrayDeque<>();
    }

}