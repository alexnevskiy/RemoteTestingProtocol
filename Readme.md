# Remote Testing Protocol

## Format

Все коммуникации внутри протокола дистанционного тестирования осуществляются в едином формате. Формат сообщения разделён на 2 раздела (в некоторых случаях Resource Record может быть пустым), показанных ниже:

```
    +---------------------+
    |        Header       |
    +---------------------+
    |   Resource Record   |
    +---------------------+
```

Раздел заголовка присутствует всегда. Он включает в себя поле, которое указывает, присутствует ли раздел Resource Record, а также определяет, от кого отправлено данное сообщение (от клиента или сервера), режим и т.д.

Раздел после заголовка используется в зависимости от режима, указанного в header, или не используется вовсе.

## Header

Заголовок содержит следующие поля:

```
                                    1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |CS|   MODE    | RCODE  |          MBZ          |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    RRCOUNT                    |
    |                                               |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
```

- **CS** - однобитовое поле, которое указывает на то, кем отправлено сообщение: клиентом (0) или сервером (1).
- **MODE** - четырёхбитное поле, которое предоставляет режим работы с определёнными значениями:
  - **0** - регистрация клиента. Отправляется два раздела Resource Record с логином и паролем пользователя со стороны клиента. Со стороны сервера в сообщении-подтверждении Resource Record не отправляется.
  - **1** - разрыв соединения. Resource Record не отправляется.
  - **2** - результат последнего теста. Со стороны клиента Resource Record не отправляется. Со стороны сервера отправляется один Resource Record с результатами в процентах.
  - **3** - список тестов. Со стороны клиента Resource Record не отправляется. Со стороны сервера отправляется столько Resource Record, сколько тестов находится в списке.
  - **4** - выбор теста. Отправляется только со стороны клиента с одним Resource Record, в котором находится порядковый номер теста.
  - **5** - вопрос-ответ. Со стороны клиента отправляется столько Resource Record, сколько выбрано ответов на вопрос (вопрос может быть с множественным ответом). Ответ со стороны клиента подразумевает из себя порядковый номер ответа, который пришёл ему от сервера. Со стороны сервера отправляется один Resource Record с вопросом и последующие n разделов с n количеством ответов.
  - **6-15** - зарезервированы для будущего использования.
- **RCODE** - трёхбитное поле, которое содержит в себе коды ошибок со стороны сервера:
  - **0** - нет ошибок.
  - **1** - *Wrong password* - пароль не совпадает с логином, если пользователь под таким логином уже зарегистрирован на сервере.
  - **2** - *Invalid test* - тест с таким номером отсутствует.
  - **3** - *Incorrect answer* - ответ с таким номером отсутствует, если вопрос содержит в себе выбор ответа.
  - **4** - *Not implemented* - сервер не поддерживает данный режим работы.
  - **5-7** - зарезервированы для будущего использования.
- **MBZ** - восьмибитное поле *MUST BE ZERO*, которое зарезервировано для будущего использования.
- **RRCOUNT** - 32 битное поле с количеством разделов Resource Record, идущих после заголовка.

## Resource Record

Раздел Resource Record содержит следующие поля:

```
                                    1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    LENGTH                     |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                                               |
    /                     DATA                      /
    /                                               /
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
```

- **LENGTH** - 16 битный размер отправляемых данных в байтах.
- **DATA** - отправляемые данные.

## Пример работы протокола

![](images/Remote_Testing.svg)
