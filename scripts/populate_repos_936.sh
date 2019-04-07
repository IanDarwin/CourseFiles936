#!/bin/sh

# ONLY USE ONCE to init all the git repos

cd /c

for repo in TicketManorJava
do
	git clone https://LearningTree@github.com/LearningTree/${repo}
done

# We omit CourseFiles936 as you've obviously already got a copy
for repo in makehandsons CreateProjects cdi-ee-examples ClubList javasrc darwinsys-api jsfdemo jpademo
do
	git clone https://IanDarwin@github.com/IanDarwin/${repo}
done
