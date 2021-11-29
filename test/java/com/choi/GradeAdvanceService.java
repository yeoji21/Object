package com.choi;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GradeAdvanceService {

    public static final Path DEFAULT_TARGETS_FILE = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/target");
//    private final Path targetsFilePath = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/target");
    private Path targetsFilePath = DEFAULT_TARGETS_FILE;
    private States states;
    private TargetGen targetGen;
    private TargetsExporter targetsExporter;
    private AdvanceApplier advanceApplier;
    private TargetsImporter targetsImporter;

    public void setTargetsFilePath(Path targetsFilePath) {
        this.targetsFilePath = targetsFilePath;
    }

    public GradeAdvanceService(States states, TargetGen targetGen,
                               TargetsExporter targetsExporter, AdvanceApplier advanceApplier,
                               TargetsImporter targetsImporter) {
        this.states = states;
        this.targetGen = targetGen;
        this.targetsExporter = targetsExporter;
        this.advanceApplier = advanceApplier;
        this.targetsImporter = targetsImporter;
    }

    public AdvanceResult advance() {
        AdvanceState state = states.get();
        Targets targets;

        if (state == AdvanceState.COMPLETED)
            return AdvanceResult.ALREADY_COMPLETED;
        if (state == AdvanceState.APPLY_FAILED) {
            targets = targetsImporter.importTargets(targetsFilePath);
        } else {
            try {
                targets = targetGen.gen();
            } catch (Exception e) {
                return AdvanceResult.TARGET_GEN_FAILED;
            }
            try {
                targetsExporter.export(targetsFilePath, targets);
            } catch (Exception e) {
                return AdvanceResult.TARGET_EXPORT_FAILED;
            }
        }
        try {
            advanceApplier.apply(targets);
        } catch (Exception e) {
            states.set(AdvanceState.APPLY_FAILED);
            return AdvanceResult.TARGET_APPLY_FAILED;
        }
        return AdvanceResult.SUCCESS;
    }
}
