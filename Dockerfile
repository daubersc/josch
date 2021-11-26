FROM ubuntu:20.04

ENV DEBIAN_FRONTEND noninteractive
ENV LANG="C.UTF-8"
ENV LC_ALL="C.UTF-8"

RUN apt update && apt -y dist-upgrade

RUN apt-get install -y wget
RUN apt-get install -y curl
RUN apt-get install -y git 

# Install Java14
RUN apt-get install -y openjdk-14-jre-headless

# Install maven
# get maven 3.3.9
RUN wget --no-verbose -O /tmp/apache-maven-3.6.3.tar.gz http://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz

RUN tar xzf /tmp/apache-maven-3.6.3.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.6.3 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-3.6.3.tar.gz

# Install Python3.8
RUN apt install software-properties-common -y
RUN add-apt-repository ppa:deadsnakes/ppa
RUN apt install python3.8 -y

# Install pip
RUN apt install -y python3-pip

# Install pipenv
RUN pip3 install pipenv

# Install NodeJS
ENV NODE_VERSION=14.15.1
RUN curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.34.0/install.sh | bash
ENV NVM_DIR=/root/.nvm
RUN . "$NVM_DIR/nvm.sh" && nvm install ${NODE_VERSION}
RUN . "$NVM_DIR/nvm.sh" && nvm use v${NODE_VERSION}
RUN . "$NVM_DIR/nvm.sh" && nvm alias default v${NODE_VERSION}
ENV PATH="/root/.nvm/versions/node/v${NODE_VERSION}/bin/:${PATH}"

# Install yarn
RUN npm install -g yarn

RUN apt-get clean

# Make Josch available
RUN useradd -ms /bin/bash josch
COPY . /home/josch/app

# Install jsonsubschema
WORKDIR /home/josch/app/tools/JsonSubSchema
RUN pipenv install

# Install is-json-schema-subset
WORKDIR /home/josch/app/tools/IsJsonSchemaSubset
RUN yarn install

# Build Josch
WORKDIR /home/josch/app/josch
RUN mvn clean install

RUN cp josch.presentation/josch.presentation.gui/josch.presentation.gui.controller/target/josch-1.0-jar-with-dependencies.jar ../Josch.jar
WORKDIR /home/josch/app
RUN ls
