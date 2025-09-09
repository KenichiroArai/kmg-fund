package kmg.fund.infrastructure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import kmg.fund.infrastructure.exception.KmgFundMsgException;
import kmg.fund.infrastructure.types.msg.KmgFundGenMsgTypes;

/**
 * KMG YAMLユーティリティ<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public final class KmgYamlUtils {

    /**
     * YAML をロードし、マップ形式で返す<br>
     *
     * @param path
     *             ファイルパス
     *
     * @return マップ形式のデータ
     *
     * @throws KmgFundMsgException
     *                             入出力処理に失敗した場合
     */
    public static Map<String, Object> load(final Path path) throws KmgFundMsgException {

        Map<String, Object> result = null;

        final Yaml yaml = new Yaml();

        try (InputStream inputStream = Files.newInputStream(path)) {

            result = yaml.load(inputStream);

        } catch (final NoSuchFileException e) {

            final KmgFundGenMsgTypes genMsgType = KmgFundGenMsgTypes.KMGFUND_GEN24000;
            final Object[]           genMsgArgs = {
                path.toString()
            };
            throw new KmgFundMsgException(genMsgType, genMsgArgs, e);

        } catch (final IOException e) {

            final KmgFundGenMsgTypes genMsgType = KmgFundGenMsgTypes.KMGFUND_GEN24001;
            final Object[]           genMsgArgs = {
                path.toString()
            };
            throw new KmgFundMsgException(genMsgType, genMsgArgs, e);

        }

        return result;

    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    private KmgYamlUtils() {

        // 処理無し
    }
}
