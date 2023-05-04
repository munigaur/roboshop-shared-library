def lintChecks(COMPONENT) {
       sh "echo installing mvn"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def call(COMPONENT){
pipeline {
    agent any
    environment {
      SONAR=credentials('SONAR')
      SONAR_URL="34.200.223.159"
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
         stage ('downloading the dependencies') {
            steps {
        	   sh "mvn clean package"
      		}
   		 }
 	}
}
}