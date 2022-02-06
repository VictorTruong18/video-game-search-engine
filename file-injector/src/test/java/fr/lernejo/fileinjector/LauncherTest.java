package fr.lernejo.fileinjector;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class LauncherTest {
    @Test
    void test_good_file_inject_success() {
        File resourcesDirectory = new File("src/test/resources");
        String file = resourcesDirectory.getAbsolutePath()+"/games.json";
        assertTimeoutPreemptively(Duration.ofSeconds(60L),() -> Launcher.main(new String[]{file}));
    }
}
