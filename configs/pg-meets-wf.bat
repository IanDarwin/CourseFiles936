REM hookup postgresql into wildfly

REM version numbers jumped from 9.x to 42.x to avoid implying req't to be in synch w/ PostgreSQL version

cd \wildfly*
cd bin
jboss-cli.bat
connect
module add --name=org.postgres --resources=/Users/student/Download/postgresql-9.4.1207.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
\q
