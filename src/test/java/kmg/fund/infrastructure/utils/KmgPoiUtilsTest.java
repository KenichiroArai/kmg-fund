package kmg.fund.infrastructure.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * KMGＰＯＩユーティリティテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls", "static-method"
})
public class KmgPoiUtilsTest {

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgPoiUtilsTest() {

        // 処理なし
    }

    /**
     * getCell メソッドのテスト - 異常系:行が存在しない場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetCell_errorNoRow() throws Exception {

        /* 期待値の定義 */
        final Cell expected = null;

        /* 準備 */
        Cell testTarget;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet = workbook.createSheet();
            testTarget = KmgPoiUtils.getCell(sheet, 0, 0);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, testTarget, "行が存在しない場合はnullを返すべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 異常系:エラーを返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_errorErrorFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        Cell   testCell;
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet = workbook.createSheet();
            final Row   row   = sheet.createRow(0);
            testCell = row.createCell(0);
            testCell.setCellFormula("1/0"); // エラーを発生させる数式
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（エラー）が返されるべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 正常系:真偽値を返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_normalBooleanFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = "true";

        /* 準備 */
        Cell   testCell;
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet = workbook.createSheet();
            final Row   row   = sheet.createRow(0);
            testCell = row.createCell(0);
            testCell.setCellFormula("TRUE");
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（真偽値）が返されるべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 正常系:数値を返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_normalNumericFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = "123.0";

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellFormula("123.0");
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（数値）が返されるべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 正常系:文字列を返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_normalStringFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = "test";

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellFormula("\"test\"");
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（文字列）が返されるべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 準正常系:空白を返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_semiBlankFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellFormula("INDIRECT(\"\"&\"\")"); // BLANKを返す数式
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（空白）が返されるべき");

    }

    /**
     * getStringFormulaValue メソッドのテスト - 準正常系:_NONEを返す数式の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringFormulaValue_semiNoneFormula() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellFormula("NA()"); // _NONEを返す数式
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testCell);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringFormulaValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "数式の計算結果（_NONE）が返されるべき");

    }

    /**
     * getStringRangeValue メソッドのテスト - 正常系:結合セルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringRangeValue_normalMergedCell() throws Exception {

        /* 期待値の定義 */
        final String expected = "test";

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet     = workbook.createSheet();
            final Row   row       = sheet.createRow(0);
            final Cell  firstCell = row.createCell(0);
            firstCell.setCellValue("test");
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 1));
            final Cell testCell = sheet.getRow(0).createCell(1);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringRangeValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "結合セルの左上のセルの値が返されるべき");

    }

    /**
     * getStringRangeValue メソッドのテスト - 準正常系:結合セル範囲外の場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringRangeValue_semiOutsideMergedRegion() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet     = workbook.createSheet();
            final Row   row       = sheet.createRow(0);
            final Cell  firstCell = row.createCell(0);
            firstCell.setCellValue("test");
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 1)); // (0,0)-(1,1)の範囲を結合

            // 結合範囲外のセル(2,2)を作成
            final Row  outsideRow = sheet.createRow(2);
            final Cell testCell   = outsideRow.createCell(2);

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringRangeValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "結合範囲外のセルの場合はnullを返すべき");

    }

    /**
     * getStringValue メソッドのテスト - 正常系:真偽値セルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_normalBooleanCell() throws Exception {

        /* 期待値の定義 */
        final String expected = "true";

        /* 準備 */
        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet      = workbook.createSheet();
            final Row   row        = sheet.createRow(0);
            final Cell  testTarget = row.createCell(0);
            testTarget.setCellValue(true);

            /* テスト対象の実行 */
            final String actual = KmgPoiUtils.getStringValue(testTarget);

            /* 検証の実施 */
            Assertions.assertEquals(expected, actual, "セルの真偽値が文字列として返されるべき");

        }

    }

    /**
     * getStringValue メソッドのテスト - 正常系:数式セルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_normalFormulaCell() throws Exception {

        /* 期待値の定義 */
        final String expected = "123.0";

        /* 準備 */
        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet      = workbook.createSheet();
            final Row   row        = sheet.createRow(0);
            final Cell  testTarget = row.createCell(0);
            testTarget.setCellFormula("123.0");
            workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCell(testTarget);

            /* テスト対象の実行 */
            final String actual = KmgPoiUtils.getStringValue(testTarget);

            /* 検証の実施 */
            Assertions.assertEquals(expected, actual, "数式セルの場合は計算結果が文字列として返されるべき");

        }

    }

    /**
     * getStringValue メソッドのテスト - 正常系:数値セルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_normalNumericCell() throws Exception {

        /* 期待値の定義 */
        final String expected = "123.0";

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellValue(123.0);
            actual = KmgPoiUtils.getStringValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "セルの数値が文字列として返されるべき");

    }

    /**
     * getStringValue メソッドのテスト - 正常系:文字列セルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_normalStringCell() throws Exception {

        /* 期待値の定義 */
        final String expected = "test";

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellValue("test");
            actual = KmgPoiUtils.getStringValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "セルの文字列値が返されるべき");

    }

    /**
     * getStringValue メソッドのテスト - 準正常系:エラーセルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_semiErrorCell() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet      = workbook.createSheet();
            final Row   row        = sheet.createRow(0);
            final Cell  testTarget = row.createCell(0);
            testTarget.setCellErrorValue(FormulaError.DIV0.getCode());

            /* テスト対象の実行 */
            final String actual = KmgPoiUtils.getStringValue(testTarget);

            /* 検証の実施 */
            Assertions.assertEquals(expected, actual, "エラーセルの場合はnullを返すべき");

        }

    }

    /**
     * getStringValue メソッドのテスト - 準正常系:NONEセルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_semiNoneCell() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        String actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            // _NONEタイプのセルを作成（通常は直接作成できないため、この方法で代用）
            testCell.setBlank();
            testCell.removeCellComment();

            /* テスト対象の実行 */
            actual = KmgPoiUtils.getStringValue(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "_NONEタイプのセルの場合はnullを返すべき");

    }

    /**
     * getStringValue メソッドのテスト - 準正常系:nullの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testGetStringValue_semiNull() throws Exception {

        /* 期待値の定義 */
        final String expected = null;

        /* 準備 */
        final Cell testTarget = null;

        /* テスト対象の実行 */
        final String actual = KmgPoiUtils.getStringValue(testTarget);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "nullの場合はnullを返すべき");

    }

    /**
     * isEmptyCell メソッドのテスト - 正常系:空のセルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testIsEmptyCell_normalEmptyCell() throws Exception {

        /* 期待値の定義 */
        final boolean expected = true;

        /* 準備 */
        boolean actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setBlank();
            actual = KmgPoiUtils.isEmptyCell(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "空のセルの場合はtrueを返すべき");

    }

    /**
     * isEmptyCell メソッドのテスト - 正常系:空でないセルの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testIsEmptyCell_normalNonEmptyCell() throws Exception {

        /* 期待値の定義 */
        final boolean expected = false;

        /* 準備 */
        boolean actual;

        try (Workbook workbook = WorkbookFactory.create(true)) {

            final Sheet sheet    = workbook.createSheet();
            final Row   row      = sheet.createRow(0);
            final Cell  testCell = row.createCell(0);
            testCell.setCellValue("test");
            actual = KmgPoiUtils.isEmptyCell(testCell);

        }

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "値が入っているセルの場合はfalseを返すべき");

    }

    /**
     * isEmptyCell メソッドのテスト - 準正常系:nullの場合
     *
     * @since 0.1.0
     *
     * @throws Exception
     *                   例外が発生した場合
     */
    @Test
    public void testIsEmptyCell_semiNull() throws Exception {

        /* 期待値の定義 */
        final boolean expected = true;

        /* 準備 */
        final Cell testTarget = null;

        /* テスト対象の実行 */
        final boolean actual = KmgPoiUtils.isEmptyCell(testTarget);

        /* 検証の実施 */
        Assertions.assertEquals(expected, actual, "nullの場合はtrueを返すべき");

    }
}
