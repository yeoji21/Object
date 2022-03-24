package com.toby.calc;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiFunction;

@Slf4j
public class Calculator {
    public int calcSum(String filePath) throws IOException {
        return lambdaTemplate(filePath, (line, value) -> value + Integer.parseInt(line), 0);

//        return lineReadTemplate(filePath, new LineCallback() {
//            @Override
//            public int doSomethingWithLine(String line, int value) {
//                return value + Integer.parseInt(line);
//            }
//        }, 0);
    }


    public int calcMultiply(String filePath) throws IOException {
        return lambdaTemplate(filePath, (line, value) -> value * Integer.parseInt(line), 1);

//        return lineReadTemplate(filePath, new LineCallback() {
//            @Override
//            public int doSomethingWithLine(String line, int value) {
//                return value * Integer.parseInt(line);
//            }
//        }, 1);
    }

    public int fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw e;
        }finally {
            if (br != null) {
                try{
                    br.close();
                }catch (IOException e){
                    log.warn(e.getMessage());
                }
            }
        }
    }

    public int lineReadTemplate(String filePath, LineCallback callback, int intVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            int sum = intVal;
            while ((line = br.readLine()) != null) {
                sum = callback.doSomethingWithLine(line, sum);
            }
            return sum;
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw e;
        }finally {
            if (br != null) {
                try{
                    br.close();
                }catch (IOException e){
                    log.warn(e.getMessage());
                }
            }
        }
    }

    public int lambdaTemplate(String filePath, BiFunction<String, Integer, Integer> callback, int intVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            int sum = intVal;
            while ((line = br.readLine()) != null) {
                sum = callback.apply(line, sum);
            }
            return sum;
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw e;
        }finally {
            if (br != null) {
                try{
                    br.close();
                }catch (IOException e){
                    log.warn(e.getMessage());
                }
            }
        }
    }
}
