from library/jenkins:2.46.3-alpine
# Install plugins from list
COPY scripts/otomato-plugins /usr/local/bin/otomato-plugins
RUN OP=`cat /usr/local/bin/otomato-plugins` && /usr/local/bin/install-plugins.sh $OP
COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/
# bake the builder user ssh key
RUN mkdir /usr/share/jenkins/ref/.ssh
COPY scripts/id_rsa /usr/share/jenkins/ref/.ssh/
# need to become root to change ownership of the key
USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/.ssh/
USER jenkins
RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
