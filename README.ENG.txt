1) LAUNCHING

To run the application, the following requirements must be met:
- installed and configured Python 2.7
- Apache Cassandra 3.11.10 installed, configured and running
- Java 8 installed and configured

First, start Cassandra and run the following script via cqlsh:

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

Then build the MessengingAPI project (Maven environment) and run the MessengingApiApplication.java class

or

In the folder with the pom.xml file, open any command-line interpreter (e.g. CMD, Git Bash) and type the following command:
java -jar target/*.jar


1) HOW TO USE

Rest API provides three endpoints:
- POST /api/message (used for posting emails)
example:
curl -X POST localhost:8080/api/message -H "Content-Type: application/json" -d '{"email": "jan.kowalski@example.com", "title": "Interview", "content": "simple text", "magic_number":101}'

- POST /api/send (used to send emails - deleting with displaying deleted emails by specifying the value 'magic_number')
example:
curl -X POST localhost:8080/api/send -H "Content-Type: application/json" -d '{"magic_number":101}'

- GET /api/messages/{emailValue} (used to display emails for the given "emailValue" value i.e. email address)
example:
curl localhost:8080/api/messages/john.smith@example.com
