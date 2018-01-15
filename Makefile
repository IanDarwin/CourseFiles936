all:	README.html databases.html maint.html troubleshooting.html

README.html:			README.adoc
	asciidoctor $?
databases.html:			databases.adoc
	asciidoctor $?
maint.html:				maint.adoc
	asciidoctor $?
troubleshooting.html:	troubleshooting.adoc
	asciidoctor $?

