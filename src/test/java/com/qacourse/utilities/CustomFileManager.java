package com.qacourse.utilities;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
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

    public static String readXlsxFromPath(String path) {
        String result = "";
        XSSFWorkbook myExcelBook = null;

        try {
            myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        Iterator<Row> rows = myExcelSheet.iterator();

        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                CellType cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
//                    case Cell.CELL_TYPE_STRING:
//                        result += cell.getStringCellValue() + "=";
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        result += "[" + cell.getNumericCellValue() + "]";
//                        break;
//
//                    case Cell.CELL_TYPE_FORMULA:
//                        result += "[" + cell.getNumericCellValue() + "]";
//                        break;
                    default:
                        result += cell.toString();
                        break;
                }
            }
        }

        try {
            myExcelBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
