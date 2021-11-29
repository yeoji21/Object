package com.choi;

import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class GradeAdvanceServiceTest {
    private final Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/state");
    private final States states = new States(path);

    @Mock private TargetGen mockGen;
    @Mock private TargetsExporter mockExporter;
    @Mock private AdvanceApplier mockApplier;
    @Mock private TargetsImporter mockImporter ;
    @InjectMocks private GradeAdvanceService service = new GradeAdvanceService(states, mockGen, mockExporter, mockApplier, mockImporter);

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    void alreadyCompleted(){
        states.set(AdvanceState.COMPLETED);

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

    @Test
    void applyFail(){
        given(mockGen.gen()).willReturn(mock(Targets.class));
        given(mockApplier.apply(any(Targets.class))).willThrow(new RuntimeException());

        AdvanceResult result = service.advance();

        assertThat(result).isEqualTo(AdvanceResult.TARGET_APPLY_FAILED);
    }

    @Test
    void applyFail_then_state_applyFailed(){
        given(mockGen.gen()).willReturn(mock(Targets.class));
        given(mockApplier.apply(any(Targets.class))).willThrow(new RuntimeException());

        service.advance();

        assertThat(states.get()).isEqualTo(AdvanceState.APPLY_FAILED);
    }

    @Test
    void applySuccess(){
        given(mockGen.gen()).willReturn(mock(Targets.class));
        given(mockApplier.apply(any(Targets.class))).willReturn(mock(ApplyResult.class));

        AdvanceResult result = service.advance();

        assertThat(result).isEqualTo(AdvanceResult.SUCCESS);
    }

    @Test
    void advance_call_when_status_is_applyFailed(){
        states.set(AdvanceState.APPLY_FAILED);
        Targets targets = new Targets(null);
        given(mockImporter.importTargets(any(Path.class))).willReturn(targets);

        service.advance();

        then(mockGen).shouldHaveNoInteractions();
        then(mockExporter).shouldHaveNoInteractions();
        then(mockApplier).should().apply(eq(targets));
    }
}
