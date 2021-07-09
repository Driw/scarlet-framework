@NonCPS
def modules() {
	def  dirsl = [ "scarlet-core", "scarlet-logger", "scarlet-stream", "scarlet-context" ]
	dirsl
}

pipeline {
	agent any

	environment {
		PROJECT_DIR_PATH = "${env.WORKSPACE}"
		GIT_URL = 'https://github.com/diverproject/scarlet-framework.git'
		GIT_BRANCH = 'feature/AE-1-base'
		SONAR_QUBE_ENV = 'sonar'
		NEXUS_VERSION = 'nexus3'
		NEXUS_PROTOCOL = 'http'
		NEXUS_URL = 'kubernetes.docker.internal:60003'
		NEXUS_REPOSITORY = 'maven-releases'
		NEXUS_CREDENTIAL_ID = 'nexus3-login'
	}

	tools {
		maven "maven-3.6.3"
	}

	stages {
		stage('Checkout') {
			steps {
				git branch: "${env.GIT_BRANCH}", url: "${env.GIT_URL}"
			}
		}

		stage('Build') {
			steps {
				script {
					sh "mvn clean validate compile package install -Dmaven.test.skip=true"
				}
			}
		}

		stage('JUnit') {
			steps {
				script {
					sh "mvn test"
				}
			}
			post {
				always {
					script {
						for (module in modules()) {
							junit "${module}/target/surefire-reports/*.xml"
						}
					}
				}
			}
		}

		stage('SonarQube Scan') {
			steps {
				script {
					for (module in modules()) {
						dir("${env.PROJECT_DIR_PATH}/${module}") {
							withSonarQubeEnv("${env.SONAR_QUBE_ENV}") {
								sh "mvn sonar:sonar"
							}
						}
					}
				}
			}
		}

		stage("SonarQube Quality Gate") {
			steps {
				script {
					for (module in modules()) {
						dir("${env.PROJECT_DIR_PATH}/${module}") {
							script {
								timeout(time: 30, unit: 'SECONDS') {
									def response = waitForQualityGate()
									if (response.status != 'OK') {
										error "Pipeline aborted due to quality gate failure: ${qg.status}"
									}
								}
							}
						}
					}
				}
			}
		}

		stage("Publish to Nexus") {
			steps {
				script {
					for (module in modules()) {
						dir("${env.PROJECT_DIR_PATH}/${module}") {
							script {
								pom = readMavenPom file: "pom.xml";
								filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
								echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
								artifactPath = filesByGlob[0].path;
								artifactExists = fileExists artifactPath;
								if(artifactExists) {
									nexusArtifactUploader(
										nexusVersion: "${env.NEXUS_VERSION}",
										protocol: "${env.NEXUS_PROTOCOL}",
										nexusUrl: "${env.NEXUS_URL}",
										groupId: pom.parent.groupId,
										version: pom.version,
										repository: "${env.NEXUS_REPOSITORY}",
										credentialsId: "${env.NEXUS_CREDENTIAL_ID}",
										artifacts: [
											[
												artifactId: pom.artifactId,
												classifier: '',
												file: artifactPath,
												type: pom.packaging
											],[
												artifactId: pom.artifactId,
												classifier: '',
												file: "pom.xml",
												type: "pom"
											]
										]
									);
								} else {
									error "*** File: ${artifactPath}, could not be found";
								}
							}
						}
					}
				}
			}
		}
	}
}
