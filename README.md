# TicTacToe
Приложение-клиент на основе HTTP-запросов играет с сервером в крестики-нолики. 
Отправляет запрос и принимает ответы в формате JSON. 

Поля: 

"youPlay": 1/2 /*определяет чей ход*/
1 - client (крестики)
2 - server (нолики)

"field": [[0,0,0], [0,0,0], [0,0,0]]  /*поле боя*/ 
0 - пустоое значение
1 - крестик
2 - нолик

"winner": 0/1/2 /*статус победителя*/
0 - никто не победил
1 - победили крестики
2 - победили нолики

В планах реализовать: 
1)Логику победителя: сейчас приложение ходит рандомно в свободные клетки
2)Проверку получаемых от сервера данных (сейчас если данные ложные, приложение не заметит подмены)

