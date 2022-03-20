def getServer(username, password){
    def remote = [:]
    remote.name = 'server'
    remote.host = '124.223.105.99'
    remote.user = username
    remote.port = 22
    remote.password = password
    remote.allowAnyHosts = true
    return remote
}

pipeline {
    agent {
        docker {
            image 'maven:3.6-alpine'
            args '-v /root/.m2:/root/.m2 -v /home/ubuntu/DockerWorkBench:/home/Deploy --net=host'
        }
    }
    environment {
            CONTAINER_NAME = 'citi'
            IMAGE_NAME = 'citi'
            IMAGE_VERSION = '1'
    }
    stages {
            stage('BuildAndCP') {
                steps {
                    sh 'mvn -B -DskipTests clean package'
                    sh 'cp -f Dockerfile /home/Deploy'
                    sh 'cp -f target/*.jar /home/Deploy'
                    sh '# cp -f src/main/sql/*.sql /home/Deploy'
                }
            }
            stage('Test') {
                steps {
                    sh 'mvn test'
                }
                post {
                    always {
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }
            stage('JacocoPublisher'){
                steps{
                    jacoco()

                }
            }
            stage('SSHDeploy'){
                steps{
                    script{
                    	def server
						withCredentials([usernamePassword(credentialsId: 'SSH-Sever-Root', passwordVariable: 'Password', usernameVariable: 'Username')]) {
							  server = getServer(env['Username'],env['Password'])
						}

                        sshCommand remote: server , command: "cd /home/ubuntu/pylib/stockPackage/ && python3 predict_allocation.py train"

                        sshCommand remote: server , command: "cd /home/ubuntu/DockerWorkBench && \
						 ((sudo docker container stop ${env.CONTAINER_NAME} && sudo docker container rm ${env.CONTAINER_NAME}) || true ) && \
						 (sudo docker image rm ${env.IMAGE_NAME}:${env.IMAGE_VERSION} || true) && \
						 sudo docker build -t ${env.IMAGE_NAME}:${env.IMAGE_VERSION} . && \
						 sudo docker run -d -v /home/ubuntu/pylib:/root/pylib -v /home/ubuntu/server-data:/root/server-data --net=host \
						 --name ${env.CONTAINER_NAME} ${env.IMAGE_NAME}:${env.IMAGE_VERSION}"
                    }
                }
            }

    }
}
