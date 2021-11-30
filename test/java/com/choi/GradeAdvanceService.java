package com.choi;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

@Service
public class GradeAdvanceService {

    public static final Path DEFAULT_TARGETS_FILE = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/target");
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
        if (state == AdvanceState.COMPLETED)
            return AdvanceResult.ALREADY_COMPLETED;

        Either<AdvanceResult, Targets> targetsEither =
                state == AdvanceState.APPLY_FAILED ? importTargets(): genAndExportTargets();

        Either<AdvanceResult, ApplyResult> applyResultEither =
                targetsEither.flatMap(applyAdvanceTargets(targetsEither.getRight()));

        if (applyResultEither.isLeft())
            return applyResultEither.getLeft();
        return AdvanceResult.SUCCESS;
    }

    private Function<Targets, Either<AdvanceResult, ApplyResult>> applyAdvanceTargets(Targets targets) {
        return ts -> {
            try {
                return Either.right(advanceApplier.apply(targets));
            } catch (Exception e) {
                states.set(AdvanceState.APPLY_FAILED);
                return Either.left(AdvanceResult.TARGET_APPLY_FAILED);
            }
        };
    }

    private Either<AdvanceResult, Targets> importTargets() {
        try {
            return Either.right(targetsImporter.importTargets(targetsFilePath));
        } catch (Exception e) {
            return Either.left(AdvanceResult.TARGET_IMPORT_FAILED);
        }
    }

    private Either<AdvanceResult, Targets> genAndExportTargets() {
        return genTargets().flatMap(exportTargets());
    }

    private Function<Targets, Either<AdvanceResult, Targets>> exportTargets() {
        return ts -> {
            try {
                targetsExporter.export(targetsFilePath, ts);
                return Either.right(ts);
            } catch (Exception e) {
                return Either.left(AdvanceResult.TARGET_EXPORT_FAILED);
            }
        };
    }

    private Either<AdvanceResult, Targets> genTargets() {
        try {
            return Either.right(targetGen.gen());
        } catch (Exception e) {
            return Either.left(AdvanceResult.TARGET_GEN_FAILED);
        }
    }

}
