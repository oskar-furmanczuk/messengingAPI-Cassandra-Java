1) URUCHOMIENIE

By uruchomić aplikację należy spełnić następujące wymagania:
- zainstalowany i skonfigurowany Python 2.7
- zainstalowana, skonfigurowana oraz uruchomiona Apache Cassandra 3.11.10
- zainstalowana i skonfigurowana Java 8

W pierwszej kolejności należy uruchomić Cassandrę i przez cqlsh uruchomić poniższy skrypt:
>>>

create keyspace messenging with replication={'class':'SimpleStrategy', 'replication_factor':1};
use messenging;
CREATE TABLE emails_by_magic_number(
   id uuid,
   email text,
   title text,
   content text,
   number int,
   PRIMARY KEY((number), id)
);
CREATE MATERIALIZED VIEW email_by_email_value AS
    SELECT * FROM email_by_magic_number
        WHERE email IS NOT NULL AND id IS NOT NULL AND number IS NOT NULL
    PRIMARY KEY ((email), number, id);

<<<

Następnie należy zbudować projekt MessengingAPI (środowisko Maven) oraz uruchomić klasę MessengingApiApplication.java

lub

Znajdując się w folderze z plikiem pom.xml otworzyć dowolny command-line interpreter (np. CMD, Git Bash) i wpisać poniższą komenendę:
java -jar target/*.jar



1) SPOSÓB UŻYCIA

Rest API przewiduję trzy endpointy:

- POST /api/message (służy do postowania emaili)
przykład:
curl -X POST localhost:8080/api/message -H "Content-Type: application/json" -d '{"email":"jan.kowalski@example.com","title":"Interview","content":"simple text","magic_number":101}'

- POST /api/send (służy do wysyłania emaili - usuwanie z wyświetleniem usuniętych emaili przez podanie wartości "magic_number")
przykład:
curl -X POST localhost:8080/api/send -H "Content-Type: application/json" -d '{"magic_number":101}'

- GET /api/messages/{emailValue} (służy do wyświetlania maili dla podanych wartości "emailValue" t.j. adres email)
przykład:
curl localhost:8080/api/messages/john.smith@example.com








