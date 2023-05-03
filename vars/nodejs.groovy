def lintChecks() {
       sh "echo installing JSLINT"
       sh "npm install jslint"
       sh "ls -ltr node_modules/jslint/bin"
       // sh "./node_modules/jslint/bin/jslint.js server.js"
       sh "echo LINT CHECKS COMPLETED !!!"
     }

def call(){
pipeline {
    agent any
    stages {
        stage('Lint Checks') {
          steps {
             script {
                 lintChecks()
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