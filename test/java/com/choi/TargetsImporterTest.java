package com.choi;

import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TargetsImporterTest {

    private final TargetsImporter importer = new TargetsImporter();

    @Test
    void importTargets_given_noFile(){
        assertThrows(NoTargetsFileException.class, () -> importer.importTargets(Paths.get("/dasds/adsad/sa/dsa")));
    }

    @Test
    void importTargets_given_badFormatFile() throws IOException {
        Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/badformat");
        FileCopyUtils.copy("105=5\n1066", new FileWriter(path.toFile()));
        assertThrows(TargetsFileBadFormatException.class, () -> importer.importTargets(path));
    }

    @Test
    void importTargets_given_FileExists() throws IOException {
        Path path = Paths.get("/Users/yeojiwon/Desktop/Spring Project/object/src/main/resources/tfile");
        FileCopyUtils.copy("105=5\n106=6", new FileWriter(path.toFile()));
        Targets targets = importer.importTargets(path);
        List<User> users = targets.getUsers();
        assertThat(users).hasSize(2);
        assertThat(users.get(0)).isEqualTo(new User(105, 5));
        assertThat(users.get(1)).isEqualTo(new User(106, 6));
    }
}
