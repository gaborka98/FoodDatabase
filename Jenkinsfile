pipeline {
  agent any
  stages {
    stage('Checkout Scm') {
      steps {
        git 'https://github.com/gaborka98/FoodDatabase'
      }
    }

    stage('Maven Build') {
      steps {
        sh 'mvn -Dmaven.test.skip=true clean package'
      }
    }

    stage('SonarQube scan') {
        steps {
            withSonarQubeEnv('sonarQube'){
                sh "mvn -B --file pom.xml -Dmaven.test.skip=true clean verify sonar:sonar"
            }
            timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
        }
    }

    stage('Maven Test') {
        steps {
            sh "mvn -B --file pom.xml test"
        }
    }
  }
  tools {
    maven 'M3'
    jdk 'JDK17'
  }
  post {
    success {
      archiveArtifacts 'target/*.jar'
    }
    failure {
        mail bcc: '', body: "<b>Error in build</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br>Stage: ${STAGE_NAME} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'jenkins@sativus.space', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "gaborka812@gmail.com";
    }

  }
}