cd \makehandsons
git pull
call mvn install

cd \TicketManorJava
 git pull
 cd datamodel
 call mvn install

cd \CourseFiles
 git pull

cd SourceCode

rm -r ex?? donow??

call \makehandsons\scripts\makehandsons.cmd .

cd \jpademo
 git pull

cd \jsfdemo
 git pull

cd \javasrc
 git pull

cd \clublist
 git pull
