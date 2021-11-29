package com.choi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class States {
    private Path path;

    public States(@Value("${states.path}") Path path) {
        this.path = path;
    }

    public AdvanceState get() {
        if (!Files.exists(path)) return null;
        try {
            List<String> lines = Files.readAllLines(path);
            return AdvanceState.valueOf(lines.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(AdvanceState advanceState) {
        try {
            Files.write(path, advanceState.name().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
