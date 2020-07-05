package com.bridgelabz.statecensusanalyser.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JsonWrite {
    public static void writeJson(String filePath , List a) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(a, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
