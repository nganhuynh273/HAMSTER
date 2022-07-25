package com.example.hamster.service.impl;

import com.example.hamster.model.AccompaniedService;
import com.example.hamster.dao.impl.AccompaniedServiceDAOImpl;
import com.example.hamster.service.IAccompaniedServiceService;

import java.util.List;

public class AccompaniedServiceServiceImpl implements IAccompaniedServiceService {
    AccompaniedServiceDAOImpl attachServiceDAO = new AccompaniedServiceDAOImpl();
    public List<AccompaniedService> showAllAccompaniedService() {
        return null;
    }
}
