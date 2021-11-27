package com.choi;

import java.nio.file.Path;

public class TargetsExporter {
    private Path path;
    private Targets targets;

    public void export(Path path, Targets targets) {
        this.path = path;
        this.targets = targets;
    }
}
