# 課題9
Spring Data JPA(Java Persistence API) 入門
MySQLに接続

http://localhost:8080/books

## Memo
### MySQL
文字コード設定の確認
```
show variables like "chara%";
```

環境変数の設定
```
set ${var}=${value}
```

ユーザー作成
```
CREATE USER 
```

権限設定の例
```
GRANT SELECT,INSERT,UPDATE,DELETE ON ${database}.${table} TO ${user}@localhost
```
