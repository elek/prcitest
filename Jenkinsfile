node {
    docker.image('elek/ozone-build').inside {
        stage('Checkout') { // for display purposes
            git 'https://github.com/elek/prcitest.git'

        }
        stage('Build') {
            try {
                if (env.CHANGE_ID) {
                    pullRequest.createStatus(status: 'pending',
                            context: 'continuous-integration/jenkins/pr-merge/build',
                            description: 'Maven build is started')
                }
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
                if (env.CHANGE_ID) {
                    pullRequest.createStatus(status: 'pending',
                            context: 'continuous-integration/jenkins/pr-merge/unit',
                            description: 'Maven test is started')
                }
                sh 'mvn test'
                junit testResults: '**/target/surefire-reports/TEST-*.xml'
            } catch (err) {
                if (env.CHANGE_ID) {
                    pullRequest.createStatus(status: 'error',
                            context: 'continuous-integration/jenkins/pr-merge/unit',
                            description: 'Maven test is failed')
                }
                throw err
            }
        }
    }

}

