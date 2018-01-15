REM hookup postgresql into wildfly

cd \wildfly*
cd bin
jboss-cli.bat
connect
module add -name=org.postgres -resources=F:postgresql-9.4.1207.jar -dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
\q
