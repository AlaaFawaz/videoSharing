FROM tomcat:jre8

COPY /target/videoSharing-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

RUN rm -r /usr/local/tomcat/webapps/ROOT/