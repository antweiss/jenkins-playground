#! /bin/bash
sudo mkdir -p /etc/systemd/system/docker.service.d
sudo tee -a /etc/systemd/system/docker.service.d/startup_options.conf  > /dev/null <<EOT
# /etc/systemd/system/docker.service.d/override.conf
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H fd:// -H tcp://0.0.0.0:2376
EOT

sudo systemctl daemon-reload
sudo systemctl restart docker.service
