all:	README.html homesetup.html databases.html maint.html passwords.html troubleshooting.html

README.html:			README.adoc
	asciidoctor $?
homesetup.html:			homesetup.adoc
	asciidoctor $?
databases.html:			databases.adoc
	asciidoctor $?
maint.html:				maint.adoc
	asciidoctor $?
passwords.html:			passwords.adoc
	asciidoctor $?
troubleshooting.html:	troubleshooting.adoc
	asciidoctor $?

