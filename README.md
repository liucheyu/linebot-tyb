# linebot-tyb

2021-09-28\
完成一般訊息和post back基本處理，\
信用卡和電子票證增加需要在提示要那一種

post back回來的data設定為json，需要解成model或dto，\
兩種作法:
1. 用instansof的方式設置多個if判斷可解成哪個model
2. 建兩個class，
   - 1個定義多個處理不同子型態model，並有標注
   - 將依標注將上一個class的方法全取出，使用內部型態可以解析得方法來執行

傳入String，自動判定用一個方法解，傳回message, 然後做出回覆



