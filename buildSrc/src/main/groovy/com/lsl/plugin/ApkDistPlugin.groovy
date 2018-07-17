package com.lsl.plugin

import com.lsl.plugin.task.ApkReleaseTask
import com.lsl.plugin.task.ApkbuildTask
import org.gradle.api.Project
import org.gradle.api.tasks.Delete

class ApkDistPlugin extends BasePlugin {


    @Override
    protected void createExtension() {
        super.createExtension()
        project.extensions.create("apkdistconf", ApkDistExtension.class)
    }

    @Override
    protected void configProject() {
        super.configProject()
        writeDespenons()

        syncConfig()



        project.subprojects.each { sub ->

            sub.afterEvaluate {
                syncConfig(sub)

            }
        }

    }

    @Override
    protected void createTask() {
        super.createTask()


        project.task('apkbuild', group: 'apk', type: ApkbuildTask,
                description: 'apkbuild')
        project.task('apkclean', group: 'apk', type: Delete,
                description: 'apkclean') {
            delete new File(project.buildDir, "xxxxxx")
        }
        project.task('apkRelease', group: 'apk', type: ApkReleaseTask,
                description: 'apkRelease')

    }


    void writeDespenons() {
        project.subprojects.each {
            File file = it.buildFile
            def enter = false
            def text = ''
            file.eachLine {
                if (enter) {
                    if (it.startsWith('}')) {
                        return
                    }
                    text += it + System.lineSeparator()
                }
                if (it.startsWith("dependencies {")) {
                    enter = true
                }
            }
            println text

        }


    }


    static void syncConfig(Project project) {
        if (project == null) {
            return
        }

        project.configurations.all { cfg ->
            cfg.resolutionStrategy {
                eachDependency { details ->
                    def module = details.requested
                    println "group:$module.group ,name:$module.name,version:$module.version"
                    if(module.group.equals('com.android.support')&&!module.name.equals('multidex')){
                        details.useVersion('22.2.1')
                    }

                }
            }

        }
    }
}
