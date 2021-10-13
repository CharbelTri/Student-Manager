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

                sh "echo "ROOT_PASSWORD=root" > .env"
                sh "echo "DATASOURCE_URL=jdbc:mysql:/\/\/mysql-standalone:3306/studentManagerDB?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false" >> .env"
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
