# A Practical Guide of Spring Boot 
Spring BootフレームワークでWebアプリケーションを開発するための実践ガイドである。
- Spring Framework
-- Spring MVC
--- Spring Boot

アプリケーション開発で、あるアプリケーションフレームワークを利用する際、そのフレームワークそのままに従い利用することが肝要である。
そのフレームワークの構成設計や実現方法を知ることは二義的なことであり、それら知見で、フレームワークの使い方の過誤や理解不足で生じる問題を解決してはならいない。

## Description

実習課題「単一オブジェクト操作Webアプリケーション」

<details open>
<summary>利用技術</summary>

- JPA(Java Persistence API)
  - O/Rマッピング
  - リポジトリサーバはMySQLサーバで実装する
- Tymeleaf
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
  - 
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
- ソースをEclipseで扱う際、pom.xmlが「ライフサイクル・マッピングのエラー」になる場合の対処方法
  - [設定(Preferences)]→[Maven]→[警告・エラー]欄→[ライフサイクル構成でカバーされていないプラグインの実行]項目を値を[無視]にする。
- astah* pluginは、OSGiバンドルに準拠したjarでなければならない。
  - astah-plugin-SDK-1.5のコマンドastah-buildは、target配下に、OSGiバンドルに準拠したjarを生成する。

## Install
- [target/povastah-X.X-SNAPSHOT.jar](./target/povastah-1.1-SNAPSHOT.jar) をastah*ツールのインストールディレクトリ配下のpluginsディレクトリに配置する。
- [includes](./includes)配下のインクルードファイル"povastah.inc", "umlTexture.inc", "povastahParts.inc"をPOVRayのインクルードファイルディレクトリに配置する。
	- POVRay v3.7 デフォルト環境ディレクトリ(インストール時に指定可能)
		- 配下に、include(インクルードファイルディレクトリ), ini(デフォルオ設定ファイルディレクトリ)等がある。
		- Windows版: My Documents\POV-Ray\v3.7
		- Linux版:  /usr/local/share/povray-3.7
	- ini/povray.iniを編集して、インクルードファイルディレクトリを追加する。
		- Windows版 ```Top Menu[Tools]->[Edit master POVRAY.INI]```で開いて編集する。
			```Shell
			Library_Path="Y:\povray\include"
			```

プラグインツール
- [ツール]→[画像出力]→[POVRayスクリプト]で、出力ディレクトリを指定する。

## Usage

1. astah*ツールを起動して、3DCG視覚化するダイアグラムを含むプロジェクトを開く。
2. astah*ツールで、POVRayスクリプトファイル(.pov)を作成する。
	- Top Menu[ツール]->[画像出力]->[POVRayスクリプト]を選択する。
	- [POVRay出力]ファイルダイアログで出力ディレクトリを選択する。
    	- POVRayスクリプトファイルはastah*プロジェクト構造と同型のファイルツリー構造に展開される。
3. POVRayツールで、POVRayスクリプトファイル(.pov)を開いて、レンダリングする。
4. POVRayスクリプトファイルを編集する。

	<details>
	<summary>カメラを操作する</summary>
	
	- カメラの配置を変更する
		```POV-Ray SDL
		#declare EYE = <x,y,z>;
		```
		- ｘは水平軸で右手が正である。yは垂直軸で上手が正である。ｚはxy平面の直交軸で奥向きが正である。
		- ステージ平面は、<0,0,32>に配置している。したがって、z>32には配置しない。
	 	
	- カメラの焦点を変更する
		```POV-Ray SDL
		#declare FOCUS = <x,y,z>;
		```
	- カメラをズームする
		```POV-Ray SDL
		camera { location EYE direction 倍率*z look_at FOCUS }
		```		
   </details>		
   <details>
	<summary>リンク影のマテリアルを切り替える</summary>

	```POV-Ray SDL
	#declare ShadowType=1;
	```
   </details>
   <details>
	<summary>ノードオブジェクトの位置を動かす</summary>

	```POV-Ray SDL
	#local Action0_1 = <x, y, z>;
	```
   </details>


※UTF-8で日本語文字を含めて出力する。POVRayエディタはUTF-8に対応していないため、出力されたPOVRayスクリプトファイルで日本語文字を編集する際は、別途、UTF-8を扱えるテキストエディタを推奨する。