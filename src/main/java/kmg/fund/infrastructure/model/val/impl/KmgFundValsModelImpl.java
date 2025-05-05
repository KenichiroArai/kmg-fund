package kmg.fund.infrastructure.model.val.impl;

import java.util.ArrayList;
import java.util.List;

import kmg.core.infrastructure.model.val.KmgValDataModel;
import kmg.core.infrastructure.model.val.impl.KmgValsModelImpl;

/**
 * KMG基盤バリデーション集合モデル<br>
 * <p>
 * Valは、Validationの略。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public class KmgFundValsModelImpl extends KmgValsModelImpl {

    /**
     * データのリスト
     */
    private final List<KmgValDataModel> datas;

    /**
     * コンストラクタ
     *
     * @author KenichiroArai
     *
     * @since 0.1.0
     */
    public KmgFundValsModelImpl() {

        this.datas = new ArrayList<>();

    }

    /**
     * データを追加する。
     *
     * @param data
     *             追加するデータ
     *
     * @author KenichiroArai
     *
     * @since 0.1.0
     */
    @Override
    public void addData(final KmgValDataModel data) {

        /* 引数チェック */
        if (data == null) {

            return;

        }

        /* データを追加 */
        this.datas.add(data);

    }

    /**
     * データのリストを返す。
     *
     * @author KenichiroArai
     *
     * @since 0.1.0
     *
     * @return データのリスト
     */
    @Override
    public List<KmgValDataModel> getDatas() {

        final List<KmgValDataModel> result = this.datas;
        return result;

    }

}
