package com.choi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: 2021/11/26 36:06 
public class GradeAdvanceStateTest {
    private final Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/state");
    private final States state = new States(path);
    private final GradeAdvanceState service = new GradeAdvanceState(state);
    
    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    void alreadyCompleted(){
        state.set(AdvanceState.COMPLETED);
        assertThrows(AlreadyCompletedException.class, service::advance);
    }

    @Test
    void targetGenFail(){
    }

    private class GradeAdvanceState {
        private States states;

        public GradeAdvanceState(States states) {
            this.states = states;
        }

        public void advance() {
            AdvanceState state = states.get();
            if (state == AdvanceState.COMPLETED)
                throw new AlreadyCompletedException();
        }
    }

    private class AlreadyCompletedException extends RuntimeException{
    }
}
