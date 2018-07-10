package com.lsl.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

class ApkbuildTask extends DefaultTask {

    @Optional
    String message = 'i am lsl'

    @TaskAction
    def hello() {
        println "hello world $message"
        File file=new File(project.getBuildDir(),"xxxxxx")
        if(!file.exists()){
            file.createNewFile()
        }
    }


}