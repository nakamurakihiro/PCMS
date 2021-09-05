# PCMS

製造業における工数の可視化、管理するシステム。  

![PCMS-work_report](https://user-images.githubusercontent.com/75915650/127656566-bb9d6e8d-9009-4c0b-b00b-f02eee142d1e.gif)

## 概要
概要は、以下の2点です。

1.工数管理者の負担を軽減  
2.具体的な工数の可視化  

工数管理システムを英語訳で、  
Product Cost Management System  
それぞれの頭文字を取って、"PCMS"　というシステムを開発しました。  

## 制作背景　　

私は高校卒業後から製造業に従事しており、日々の業務の中で工数短縮を徹底してきました。  
しかし、工数を管理する側の業務を見てきて以下のような問題点があることに気づきました。  

・作業内容を日報用紙に記入し、管理者がPCで1つ1つ手入力している。  
・管理者側での工数管理に時間がかかっている。   

という観点から、"もっと効率よく工数を管理し、管理者側の負担を減らしたい" といった思いで、このシステムを開発しました。  

## 使用技術
・jdk-16.0.2  
・Servlet  
・JSP  
・HTML,CSS  
・MySQL 8.0  
・Tomcat 9.0  

Javaの仕組みや理解を深めるために、フレームワークは使わずに実装しました。  

## 使用画面イメージ  
![README](https://user-images.githubusercontent.com/75915650/128176053-f741ac24-ce40-4851-aea2-24371506bbe4.png)


## 機能一覧
〇基本機能  
・ログイン,ログアウト機能  
・社員・管理者登録機能  
・社員・管理者パスワード変更機能  
・社員・管理者ゲストログイン機能  

〇社員機能   
・工数記録機能  
・マイページ編集機能  
・工数記録一覧表示機能  

〇管理者機能  
・登録社員、工数記録一覧表示機能  
・工数記録編集、削除機能  
・各社員毎の工数記録表示機能  
・各部署毎の工数合計時間表示機能  
・各機械毎の工数合計時間表示機能  

## 工夫したこと  
### フレームワークを使わずに開発
Java自体の言語理解を深めるためにフレームワークを使わずに開発しました。  
フレームワークを使えば簡単に実装できた部分もあったかもしれませんが、私自身まずはしっかりと理解を深めていきたいと思ったので、言語だけで実装するということにこだわって開発しました。       

### 工数合計時間の自動計算表示
管理者画面で、社員が記録した工数時間を部署や機械毎に自動計算して工数合計時間を表示し、一目で分かるようにしました。  
![PCMS-machine_list](https://user-images.githubusercontent.com/75915650/127854747-b5542a5a-ef79-4385-b4ee-c96ffa983942.gif)


## DB設計
### ER図
![ER図-Page-1](https://user-images.githubusercontent.com/75915650/127867621-5c92f6fa-9dc3-4e0c-bd21-0eb07e206ead.png)


### 各テーブルについて
![テーブル情報](https://user-images.githubusercontent.com/75915650/131236233-67969f7e-b598-4729-8748-76a0fbede73b.png)


## 今後追加したい機能  
・社員、工数記録の検索機能  
・画像投稿機能  
・工数記録を選択して、選択した工数の合計・平均時間の計算、表示　　   
・工数記録のグラフ化  
・管理者権限を取得する際のセキュリティ対策 　など　　

## 参考
https://qiita.com/orihikarumakan/items/f4512a4cd22ab810887f
