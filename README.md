# SelectOptionsのカウントだけクエリを発行するようなカスタマイズ

`SelectOptions`を使用してカウントを取得する場合、通常のクエリとカウントのクエリの計2回クエリが発行されます。

この挙動をカスタマイズして、カウントだけが欲しい場合にカウントのクエリだけが発行されるようにしてみます。

## 動作確認

```sh
mvn exec:java
```

デフォルトの動作と、カウントのみの動作を続けて行なっていますのでコードと実行ログを見比べてみてください。

カウントのみの動作の場合は実行ログを見ると元のSELECTクエリは発行されず、カウントのクエリだけが発行されていることが分かると思います。

## 仕組み

Daoインターフェースを実装したクラスでは大まかに言うと次のような流れで検索処理が行われます。

1. `QueryImplementors`によって`SqlFileSelectQuery`が作られる
2. `SqlFileSelectQuery`にクエリタイムアウトやフェッチサイズを設定する
3. `SqlFileSelectQuery.prepare`でSQLファイルがパースされる
4. `CommandImplementors`によって`SelectCommand`が作られる
5. `SelectCommand.execute`でクエリが発行される
6. カウントを取得するように`SelectOptions`が設定されている場合は`SqlFileSelectQuery.complete`でカウントのクエリが発行される

今回省略したいのは5の処理なので、クエリを発行しない[`SelectCommand`サブクラス](src/main/java/com/example/config/CountOnlySelectCommand.java)と、そのインスタンスを生成する[`CommandImplementors`サブクラス](src/main/java/com/example/config/CommandImplementorsImpl.java)を準備しました。

ただし、5の処理を省略しない通常の`SelectCommand`も使いたいので、[`SelectOptions`サブクラス](src/main/java/com/example/config/CountOnlySelectOptions.java)を準備して、その`SelectOptions`サブクラスが渡ってきたらクエリを発行しない`SelectCommand`サブクラスを返すようにしています。

`CommandImplementors`は`Config`から取得されるので、準備した`CommandImplementors`サブクラスを取得できるような[`Config`実装クラス](src/main/java/com/example/config/DomaConfig.java)を準備しました。

