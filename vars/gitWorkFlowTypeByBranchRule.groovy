import com.jenkins.library.BranchRule

/**
*	Returns the Rule for the current branch.
*
*	@returns the branch type
*	@see com.jenkins.library.BranchRule
**/
def String call() {
	return call(env.BRANCH_NAME)
}

/**
*	Returns the Rule for a branch.
*
*	@param branchName the name of the branch to be tested
*	@returns the branch type
*	@see com.jenkins.library.BranchRule
**/
def String call(String branchName) {

	BranchRule rule = new BranchRule()

	def result = rule.getGitWorkFlow(branchName)

	return result
}
