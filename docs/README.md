# KMG 基盤（kmg-fund） ドキュメント

このディレクトリには、kmg-fund プロジェクトのドキュメントが含まれています。

## ドキュメント一覧

### 📚 API ドキュメント

- [Javadoc](javadoc/) - KMG ツールの API リファレンス

### メッセージ一覧.xlsx

- **説明**: kmg 基盤 プロジェクトで使用されるメッセージの定義一覧
- **形式**: Excel ファイル（.xlsx）
- **内容**:
  - 汎用メッセージ（KmgFundGenMsgTypes）
  - ログメッセージ（KmgFundLogMsgTypes）
  - バリデーションメッセージ（KmgFundValMsgTypes）
  - その他のメッセージ定義

## ドキュメントの更新について

### メッセージ一覧.xlsx の更新手順

1. **メッセージの追加・変更**

   - Excel ファイルを開いて該当シートを編集
   - メッセージ ID、メッセージ内容、説明を記入

2. **Java コードの更新**

   - メッセージ型クラス（`KmgFundGenMsgTypes`、`KmgFundLogMsgTypes`、`KmgFundValMsgTypes`）を更新
   - メッセージプロパティファイル（`kmg-fund-messages.properties`、`kmg-fund-messages-log.properties`）を更新

3. **テストの更新**
   - メッセージ型のテストクラスを更新
   - 必要に応じて統合テストを追加

## 注意事項

- メッセージ ID は一意である必要があります
- メッセージ内容は国際化を考慮して設計してください
- 既存のメッセージ ID を変更する場合は、影響範囲を十分に確認してください
- ドキュメントの更新後は、必ずテストを実行して動作確認を行ってください

## 関連ファイル

- `src/main/resources/kmg-fund-messages.properties` - メッセージ定義ファイル
- `src/main/resources/kmg-fund-messages-log.properties` - ログメッセージ定義ファイル
- `src/main/java/kmg/fund/infrastructure/types/msg/` - メッセージ型クラス
