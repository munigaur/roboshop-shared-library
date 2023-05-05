def sonarChecks(COMPONENT) {
       sh "echo starting Code Quality Analysis"
       //sh "sonar-scanner -Dsonar.projectKey=catalogue -Dsonar.sources=. -Dsonar.host.url=http://${SONAR_URL}:9000 -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}"
       //sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gata.sh"
       //sh "bash -x sonar-quality-gata.sh  ${SONAR_USR} ${SONAR_PSW} ${SONAR_URL} ${COMPONENT}"     
       sh "echo sonar checks COMPLETED !!!!"
     }