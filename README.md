# A Practical Guide of Spring Boot 
Spring BootフレームワークでJavaプログラミングによるWebアプリケーション開発を実践するガイドである。
- Spring Framework
  - Spring MVC
    - Spring Boot

アプリ開発で、あるアプリケーションフレームワークを利用する際、そのフレームワークそのままに従い利用することが肝要である。
そのフレームワークの構成設計や実現方法を知ることは二義的なことである。
それらの知見で、フレームワークの使い方の過誤や理解不足で生じる問題を解決すると、以降、そのアプリ開発に携わる者にそれら二義的知見を要求することになる。
本ガイドに続く、開発実習コースではSpring Bootフレームワークの基本的構成で給与システムを実現する。

## Description

実習課題「単一オブジェクト操作Webアプリケーション」

<details open>
<summary>利用技術</summary>

- JPA(Java Persistence API)
  - O/Rマッピング
- [Flyway](https://flywaydb.org/)
  - リポジトリはMySQLサーバで実装する
- [Tymeleaf](https://www.thymeleaf.org/index.html)
  - テンプレート・エンジン
</details>
  
<details open>
<summary>Webアプリケーション構造</summary>

- Model: クラスEntity(属性 int key, Date value)
- View: ホームページ, Entity一覧ページ, Entity登録ページ
- Controller: クラスEntityController
</details>

<details>
<summary>構成(Configuration)</summary> 

- 共通アプリケーション設定値(Common Application Properties)の外部設定ファイル
  - src/test/resources/application.properties
      - "src/main/resources/"はMavenのクラスパスルートに含まれている
  - [共通アプリケーション設定値一覧](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- ページ・テンプレート フォルダ
  - src/main/resources/templates/
  - jsp, htmlファイル
- データベースへの移行データ フォルダ
  - src/main/resources/db.migration/
  - V?.?.?__xxx.sql
</details>


## Requirement
実行環境
- Tomcat
  - Webアプリケーションサービス
- MySQL Connector/J
  - オブジェクトリポジトリ
- Firefox
  - Webアプリケーションクライアント

ビルド環境
- Tymleaf
- Lombok

開発環境(Eclipse)

