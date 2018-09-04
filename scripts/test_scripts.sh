#!/bin/bash

curl -H "Content-Type: application/json" -X POST -d '{"title":"사피엔스", "author":"유발하라리"}' http://localhost:8080/api/books
curl -H "Content-Type: application/json" -X PUT -d '{"title":"사피엔스2", "author":"유발하라리"}' http://localhost:8080/api/books/1
curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/books/1

curl -H "Content-Type: application/json" -X POST -d '{"title":"사피엔스", "author":"유발하라리"}' http://localhost:8080/api/books
curl -X GET http://localhost:8080/api/books/1?format=xml
curl -X GET http://localhost:8080/api/books/1?format=json
