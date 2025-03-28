Javaで書いた『DIの原理・原則とパターン』サンプルプログラム
====

書籍『DIの原理・原則とパターン』ISBN978-4-8399-8306-2 のサンプルプログラム[https://github.com/DependencyInjection-2nd-edition/codesamples](https://github.com/DependencyInjection-2nd-edition/codesamples)はC#で書かれているが、理解のためにJavaで書き直してみる。

# 第1章 依存注入(Dependency Injection)の役割

```shell
cd chap01
mvn exec:java
```

# 第2章 密結合したコードで構築されたアプリケーション

```shell
cd chap02
mvn jetty:run
```

※ 停止は```Ctrl-C```


|ID | パスワード | ロール|
----|------------|--------
|user|password|なし|
|premier|password|preferredCustomer|

# 第3章 疎結合なコードへの変換

```shell
cd chap03
mvn jetty:run
```

※ 停止は```Ctrl-C```
