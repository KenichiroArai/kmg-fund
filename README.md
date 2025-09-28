# KMG 基盤（kmg-fund）

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.x-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

kmg-fund（以降は、「KMG 基盤」と言う。）は、KMG シリーズの Java アプリケーション開発のための基盤となるライブラリです。

特徴は、Spring Boot フレームワークをベースに、多くのプロジェクトで使用できるように構成されています。

## 概要

本プロジェクトは、以下の機能を提供します：

- Spring Boot アプリケーション基盤
- プロパティファイル管理機能
- メッセージ管理機能
- バリデーション機能
- ユーティリティクラス群
- JavaFX サポート

## ドキュメント

詳細なドキュメントは [docs/](docs/) ディレクトリに格納されています：

## プロジェクト構成

```bash
kmg-fund/
├── src/
│   ├── main/java/kmg/fund/
│   │   ├── domain/
│   │   │   └── types/           # ドメイン型定義
│   │   ├── infrastructure/
│   │   │   ├── cmn/            # 共通機能
│   │   │   │   └── msg/       # メッセージ型
│   │   │   ├── config/         # 設定
│   │   │   ├── context/        # コンテキスト
│   │   │   ├── exception/      # 例外処理
│   │   │   ├── model/          # モデル
│   │   │   │   └── val/       # バリデーションモデル
│   │   │   │       └── impl/  # バリデーション実装
│   │   │   ├── test/           # テスト基盤
│   │   │   ├── types/          # 型定義
│   │   │   │   └── msg/       # メッセージ型
│   │   │   └── utils/          # ユーティリティクラス
│   │   └── KmgFundApplication.java  # メインアプリケーション
│   ├── main/resources/
│   │   ├── application*.properties  # アプリケーション設定
│   │   ├── kmg-fund-*.properties     # KMG基盤設定
│   │   ├── logback-*.xml            # ログ設定
│   │   └── META-INF/
│   │       └── spring.factories     # Spring自動設定
│   └── test/java/kmg/fund/
│       ├── domain/
│       │   └── types/           # ドメイン型テスト
│       └── infrastructure/
│           ├── config/          # 設定テスト
│           ├── context/         # コンテキストテスト
│           ├── exception/       # 例外テスト
│           ├── model/           # モデルテスト
│           │   └── val/         # バリデーションテスト
│           │       └── impl/    # バリデーション実装テスト
│           ├── test/            # テスト基盤テスト
│           ├── types/           # 型テスト
│           │   └── msg/         # メッセージ型テスト
│           └── utils/           # ユーティリティテスト
├── docs/                        # ドキュメント
│   └── メッセージ一覧.xlsx        # メッセージ定義
├── scripts/                     # スクリプト
│   └── release.bat             # リリーススクリプト
└── pom.xml                     # Maven設定ファイル
```

## 開発環境

- Java 21
- Spring Boot 3.5.5
- Maven 3.x
- JUnit Jupiter 5.13.4
- Mockito 5.18.0
- TestFX 4.0.17
- SLF4J 2.0.17

### ビルドツール

- Maven Compiler Plugin 3.12.1
- Maven Surefire Plugin 3.2.5（JUnit テストレポート用）
- JaCoCo Maven Plugin 0.8.11（カバレッジレポート用）
- Spring Boot Maven Plugin 3.5.5
- JavaFX Maven Plugin 0.0.8

### プロジェクト情報

- グループ ID: kmg.fund
- アーティファクト ID: kmg-fund
- バージョン: 0.1.0
- エンコーディング: UTF-8

## ビルド方法

```bash
mvn clean install
```

## テスト実行

```bash
mvn test
```

## リリース

リリースプロセスの詳細については、[リリース手順](scripts/release.bat)を参照してください。

リリースは以下の 2 つの方法で実行できます：

1. 自動リリーススクリプト（`scripts/release.bat`）を使用
2. 手動でリリースプロセスを実行

各リリースは以下の品質基準を満たす必要があります：

- テストカバレッジ 100%
- すべてのテストが成功
- セキュリティチェックをパス

## 主要機能

### ドメイン型定義

- `KmgApplicationPropertyFileTypes` - アプリケーションプロパティファイル型
- `KmgApplicationPropertyKeyTypes` - アプリケーションプロパティキー型

### インフラストラクチャ

#### 設定管理

- `KmgFundPropertiesLoader` - プロパティファイル統合ローダー
- `KmgMessageSource` - メッセージソース管理
- `SpringApplicationContextHelper` - Spring アプリケーションコンテキストヘルパー

#### 例外処理

- `KmgFundMsgException` - KMG 基盤メッセージ例外
- `KmgFundValException` - KMG 基盤バリデーション例外

#### バリデーション

- `KmgFundValDataModelImpl` - バリデーションデータモデル実装
- `KmgFundValsModelImpl` - バリデーション集合モデル実装

#### ユーティリティ

- `KmgPoiUtils` - Apache POI ユーティリティ
- `KmgYamlUtils` - YAML ユーティリティ

#### メッセージ型

- `KmgFundGenMsgTypes` - 汎用メッセージ型
- `KmgFundLogMsgTypes` - ログメッセージ型
- `KmgFundValMsgTypes` - バリデーションメッセージ型

### テスト基盤

- `AbstractKmgJunitTest` - KMG JUnit テスト基底クラス

## 依存関係

### 主要依存関係

- **kmg-core**: KMG コアライブラリ（0.2.0）
- **Spring Boot**: Spring Boot フレームワーク（3.5.5）
- **Apache POI**: Excel ファイル操作（5.4.1）
- **SnakeYAML**: YAML ファイル処理
- **JavaFX**: デスクトップアプリケーション（21.0.8）

### テスト依存関係

- **Spring Boot Test**: Spring Boot テストサポート
- **JUnit Jupiter**: ユニットテストフレームワーク
- **Mockito**: モックテストフレームワーク
- **TestFX**: JavaFX テストフレームワーク
- **Hamcrest**: マッチャーライブラリ

## 設定ファイル

### アプリケーション設定

- `application.properties` - 基本アプリケーション設定
- `application-base.properties` - ベース設定
- `kmg-fund-application.properties` - KMG 基盤設定

### メッセージ設定

- `kmg-fund-messages.properties` - メッセージ定義
- `kmg-fund-messages-log.properties` - ログメッセージ定義

### ログ設定

- `logback-kmg-fund.xml` - KMG 基盤ログ設定
- `logback-kmg-spring-base.xml` - Spring ベースログ設定

## ライセンス

本プロジェクトは LICENSE ファイルに記載の条件の下で提供されています。

## 貢献について

バグ報告や機能改善の提案は、GitHub の Issue を通じてお願いします。

### 貢献の方法

1. リポジトリをフォーク
2. 機能ブランチを作成（`git checkout -b feature/amazing-feature`）
3. 変更をコミット（`git commit -m 'Add some amazing feature'`）
4. ブランチにプッシュ（`git push origin feature/amazing-feature`）
5. プルリクエストを作成

### コーディング規約

- Java コーディング規約に従う
- 適切な Javadoc コメントを記述
- 単体テストを必ず作成
- テストカバレッジ 100% を維持

---

**kmg-fund** - Java アプリケーション開発のための基盤ライブラリ
