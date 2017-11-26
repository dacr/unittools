// Jenkins Requirements :
// - plugins :
//   + Slack Notification
//

pipeline {
  agent any
  environment { 
    LANG = 'C'
  }
  triggers {
    pollSCM('H/2 * * * *')
  }
  options {
    buildDiscarder(logRotator(numToKeepStr:'20'))
  }
  stages {

    // ----------------------------- BUILD
    stage('build') {
      agent { docker { image 'dacr/jenkins-docker-agent-sbt' } }
      steps {
        sh 'sbt test'
        sh 'sbt package'
      }
      post {
        success {
          archive 'target/**/unittools*.jar'
          junit 'target/junitresults/**/*.xml'
        }
      }
    }

  }

  post {
    success {
      slackSend(
        failOnError:false, color:'good',
        message: "${env.JOB_NAME} build SUCCESS ! <${env.RUN_DISPLAY_URL}|pipeline status>",
      )
    }
    failure {
      slackSend(
        failOnError:false, color:'#FF0000',
        message:"${env.JOB_NAME} build FAILURE ! <${env.RUN_DISPLAY_URL}|pipeline status>",
      )
    }
  }
}
