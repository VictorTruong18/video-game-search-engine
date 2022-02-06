package fr.lernejo.fileinjector;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class LauncherTest {
    @Test
    void test_good_file_inject_success() {
        File resourcesDirectory = new File("src/test/resources");
        String file = resourcesDirectory.getAbsolutePath()+"/games.json";
        assertTimeoutPreemptively(Duration.ofSeconds(60L),() -> Launcher.main(new String[]{file}));
    }

    @Test
    void test_no_file_inject_stopped(){
        assertTimeoutPreemptively(Duration.ofSeconds(10L),() -> Launcher.main(new String[]{}));
    }

    @Test
    void test_wrong_file_inject_exception_caught(){
        assertThrows(IOException.class, () -> Launcher.main(new String[]{"test/wrong_file.json"}));
    }

}
