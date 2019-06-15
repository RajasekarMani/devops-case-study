#!/bin/groovy

def call() {
    try {
    	stage('Preparation') {
    		checkout scm
    	}
    }
    catch (e) {
        println("Something went wrong...")
        throw e
    }
}
return this;