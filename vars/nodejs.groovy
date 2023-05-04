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
                 env.ARGS="-dsonar.sources=."
                 common.sonarChecks(COMPONENT)
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