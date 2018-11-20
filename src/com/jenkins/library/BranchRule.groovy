/*
 * Author: Kshitiz mahajan
 */
package com.jenkins.library

/**
 * Branch Rule Class.
 */
class BranchRule {

    /** String */
    String branchName

    /**
     * Determines Git Work Flow
     *
     * @return String the workflow - either feature-branch, pull-request or integration-branch
     */
    static String getGitWorkFlow(String branchName) {
    if (this.isFeatureBranch(branchName) ) {
        return 'feature-branch'
    } else if (this.isPullRequestBranch(branchName)) {
        return 'pull-request'
    } else if (this.isIntegrationBranch(branchName) ) {
        return 'integration-branch'
    }

    throw new IllegalArgumentException("No known git-workflow rule for branch called ${branchName}")
}

    /**
     * Is pull request.
     *
     * @return Boolean
     */
    private static Boolean isPullRequestBranch(String branch) {
        branch =~ /^PR-[0-9]+/          ||
        branch =~ /^PR-[0-9]+-head/     ||
        branch =~ /^PR-[0-9]+-merge/
    }

    /**
     * Is featured branch.
     *
     * @return Boolean
     */
    private static Boolean isFeatureBranch(String branch) {
        branch =~ /^sprint[0-9]+\/.+$/   ||
        branch =~ /^epic\/.+$/           ||
        branch =~ /^feature\/.*$/        ||
        branch =~ /^pr\/[0-9]+\/head$/   ||
        branch =~ /^pr\/[0-9]+\/merge$/
    }

    /**
     * Is integration branch.
     *
     * @return Boolean
     */
    private static Boolean isIntegrationBranch(String branch) {
        branch =~ /^release-prod.*$/ ||
        branch =~ /^releases\/.*$/   ||
        branch =~ /^release\/.*$/    ||
        branch =~ /^release-.*$/     ||
        branch =~ /^master$/         ||
        branch =~ /^hotfix\/.*$/     ||
        branch =~ /^hotfixes.*$/     ||
        branch =~ /^develop$/        ||
        branch =~ /^bugfix\/.*$/     ||
        branch =~ /^feat-.*$/
    }
}
