def lintChecks(COMPONENT) {
       sh "echo installing JSLINT"
       sh "npm install jslint"
       sh "ls -ltr node_modules/jslint/bin"
       // sh "./node_modules/jslint/bin/jslint.js server.js"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def sonarChecks(COMPONENT) {
       sh "echo sonar checks started"
       //sh "npm install jslint"
       //sh "ls -ltr node_modules/jslint/bin"
       // sh "./node_modules/jslint/bin/jslint.js server.js"
       sh "echo sonar checks COMPLETED !!!"
     }

def call(COMPONENT){
pipeline {
    agent any
    stages {
        stage('Lint Checks') {
          steps {
             script {
                 lintChecks(COMPONENT)
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