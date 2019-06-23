#!/bin/groovy
package ci

timestamps{
  ansiColor('xterm'){
    try{
      node('slave-machine-label'){
        def app
        cleanWs()

        stage('Checkout') {
          git branch: "${BRANCH}", credentialsId: 'github-devops-case-study', url: "https://github.com/RajasekarMani/devops-case-study.git"
        }

        stage('Build & Run Junit'){
          dir('DevOpsCaseStudy'){
            sh 'chmod +x src/main/resources/chromedriver'
            sh '''
            mvn clean install -B -U -q -Dmaven.test.failure.ignore=true -DskipTests
            '''
            junit 'target/surefire-reports/**/*.xml'
          }
        }

        stage('Sonar'){
          dir('DevOpsCaseStudy'){
            sh 'mvn sonar:sonar -Dsonar.host.url=http://35.200.203.29:9000'
          }
        }

        stage('Build & Push Image'){
          dir('ci_helper'){
            sh 'cp /tmp/workspace/devops-case-study/DevOpsCaseStudy/target/DevOpsCaseStudy.war .'
            sh "docker build -t rmani1/app:${env.BUILD_NUMBER} ."
            withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'docker_password', usernameVariable: 'docker_username')]) {
              sh "docker login -u=${docker_username} -p=${docker_password}"
              sh 'docker push rmani1/app'
            }
          }
        }

        stage('DEV - Deploy'){
          sh 'docker pull rmani1/app'
          sh "docker run -itd --name app --rm -p 8080:8080 rmani1/app:${env.BUILD_NUMBER}"          
          sh 'docker logout'
        }

        stage('Selenium Tests'){
          dir('DevOpsCaseStudy'){
            sh 'mvn failsafe:integration-test -Dskip.surefire.tests -Dapp.url=http://localhost:8080/DevOpsCaseStudy/'
            junit 'target/failsafe-reports/**/*.xml'
            sh 'docker stop app'
          }
        }

        stage('PROD - deploy'){
          withCredentials([file(credentialsId: "sa-devops-case-study-key", variable: 'GCLOUDCREDENTIAL')]){
            sh """
            gcloud auth activate-service-account --key-file=${GCLOUDCREDENTIAL}
            gcloud config set compute/zone asia-south1-c
            gcloud config set compute/region asia-south1
            gcloud config set project local-mediator-240712
            gcloud container clusters get-credentials devops-case-study
            kubectl apply -f ci_helper/kubernetes/deploy/
            rm -rf ~/.config
            rm -rf ~/.kube
            """
          }
        }
      }
    }
    catch(Exception err){
      error err.message
    }
    finally {
    }
  }
}
