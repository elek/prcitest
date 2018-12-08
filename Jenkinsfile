pipeline {
    agent { docker 'elek/ozone-build' }
    stages {
          stage('Build') {
               steps {
                    script {
                         pullRequest.createStatus(status: 'pending',
                              context: 'continuous-integration/jenkins/pr-merge/build',
                              description: 'Maven build has been started')
                    }
                    sh 'mvn clean install -DskipTests'
               }
          }
          post {
               failure {
                    script {
                         pullRequest.createStatus(status: 'error',
                         context: 'continuous-integration/jenkins/pr-merge/build',
                         description: 'Maven build is failed')
                    }
               }
               unstable {
                    script  {
                         pullRequest.createStatus(status: 'error',
                         context: 'continuous-integration/jenkins/pr-merge/build',
                         description: 'Maven build is unstable')
                    }
               }
               success {
                    script {
                         pullRequest.createStatus(status: 'success',
                         context: 'continuous-integration/jenkins/pr-merge/build',
                         description: 'Maven build is OK')
                    }
               }
          }
          stage('Unit test') {
               steps {
                    script {
                         pullRequest.createStatus(status: 'pending',
                              context: 'continuous-integration/jenkins/pr-merge/test',
                              description: 'Maven test execution has been started')
                    }
                    sh 'mvn test -fn'
               }
               post {
                    always {
                         junit testResults: '**/target/surefire-reports/TEST-*.xml'
                    }
               }
          }
          post {
               failure {
                    script {
                         pullRequest.createStatus(status: 'error',
                         context: 'continuous-integration/jenkins/pr-merge/test',
                         description: 'Maven test is failed')
                    }
               }
               unstable {
                    script  {
                         pullRequest.createStatus(status: 'error',
                         context: 'continuous-integration/jenkins/pr-merge/test',
                         description: 'Maven test is unstable')
                    }
               }
               success {
                    script {
                         pullRequest.createStatus(status: 'success',
                         context: 'continuous-integration/jenkins/pr-merge/test',
                         description: 'Maven test is OK')
                    }
               }
          }
    }
}

