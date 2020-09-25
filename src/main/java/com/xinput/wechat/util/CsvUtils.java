package com.xinput.wechat.util;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.StringReader;
import java.util.List;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-25 15:28
 */
public class CsvUtils {

    /**
     * 将csv文件内容转为实体对象
     *
     * @param csvText csv 格式数据
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readCsv(String csvText, Class<T> clazz) {
        return readCsv(csvText, 0, clazz);
    }

    /**
     * 将csv文件内容转为实体对象
     *
     * @param csvText    csv 格式数据
     * @param skipRowNum 跳过的行数，如果为空，默认全部读
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readCsv(String csvText, int skipRowNum, Class<T> clazz) {
        return readCsv(csvText, skipRowNum, 0, clazz);
    }

    /**
     * 将csv文件内容转为实体对象
     *
     * @param csvText    csv 格式数据
     * @param skipRowNum 跳过的行数，如果为空，默认全部读
     * @param readNum    指定读取的行数，如果为空，默认全部读
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readCsv(String csvText, int skipRowNum, int readNum, Class<T> clazz) {
        BeanListProcessor<T> rowProcessor = new BeanListProcessor<>(clazz);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setNumberOfRowsToSkip(skipRowNum);
        if (readNum > 0) {
            parserSettings.setNumberOfRecordsToRead(readNum);
        }
        parserSettings.setProcessor(rowProcessor);
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.getFormat().setDelimiter(',');
        CsvParser parser = new CsvParser(parserSettings);
        parser.parse(new StringReader(csvText));

        return rowProcessor.getBeans();
    }

}
