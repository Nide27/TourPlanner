FROM postgres:latest

COPY ./createDB.sql /docker-entrypoint-initdb.d/