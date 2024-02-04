#!/usr/bin/env groovy

def call(String imageName) {
    echo "build the docker image..."
    sh "docker build -t $imageName ."
    withCredentials([usernamePassword(credentialsId: 'docker-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
}