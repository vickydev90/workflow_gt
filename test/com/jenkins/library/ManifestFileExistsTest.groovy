package com.jenkins.library

import support.PipelineSpockTestBase
import org.apache.commons.io.FileUtils;
import groovy.json.JsonSlurper

class ManifestFileExistsTest extends PipelineSpockTestBase {

    def setup()  {
        super.setUp()
    }

    def "Check manifest file exists"() {
        given:
            def manefestFileName = 'manifest.json'

        when:
            String manifestFile = FileUtils.readFileToString(new File(manefestFileName));
            def slurper = new groovy.json.JsonSlurper()
            def result = slurper.parseText(manifestFile)

        then:

            result.Jenkins != ""
    }
}
