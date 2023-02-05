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
                sh "mvn -B --file pom.xml -Dmaven.test.skip=true clean verify sonar:sonar -Dsonar.login=$SONAR_SECRET"
            }
            timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: false
              }
        }
    }

    stage('Maven Test') {
        steps {
            sh "mvn -B --file pom.xml test"
        }
    }

    stage('Build image') {
        steps{
            script{
                dockerImage = docker.build("gaborka98/food_app:latest")
            }
        }
    }
    stage('Push image') {
        steps {
            script{
                withDockerRegistry([ credentialsId: "9f06dc5f-9e91-4623-9567-4d7cd5666417", url: "" ]) {
                    dockerImage.push()
                }
            }
        }
    }

    stage('Deploy') {
        steps {
            script {
                sh "docker container stop food-app"
                sh "docker container rm food-app"
                sh "docker run -d --restart unless-stopped -p 8094:8080 --name food-app gaborka98/food_app:latest"
            }
        }
    }
}
  tools {
    maven 'M3'
  }
  post {
    success {
      archiveArtifacts 'target/*.jar'
      junit 'target/surefire-reports/TEST*.xml'
    }
    failure {
        mail bcc: '', body: "<b>Error in build</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br>Stage: ${STAGE_NAME} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'jenkins@sativus.space', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "gaborka812@gmail.com";
    }

  }
}