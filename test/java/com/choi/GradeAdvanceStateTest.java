package com.choi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class GradeAdvanceStateTest {
    private final Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/state");
    private final States state = new States(path);
    @Mock private Targets targets;
//    private TargetGen mockGen = mock(TargetGen.class);
    @Mock private TargetGen mockGen;
    @Mock private TargetsExporter mockExporter;
    @InjectMocks
    private final GradeAdvanceState service = new GradeAdvanceState(state, mockGen, mockExporter);
//    private final GradeAdvanceState service = new GradeAdvanceState(state, mockGen, mockExporter);


    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    void alreadyCompleted(){
        state.set(AdvanceState.COMPLETED);
        AdvanceResult result = service.advance();
        assertThat(result).isEqualTo(AdvanceResult.ALREADY_COMPLETED);
    }

    @Test
    void targetGenFail(){
        given(mockGen.gen()).willThrow(new RuntimeException());
        AdvanceResult result = service.advance();
        assertThat(result).isEqualTo(AdvanceResult.TARGET_GEN_FAILED);
    }

    @Test
    void targetExportFail(){
        given(mockGen.gen()).willReturn(mock(Targets.class));

        willThrow(new RuntimeException("!"))
                .given(mockExporter).export(any(Path.class), any(Targets.class));

        AdvanceResult result = service.advance();
        assertThat(result).isEqualTo(AdvanceResult.TARGET_EXPORT_FAILED);
    }

    private static class GradeAdvanceState {
        private States states;
        private TargetGen targetGen;
        private TargetsExporter targetsExporter;

        public GradeAdvanceState(States states, TargetGen targetGen, TargetsExporter targetsExporter) {
            this.states = states;
            this.targetGen = targetGen;
            this.targetsExporter = targetsExporter;
        }

        public AdvanceResult advance() {
            AdvanceState state = states.get();
            Targets targets;

            if (state == AdvanceState.COMPLETED)
                return AdvanceResult.ALREADY_COMPLETED;

            try {
                targets = targetGen.gen();
            } catch (Exception e) {
                return AdvanceResult.TARGET_GEN_FAILED;
            }
            try {
                targetsExporter.export(Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/target"), targets);
            } catch (Exception e) {
                return AdvanceResult.TARGET_EXPORT_FAILED;
            }
            return null;
        }
    }

    private enum AdvanceResult {
        TARGET_GEN_FAILED, TARGET_EXPORT_FAILED, ALREADY_COMPLETED
    }
}
