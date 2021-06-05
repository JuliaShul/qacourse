package com.qacourse;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.qacourse.utilities.CustomFileManager;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

class SelenideWorkWithFileTests {

    @BeforeAll
    static void selenideSettings() {
        Configuration.downloadsFolder = "download";
        Configuration.headless = true;
    }

    @AfterAll
    static void removedDownloads() {
        CustomFileManager.removeDownloadDirectory("download");
    }

    @Test
    void downloadFileTest() throws FileNotFoundException {
        String actualTitle = "cypress-allure-plugin";

        open("https://github.com/Shelex/cypress-allure-plugin/blob/master/README.md");
        File downloadedFile = $("#raw-url").download();
        String content = CustomFileManager.readTextFromFile(downloadedFile).get();

        assertThat(content, containsString(actualTitle));
    }

    @Test
    void readTxtFileTest() {
        String filePath = "src/test/resources/file.txt",
                actualData = "The first program that usually beginners create is Hello world.";

        String fileContent = CustomFileManager.readTextFromPath(filePath).get();

        assertThat(fileContent, containsString(actualData));
    }

    @Test
    void readPdfFileTest() {
        String pdfFilePath = "src/test/resources/Quick Start.pdf",
                actualData = "For Maven users";

        PDF pdfContent = CustomFileManager.getPdf(pdfFilePath).get();

        assertThat(pdfContent, PDF.containsText(actualData));
    }


    @Test
    void readXlsxFileTest() {
        String pdfFilePath = "src/test/resources/test.xlsx",
                actualData = "igor@email.com";

        XLS xlsContent = CustomFileManager.getXls(pdfFilePath);
        String string = xlsContent.excel
                .getSheetAt(0)
                .getRow(2)
                .getCell(1)
                .toString();

        assertThat(string, containsString(actualData));
    }

    @Test
    void zipWithPasswordTest() throws ZipException {
        String zipFilePath = "./src/test/resources/Quick_Start.zip",
                unzipFolderPath = "./src/test/resources/unzip",
                zipPassword = "123",
                unzipPdfFilePath = "./src/test/resources/unzip/Quick Start.pdf",
                text = "For Maven users";

        CustomFileManager.unzip(zipFilePath, unzipFolderPath, zipPassword);

        assertThat(CustomFileManager.getPdf(unzipPdfFilePath).get(), PDF.containsText(text));
    }
}
