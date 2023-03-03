package ru.nic.hackaton.services;

import org.springframework.stereotype.Service;
import ru.nic.hackaton.requests.CalculateRequest;
import ru.nic.hackaton.responses.ResultResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeadService {
    public ResultResponse calculate(CalculateRequest request) {
        ResultResponse result = new ResultResponse();
        int x = request.getX();
        int y = request.getY();

        List<Double> estimates = new ArrayList<>();
        List<Long> angles = new ArrayList<>();

        double g = 0.01;
        int[] v_values = new int[10];
        for (int i = 0; i < v_values.length; i++) {
            v_values[i] = i*100 + 100;
        }

        double real_fita = get_real_angle(x, y);
        long fita = Math.round(real_fita);
        double S = get_S(x, y, fita, real_fita);

        for (int v_value : v_values) {
            long angle = Math.round(get_angle(S, v_value, g));
            if (angle == -0.1) {
                estimates.add(10000.0);
                angles.add(10000L);
                continue;
            }
            double s_estimate = get_estimate(v_value, angle, g);
            angles.add(angle);
            estimates.add(Math.abs(s_estimate - S));
        }

        int index = get_min_index(estimates);

        result.setAzimuth(fita);
        result.setElevation(angles.get(index));
        result.setSpeed(v_values[index] * 1000);

        return result;
    }

    private static int get_min_index(List<Double> estimates) {
        int indexOfMin = 0;
        for (int i = 1; i < estimates.size(); i++) {
            if (estimates.get(i) < estimates.get(indexOfMin)) {
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    private static double get_real_angle(int x, int y){
        return 90 - (Math.atan(y / x) / Math.PI * 180);
    }

    private static double get_angle(double s , int v, double g){
        double est = s * g / Math.pow(v, 2);

        if (est >= 1.0 || est <= 0.0){
            return -0.1  ;
        }

        return Math.asin(est) / Math.PI * 180 / 2;
    }

    private static double get_estimate(int v, double angle, double g){
        return Math.pow(v, 2) * Math.sin(2 * angle / 180 * Math.PI) / g;
    }

    private static double get_S(int x, int  y, double fita, double real_fita){
        double dist = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        double delta_alpha = Math.abs(fita - real_fita);
        return dist * Math.cos(delta_alpha);
    }
}
