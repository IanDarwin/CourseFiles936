#!/bin/sh

# hookup postgresql into wildfly
V=9.4.1207

cd /tmp
ftp https://jdbc.postgresql.org/download/postgresql-#{V}.jar
exit
cd /wildfly*/wildfly/bin
./jboss-cli.sh <<!
connect
module add –name=org.postgres –resources=/tmp/postgresql-#{V}.jar –dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name=”postgres”,driver-module-name=”org.postgres”,driver-class-name=org.postgresql.Driver)
!
