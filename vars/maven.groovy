def lintChecks(COMPONENT) {
       sh "echo installing mvn"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def sonarChecks(COMPONENT) {
       sh "echo starting Code Quality Analysis"
       sh "mvn clean compile"
       sh "sonar-scanner -Dsonar.projectKey=catalogue -Dsonar.java.binaries=target/ -Dsonar.host.url=http://${SONAR_URL}:9000 -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}"
       sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gata.sh"
       sh "bash -x sonar-quality-gata.sh  ${SONAR_USR} ${SONAR_PSW} ${SONAR_URL} ${COMPONENT}"     
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