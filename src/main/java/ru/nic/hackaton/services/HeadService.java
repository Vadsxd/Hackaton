package ru.nic.hackaton.services;

import org.springframework.stereotype.Service;
import ru.nic.hackaton.requests.CalculateRequest;
import ru.nic.hackaton.responses.ResultResponse;

@Service
public class HeadService {
    public ResultResponse calculate(CalculateRequest request) {
        ResultResponse result = new ResultResponse();

        return result;
    }
}
