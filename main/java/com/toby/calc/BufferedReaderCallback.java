package com.toby.calc;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    int doSomethingWithReader(BufferedReader br) throws IOException;
}
