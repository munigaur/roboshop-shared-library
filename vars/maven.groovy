def lintChecks(COMPONENT) {
       sh "echo installing mvn"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def call(COMPONENT){
pipeline {
    agent any
    environment {
      SONAR=credentials('SONAR')
      SONAR_URL="344.215.71.150"
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
                 sh "mvn clean package"
                 env.ARGS="-Dsonar.java.binaries=target/"
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
        	   sh "mvn clean package"
      		}
   		 }
 	}
}
}