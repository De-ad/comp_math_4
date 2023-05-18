package com.vich_mat.services;

import com.vich_mat.entities.Dots;

import com.vich_mat.entities.Result;
import com.vich_mat.payload.SuccessResponse;
import com.vich_mat.payload.DotsRequest;
import com.vich_mat.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DotsService {
    @Autowired
    LinearApproximation linearApproximation;
    @Autowired
    ExponentialApproximation exponentialApproximation;
    @Autowired
    LogarithmicApproximation logarithmicApproximation;
    @Autowired
    PowerApproximation powerApproximation;
    @Autowired
    TwoPolynomialApproximation twoPolynomialApproximation;
    @Autowired
    ThreePolynomialApproximation threePolynomialApproximation;


    public List<Result> calculate(@RequestBody DotsRequest request) {

        Dots dots = new Dots(request.getXArray(), request.getYArray(), request.getYArray().length);
        List<Result> list = new ArrayList<>();
        list.add(linearApproximation.calculate(dots));
        list.add(twoPolynomialApproximation.calculate(dots));
//        list.add(threePolynomialApproximation.calculate(dots));
        list.add(exponentialApproximation.calculate(dots));
        list.add(logarithmicApproximation.calculate(dots));
        list.add(powerApproximation.calculate(dots));
        return list;
    }
}
