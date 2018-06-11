/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.controller;

import com.apple.flagpicker.services.MetricService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MReddy
 */
@RestController
@RequestMapping(path = "/metrics")
public class MetricsController {

    @Autowired
    private MetricService metricService;

    @GetMapping(path = "/")
    public Map getMetric() {
        return metricService.getFullMetric();
    }

    @GetMapping(path = "/status")
    public Map getStatusMetric() {
        return metricService.getStatusMetric();
    }

    @GetMapping(path = "/graph")
    public Object[][] drawMetric() {
        final Object[][] result = metricService.getGraphData();
        for (int i = 1; i < result[0].length; i++) {
            result[0][i] = result[0][i].toString();
        }
        return result;
    }
}
