# Client for ProjectThree

Используйте класс RestTemplate, чтобы отправить 1000 запросов со
случайными температурами и "дождями" на адрес:

POST-запрос на /measurements/add

Не забудьте перед этим зарегистрировать новый сенсор (тоже с
помощью запроса).
После этого, используйте RestTemplate, чтобы получить эти 1000
измерений с сервера, отправив GET запрос на адрес:

/measurements


Постройте график температур, получив 1000 температур с сервера.
Для построения графика можно использовать библиотеку xchart