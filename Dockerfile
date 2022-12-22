FROM centos:7

RUN yum install -y java-1.8.0-openjdk-devel wget git maven

# Create users and groups
RUN groupadd tomcat
RUN mkdir /opt/tomcat
RUN useradd -s /bin/nologin -g tomcat -d /opt/tomcat tomcat

# Download and install tomcat
RUN wget https://apache.mirrors.nublue.co.uk/tomcat/tomcat-8/v8.5.54/bin/apache-tomcat-8.5.54.tar.gz
RUN tar -zxvf apache-tomcat-8.5.54.tar.gz -C /opt/tomcat --strip-components=1
RUN chgrp -R tomcat /opt/tomcat/conf
RUN chmod g+rwx /opt/tomcat/conf
RUN chmod g+r /opt/tomcat/conf/*
RUN chown -R tomcat /opt/tomcat/logs/ /opt/tomcat/temp/ /opt/tomcat/webapps/ /opt/tomcat/work/
RUN chgrp -R tomcat /opt/tomcat/bin
RUN chgrp -R tomcat /opt/tomcat/lib
RUN chmod g+rwx /opt/tomcat/bin
RUN chmod g+r /opt/tomcat/bin/*

RUN rm -rf /opt/tomcat/webapps/*
COPY . /tmp
RUN cd /tmp/gcp-ci-cd && mvn clean install
RUN cp /tmp/gcp-ci-cd/target/ci-cd-gcp-training-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ROOT.war
RUN chmod 777 /opt/tomcat/webapps/ROOT.war

VOLUME /opt/tomcat/webapps
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]