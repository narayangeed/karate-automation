package com.ngt.karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner {

    @Test
    void testAll() {
        Results results = Runner.path("classpath:features").tags("@e2e")
                .outputCucumberJson(true)
                .reportDir("target/surefire-reports")
                .backupReportDir(false)
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
        generateReport(results.getReportDir());
    }

    public static void generateReport(String karateOutputPath){
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "surefire-reports");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
