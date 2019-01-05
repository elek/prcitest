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
            output = sh returnStatus: true, script: 'sub1/dev-support/checks/rat.sh'
            prStatusResult(status, "licence")
        }

        stage('Author') {
            prStatusStart("author")
            output = sh returnStatus: true, script: 'sub1/dev-support/checks/author.sh'
            prStatusResult(status, "author")
        }

        stage('Unit test') {
            prStatusStart("test")
            output = sh returnStatus: true, script: 'sub1/dev-support/checks/unit.sh'
            junit '**/target/surefire-reports/*.xml'
            prStatusResult(status, "unit")
        }

        stage('Checkstyle') {
            prStatusStart("checkstyle")
            output = sh returnStatus: true, script: 'sub1/dev-support/checks/checkstyle.sh'
            prStatusResult(status, "checkstyle")
        }

        stage('Findbugs') {
            prStatusStart("findbugs")
            output = sh returnStatus: true, script: 'sub1/dev-support/checks/findbugs.sh'
            prStatusResult(status, "findbugs")
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
