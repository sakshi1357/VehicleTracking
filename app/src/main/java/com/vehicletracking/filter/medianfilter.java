package com.vehicletracking.filter;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayDeque;
import java.util.Arrays;

public class medianfilter extends AverageFilter {

    private static final String tag = medianfilter.class
            .getSimpleName();

    private ArrayDeque<float[]> values;


    /**
     * Initialize a new MeanFilter object.
     */
    public medianfilter() {
        this(DEFAULT_TIME_CONSTANT);
    }

    public medianfilter(float timeConstant) {
        this.timeConstant = timeConstant;
        this.values = new ArrayDeque<>();

        reset();
    }

    /**
     * Filter the data.
     *
     * @param data contains input the data.
     * @return the filtered output data.
     */
    public float[] filter(float[] data) {

        if (startTime == 0) {
            startTime = System.nanoTime();
        }

        timestamp = System.nanoTime();

        // Find the sample period (between updates) and convert from
        // nanoseconds to seconds. Note that the sensor delivery rates can
        // individually vary by a relatively large time frame, so we use an
        // averaging technique with the number of sensor updates to
        // determine the delivery rate.
        float hz = (count++ / ((timestamp - startTime) / 1000000000.0f));

        int filterWindow = (int) Math.ceil(hz * timeConstant);

        values.addLast(Arrays.copyOf(data, data.length));

        while (values.size() > filterWindow) {
            values.removeFirst();
        }

        return getMean(values);
    }

    /**
     * Get the mean of the data set.
     *
     * @param data the data set.
     * @return the mean of the data set.
     */
    private float[] getMean(ArrayDeque<float[]> data) {
        float[] mean = new float[3];

        double[][] values = new double[3][data.size()];
        int index = 0;

        for (float[] axis : data) {
            for (int i = 0; i < axis.length; i++) {
                values[i][index] = axis[i];
            }
            index++;
        }

        for (int i = 0; i < mean.length; i++) {
            mean[i] = (float) StatUtils.percentile(values[i], 50);
        }

        return mean;
    }

    public void setTimeConstant(float timeConstant) {
        this.timeConstant = timeConstant;
    }

    public void reset() {
        super.reset();

        if(values != null) {
            this.values.clear();
        }
    }
}

