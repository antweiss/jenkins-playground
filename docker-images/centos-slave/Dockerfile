FROM centos:7.2.1511
MAINTAINER Anton Weiss
COPY otomato.repo /etc/yum.repos.d/
RUN yum clean all && yum install --enablerepo=cr -y jdk1.8.0_60.x86_64 openssh-server wget git which rpm-build nfs-utils mysql sudo jq nodejs node-gyp gcc make \
    npm xmlstarlet python-pip docker-engine python-devel nomad-0.5.0_dev nvidia-docker \
    svn cyrus-sasl cyrus-sasl-plain cyrus-sasl-ldap
WORKDIR /opt
COPY ssh/* /home/builder/.ssh/
COPY docker_auth.json /home/builder/.docker/config.json
COPY docker_auth.json /root/.docker/config.json
COPY gsutil/* /home/builder/
RUN groupadd -g 10000 Developers \
    && pip install --upgrade pip \
    && pip install requests==2.11.1 \
    && pip install Fabric==1.12.0 \
    && pip install jenkinsapi \
    && pip install docker-py \
    && pip install jinja2 \
    && pip install gsutil \
    && pip install pandas \
    && pip install spacy \
    && pip install tensorflow \
    && pip install ujson \
    && useradd -m -d /home/builder -g 10000 -u 10532 -G wheel -G docker builder \
    && echo 1qazxsw2 | passwd builder --stdin \
    && echo 'builder ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers \
    && perl -i -ple 's/^D.*requiretty/\#Defaults requiretty/g' /etc/sudoers \
    && mkdir -p /home/builder/workspace && mkdir -p /home/builder/.ssh \
    && chown -R builder:wheel /home/builder \
    && chmod 700 /home/builder/.ssh && chmod 600 /home/builder/.ssh/* \
    && ssh-keygen -A \
    && echo 'buildsrv3.otomato.link:/opt/otomato/sdb1	/opt/otomato/CI	nfs	rw,nfsvers=3,nolock,hard,rsize=32768,wsize=32768,intr,nocto,noatime,nodiratime,noac,noacl	0	0' >> /etc/fstab \
    && mkdir -p /opt/otomato/CI
RUN echo "UseDNS no" >> /etc/ssh/sshd_config
USER builder
ENV CI_TOOLS_PATH /opt/otomato/CI/ci-tools
EXPOSE 22
CMD ["sudo", "/usr/sbin/sshd", "-d"]
