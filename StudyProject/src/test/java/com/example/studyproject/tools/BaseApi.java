package com.example.studyproject.tools;

import com.example.studyproject.service.DbHandler;
import org.springframework.http.server.reactive.HttpHandler;

public class BaseApi {
    public BaseApi(DbHandler dbHandler, HttpHandler localhost) {
    }

    public void setup() {

    }
}
