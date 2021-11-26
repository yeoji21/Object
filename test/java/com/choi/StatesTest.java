package com.choi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatesTest {
    private final Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/state");
    States states = new States(path);

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    public void no_state_yet(){
        AdvanceState state = states.get();
        assertThat(state).isNull();
    }

    @Test
    public void setState() throws IOException {
        states.set(AdvanceState.GENERATING);

        List<String> lines = Files.readAllLines(path);
        assertThat(lines.get(0)).isEqualTo(AdvanceState.GENERATING.name());
    }

    @Test
    public void get() throws IOException {
        FileCopyUtils.copy(AdvanceState.GENERATING.name(), new FileWriter(path.toFile()));

        AdvanceState advanceState = states.get();
        assertThat(advanceState).isEqualTo(AdvanceState.GENERATING);
    }

}
