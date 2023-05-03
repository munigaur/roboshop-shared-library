def lintChecks(COMPONENT) {
       sh "echo installing JSLINT"
       sh "npm install jslint"
       sh "ls -ltr node_modules/jslint/bin"
       // sh "./node_modules/jslint/bin/jslint.js server.js"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def sonarChecks(COMPONENT) {
       sh "echo starting Code Quality Analysis"
       sh "sonar-scanner -Dsonar.projectKey=catalogue -Dsonar.sources=. -Dsonar.host.url=http://${SONAR_URL}:9000 -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}"
     }

def call(COMPONENT){
pipeline {
    agent any
    environment {
      SONAR=credentials('SONAR')
      SONAR_URL="3.235.100.37"
    }
    stages {
        stage('Lint Checks') {
          steps {
             script {
                 lintChecks(COMPONENT)
                 }
             }
          }
        stage('sonar Checks') {
          steps {
             script {
                 sonarChecks(COMPONENT)
                 }
             }
          }
         stage ('downloading the dependencies') {
            steps {
        	   sh "npm install"
      		}
   		 }
 	}
}
}