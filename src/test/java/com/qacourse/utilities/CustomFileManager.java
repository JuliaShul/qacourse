package com.qacourse.utilities;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Optional;


public class CustomFileManager {

    public static Optional<String> readTextFromFile(File file) {
        try {
            return Optional.ofNullable(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void removeDownloadDirectory(String directoryPath) {
        try {
            FileUtils.deleteDirectory(new File(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<String> readTextFromPath(String path) {
        return readTextFromFile(getFile(path));
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static Optional<PDF> getPdf(String path) {
        try {
            return Optional.of(new PDF(getFile(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static XLS getXls(String path) {
        return new XLS(getFile(path));
    }

    public static void unzip(String path, String unzipPath, String password) throws ZipException {
        ZipFile zipFile = new ZipFile(path);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password);
        }
        zipFile.extractAll(unzipPath);
    }

    public static void unzip(String path, String unzipPath) {
        try {
            unzip(path, unzipPath, "");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    public static String readDocFile(String filePath) {

        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WordExtractor we = new WordExtractor(doc);


        return we.getText();
    }

        public static String readDocxFile(String fileName) {

        File file = new File(fileName);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XWPFWordExtractor extractor = new XWPFWordExtractor(docx);

        return extractor.getText();
    }

}
