package com.lsl.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BasePlugin implements Plugin<Project> {

    protected Project project

    @Override
    void apply(Project project) {
        this.project = project

        createExtension()

        configProject()


        createTask()
    }


    protected void createExtension() {

    }


    protected void configProject() {

        project.gradle.buildFinished {
            tidyUp()
        }

    }

    protected void createTask() {

    }


    protected void tidyUp() {
        println 'build finsh!'
    }


}