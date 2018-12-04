#!/bin/sh

# hookup postgresql into wildfly

# version numbers jumped from 9.x to 42.x to avoid implying req't to be in synch w/ PostgreSQL version
V=42.1.4

cd /tmp
ftp https://jdbc.postgresql.org/download/postgresql-${V}.jar

cd /var/wildfly-1?.*/bin
# If you don't have "doas" yet, use "sudo" in its place
doas -u _wildfly ./jboss-cli.sh <<!
connect
module add --name=org.postgres --resources=/tmp/postgresql-${V}.jar --dependencies=javax.api,javax.transaction.api
# It's OK if this command fails with "duplicate resource"
/subsystem=datasources/jdbc-driver=postgres:add(driver-name=”postgres”,driver-module-name=”org.postgres”,driver-class-name=org.postgresql.Driver)
!
