#!/usr/bin/env groovy

def call() {
    echo "build the docker image..."
    sh "docker build -t ismailsdockers/java-maven-app:jma-2.0.1 ."
    withCredentials([usernamePassword(credentialsId: 'docker-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ismailsdockers/java-maven-app:jma-2.0.1"
    }
}