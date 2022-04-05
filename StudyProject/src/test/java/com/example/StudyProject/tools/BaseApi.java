package com.example.StudyProject.tools;

import com.example.StudyProject.service.DbHandler;
import org.springframework.http.server.reactive.HttpHandler;

public class BaseApi {
    public BaseApi(DbHandler dbHandler, HttpHandler localhost) {
    }

    public void setup() {

    }
}
