node {
    docker.image('elek/ozone-build').inside {
        stage('Build') {
            try {
                sh 'mvn clean install -DskipTests'
                //                    junit testResults: '**/target/surefire-reports/TEST-*.xml'

            } catch (err) {
                if (env.CHANGE_ID) {
                    pullRequest.createStatus(status: 'error',
                            context: 'continuous-integration/jenkins/pr-merge/build',
                            description: 'Maven build is failed')
                }
                throw err
            }
        }
        stage('Unit test') {
            try {
                sh 'mvn test'
                junit testResults: '**/target/surefire-reports/TEST-*.xml'
            } catch (err) {
                if (env.CHANGE_ID) {
                    pullRequest.createStatus(status: 'error',
                            context: 'continuous-integration/jenkins/pr-merge/build',
                            description: 'Maven build is failed')
                }
                throw err
            }
        }
    }

}

