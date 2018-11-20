# gitflow-enablers-shared-lib

<!-- TOC -->

- [What does this repo include](#what-does-this-repo-include)
    - [Pre-Requisites](#pre-requisites)
    - [Configuration on Jenkins server](#configuration-on-jenkins-server)
        - [Global Configuration for Plugins](#global-configuration-for-plugins)
    - [Configuration for Shared Library on Jenkins project level or at Global configuration level](#configuration-for-shared-library-on-jenkins-project-level-or-at-global-configuration-level)
    - [How to use the shared library](#how-to-use-the-shared-library)
    - [Parameters](#parameters)
    - [Working Setup](#working-setup)

<!-- /TOC -->

## Branch Rule

Use this to determine the git work flow based on Branch name.

_How to use shared library in a pipeline_

```
library identifier: 'gitflowEnablers_multi@master', retriever: modernSCM([$class: 'GitSCMSource',
	remote: 'https://github.lbg.eu-gb.bluemix.net/jenkins-shared-library/gitflowEnablers_multi.git',
	credentialsId: 'jenkinsGHEPAT'])
    
stage ('Init') {
    steps {
        script {
            echo "Checking git workflow"
            String gitWorkFlow = gitWorkFlowTypeByBranchRule()
            echo "gitWorkFlow: ${gitWorkFlow}"					
        }
    }
}
```


#### If not a multibranch project
```groovy
def branchName = 'release/sprint/5'
BranchRule rule = new BranchRule(branchName)
def result = rule.getGitWorkFlow()

echo "result: ${result}"

```

### Parameters

| name | mandatory | default | possible values |
| ----------|-----------|---------|-----------------|
| branchName | yes |  | String |


* `branchName` defines name of a branch.


#### Rules can be found in following functions:

```groovy
    private Boolean isPullRequestBranch()
    private Boolean isFeatureBranch()
    private Boolean isIntegrationBranch()
```
