/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.services;

import java.util.Map;

/**
 *
 * @author Shree
 */
public interface MetricService {

    public void increaseCount(final String request, final int status);

    public Map getFullMetric();

    public Map getStatusMetric();

    public Object[][] getGraphData();
}
