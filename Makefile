all:	README.html databases.html maint.html passwords.html troubleshooting.html

README.html:			README.adoc
	asciidoctor $?
databases.html:			databases.adoc
	asciidoctor $?
maint.html:				maint.adoc
	asciidoctor $?
passwords.html:			passwords.adoc
	asciidoctor $?
troubleshooting.html:	troubleshooting.adoc
	asciidoctor $?

