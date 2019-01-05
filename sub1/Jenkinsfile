node {
    docker.image('elek/ozone-build').inside {
        stage('Checkout') { // for display purposes
            git 'https://github.com/elek/prcitest.git'

        }
        stage('Build') {
            prStatusStart("build")
            status = sh returnStatus: true, script: 'mvn clean install -DskipTests'
            prStatusResult(status, "build")
        }

        stage('Licence') {
            prStatusStart("licence")
            output = sh returnStatus: true, script: './rat.sh'
            prStatusResult(status, "licence")
        }

        stage('Author') {
            prStatusStart("author")
            output = sh returnStatus: true, script: './author.sh'
            prStatusResult(status, "author")
        }

        stage('Unit test') {
            prStatusStart("test")
            output = sh returnStatus: true, script: 'mvn test -fn'
            junit '**/target/surefire-reports/*.xml'
            prStatusResult(status, "test")
        }

        stage('Checkstyle') {
            prStatusStart("checkstyle")
            output = sh returnStatus: true, script: './checkstyle.sh'
            prStatusResult(status, "checkstyle")
        }
    }

}


def prStatusStart(name) {
    if (env.CHANGE_ID) {
        pullRequest.createStatus(status: "pending",
                context: 'continuous-integration/jenkins/pr-merge/' + name,
                description: name + " is started")
    }
}

def prStatusResult(responseCode, name) {
    if (env.CHANGE_ID) {
        status = "error"
        desc = "failed"
        if (responseCode == 0) {
            status = "success"
            desc = "passed"
        }
        pullRequest.createStatus(status: status,
                context: 'continuous-integration/jenkins/pr-merge/' + name,
                description: name + " is " + desc)
    }
    if (responseCode != 0) {
        currentBuild.result = "FAILURE"
    }
}
