pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven1"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/CharbelTri/Student-Manager.git'

                echo "ROOT_PASSWORD=root" > .env
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean install -P${PROFILE}"
                sh "cat .env"
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean install"
            }
        }
	    stage('deploy'){
	        steps {
	            sh "mvn -Dmaven.test.failure.ignore=true clean install -P${PROFILE}"
	        }
	    }
    }
}
