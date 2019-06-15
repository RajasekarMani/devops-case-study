#!/bin/groovy
package ci

pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh '''
                    cd DevOpsCaseStudy
                    mvn clean install -B -U -q -Dmaven.test.failure.ignore=true                  
                '''
            }
            post {
                success {
                    junit 'DevOpsCaseStudy/target/surefire-reports/**/*.xml'
                }
            }
        }

        stage ('Temp') {
            steps {
                echo 'This is a minimal pipeline.'
            }
        }
    }
}
