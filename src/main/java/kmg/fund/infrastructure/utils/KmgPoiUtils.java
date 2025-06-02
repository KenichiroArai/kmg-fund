package kmg.fund.infrastructure.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import kmg.core.infrastructure.type.KmgString;

/**
 * KMGＰＯＩユーティリティー<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public final class KmgPoiUtils {

    /**
     * セルを返す<br>
     *
     * @since 0.1.0
     *
     * @param sheet
     *                    シート
     * @param rowIndex
     *                    行番号
     * @param columnIndex
     *                    カラム番号
     *
     * @return セル
     */
    public static Cell getCell(final Sheet sheet, final int rowIndex, final int columnIndex) {

        Cell result = null;

        final Row row = sheet.getRow(rowIndex);

        if (row == null) {

            return result;

        }

        result = row.getCell(columnIndex);
        return result;

    }

    /**
     * セルの数式を計算し、値を返す（String型）<br>
     *
     * @since 0.1.0
     *
     * @param cell
     *             セル
     *
     * @return セルの数式の計算結果（String型）
     */
    public static String getStringFormulaValue(final Cell cell) {

        String result = null;

        switch (cell.getCachedFormulaResultType()) {

            case STRING:
                result = cell.getStringCellValue();
                break;

            case NUMERIC:
                result = Double.toString(cell.getNumericCellValue());
                break;

            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;

            case BLANK:
            case ERROR:
            case FORMULA:
            case _NONE:
                break;

        }
        return result;

    }

    /**
     * 結合セルの値を返す（String型）<br>
     *
     * @since 0.1.0
     *
     * @param cell
     *             セル
     *
     * @return 結合セルの値（String型）
     */
    public static String getStringRangeValue(final Cell cell) {

        String result = null;

        final int rowIndex    = cell.getRowIndex();
        final int columnIndex = cell.getColumnIndex();

        final Sheet sheet = cell.getSheet();
        final int   size  = sheet.getNumMergedRegions();

        for (int i = 0; i < size; i++) {

            final CellRangeAddress range = sheet.getMergedRegion(i);

            if (!range.isInRange(rowIndex, columnIndex)) {

                continue;

            }
            final Cell firstCell = KmgPoiUtils.getCell(sheet, range.getFirstRow(), range.getFirstColumn()); // 左上のセルを取得
            result = KmgPoiUtils.getStringValue(firstCell);
            break;

        }
        return result;

    }

    /**
     * セルの値を返す<br>
     *
     * @since 0.1.0
     *
     * @param cell
     *             セル
     *
     * @return セルの値
     */
    public static String getStringValue(final Cell cell) {

        String result = null;

        if (cell == null) {

            return result;

        }

        switch (cell.getCellType()) {

            case STRING:
                result = cell.getStringCellValue();
                break;

            case NUMERIC:
                result = Double.toString(cell.getNumericCellValue());
                break;

            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;

            case FORMULA:
                result = KmgPoiUtils.getStringFormulaValue(cell);
                break;

            case BLANK:
                result = KmgPoiUtils.getStringRangeValue(cell);
                break;

            case ERROR:
            case _NONE:
                break;

        }
        return result;

    }

    /**
     * セルが空か<br>
     *
     * @since 0.1.0
     *
     * @param cell
     *             セル
     *
     * @return true：空、false：空ではない
     */
    public static boolean isEmptyCell(final Cell cell) {

        boolean result = true;

        if (cell == null) {

            return result;

        }

        if (KmgString.isEmpty(KmgPoiUtils.getStringValue(cell))) {

            return result;

        }

        result = false;
        return result;

    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    private KmgPoiUtils() {

        // 処理無し
    }

}
