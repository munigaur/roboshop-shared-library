def lintChecks(COMPONENT) {
       sh "echo installing JSLINT"
       sh "npm install jslint"
       sh "ls -ltr node_modules/jslint/bin"
       // sh "./node_modules/jslint/bin/jslint.js server.js"
       sh "echo LINT CHECKS COMPLETED !!!"
     }


def call(COMPONENT){
pipeline {
    agent any
    environment {
      SONAR=credentials('SONAR')
      SONAR_URL="44.215.71.150"
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
                 env.ARGS="-dsonar.sources=."
                 common.sonarChecks(COMPONENT)
                    }
             }
          }
        stage('test cases') {
             parallel {
                stage ('unit testing') {
                   steps {
        	             sh "echo unit testing ..."
      		               }
          }
                stage ('integration testing') {
                   steps {
        	             sh "echo integration testing ..."
      		               }
          }
                stage ('functional testing') {
                   steps {
        	             sh "echo functional testing ..."
      		               }
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