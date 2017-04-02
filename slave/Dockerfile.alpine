from openjdk:8-jdk-alpine
RUN apk add -U docker openssh git curl
RUN mkdir /var/run/sshd && chmod 0755 /var/run/sshd
RUN adduser -G docker -s /bin/sh -D jenkins \
    && echo 'jenkins:jenkins' | chpasswd \
    && ssh-keygen -A
CMD /usr/sbin/sshd -D
