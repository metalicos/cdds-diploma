properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
    pollSCM('* * * * *')
  }
  environment {
    IMAGE = ""
    VERSION = ""
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    skipDefaultCheckout(true)
    skipStagesAfterUnstable()
    timestamps()
  }
  stages {
    stage('Prepare') {
      steps {
        checkout scm
        script {
          IMAGE = readMavenPom().getArtifactId().toLowerCase()
          VERSION = readMavenPom().getVersion().toLowerCase()
        }
        echo IMAGE
        echo VERSION
      }
    }
    stage('Build') {
      steps {
        echo "=============================== STARTING BUILD ====================================="
        withMaven(maven: 'maven-latest') {
          bat "mvn clean install"
        }
        echo "=============================== BUILD SUCCESSFUL ==================================="
      }
    }
    stage('Create Docker Image') {
      steps {
        echo "========================== STARTING DOCKER IMAGE CREATION =========================="
        bat "docker build -t ${IMAGE}:${VERSION} -t ${IMAGE}:latest ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        script {
          try {
            bat "docker stop ${IMAGE}-${VERSION}"
            bat "docker rm ${IMAGE}-${VERSION}"
          } catch (Exception e) {
            echo "${IMAGE}-${VERSION} container is not running."
          }
          bat "docker run -d -t -i -e CDDS_DB_PASSWORD=\"${CDDS_DB_PASSWORD}\" -e CDDS_DB_USERNAME=\"${CDDS_DB_USERNAME}\" -e CDDS_DB_URL=\"${CDDS_DB_URL}\" -e JWT_SECRET=\"${JWT_SECRET}\" -e CDDS_MESSAGE_ENCRYPTIN_SECRET=\"${CDDS_MESSAGE_ENCRYPTIN_SECRET}\" -e CDDS_MQTT_USERNAME=\"${CDDS_MQTT_USERNAME}\" -e CDDS_MQTT_PASSWORD=\"${CDDS_MQTT_PASSWORD}\" -e CDDS_MQTT_SERVER_URL=\"${CDDS_MQTT_SERVER_URL}\" -p 5555:5555 --name=${IMAGE}-${VERSION} ${IMAGE}"
          echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
      }
    }
  }
}