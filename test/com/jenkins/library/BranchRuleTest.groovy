package com.jenkins.library

import support.PipelineSpockTestBase

class BranchRuleTest extends PipelineSpockTestBase {

    def setup()  {
        super.setUp()
    }

    def "Feature branches are correctly identified"() {
        expect:
            assertJobStatusSuccess()
            workflow == BranchRule.getGitWorkFlow(branch)

        where:
            branch          |   workflow
            'sprint1/test'  |   'feature-branch'
            'epic/test'     |   'feature-branch'
            'feature/test'  |   'feature-branch'
            'pr/1/head'     |   'feature-branch'
            'pr/1/merge'    |   'feature-branch'

            'PR-1'          |   'pull-request'
            'PR-1-head'     |   'pull-request'
            'PR-1-merge'    |   'pull-request'

            'release-prod'  |   'integration-branch'
            'releases/1'    |   'integration-branch'
            'release/1'     |   'integration-branch'
            'release-1'     |   'integration-branch'
            'master'        |   'integration-branch'
            'hotfix/1'      |   'integration-branch'
            'hotfixes'      |   'integration-branch'
            'develop'       |   'integration-branch'
            'bugfix/1'      |   'integration-branch'
            'feat-1'        |   'integration-branch'
    }

    def "unknown branch correctly fails"() {
        given:
            def branchName = 'blah'

        when:
            BranchRule.getGitWorkFlow(branchName)

        then:
            def e = thrown(IllegalArgumentException)
            e.message == "No known git-workflow rule for branch called ${branchName}"
    }
}